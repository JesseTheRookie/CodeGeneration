package io.swagger.service;

//import com.sun.org.apache.xpath.internal.operations.Bool;

import io.swagger.api.AccountsApiController;
import io.swagger.api.ApiException;
import io.swagger.api.SecurityController;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import io.swagger.service.AccountService;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import org.springframework.stereotype.Service;
//import sun.jvm.hotspot.code.Location;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private SecurityController securityController;
    private DepositsService depositsService;
    private WithdrawalsService withdrawalsService;
    private UserRepository userRepository;
    private final Double maxAmount = 500.0;
    private final Integer dailyTransactionsLimit = 2;
    private Integer transactionsDoneThisDay = 0;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, SecurityController securityController, UserRepository userRepository, DepositsService depositsService, WithdrawalsService withdrawalsService) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.withdrawalsService = withdrawalsService;
        this.depositsService = depositsService;
        this.securityController = securityController;
        this.userRepository = userRepository;
    }

    private Integer getNumberOfTransactionToday(String iban) {

        Timestamp timestamp = new Timestamp(new Date().getTime());
        Iterable<Transaction> transactions = transactionRepository.findAll();
        for (Transaction t : transactions) {

            //currenttime
            long stamp1 = timestamp.getTime();
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(stamp1);

            //time of transaction
            long stamp2 = t.getTimeStamp().getTime();
            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(stamp2);

            boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

            if (sameDay && iban == t.getFromIban()) {
                transactionsDoneThisDay++;
            }
        }
        return transactionsDoneThisDay;
    }

    private Boolean theTransactionDoesNotExceedTheDailyLimit(String iban) throws ApiException{
        if(getNumberOfTransactionToday(iban) < dailyTransactionsLimit){
            return true;
        } else {
            throw new ApiException(406, "Can't create more transactions than day limit" + dailyTransactionsLimit);
        }
    }

    private Boolean theAmountFromAccountDoesNotExceedTheMaxAmount(Double amount) throws ApiException{
        if(amount < maxAmount){
            return true;
        } else{
            throw new ApiException(406, "Can't transfer more than " + maxAmount);
        }
    }

    private Boolean areAccountsSameUser(String ibanFrom, String ibanTo){
        Account accountFrom = accountRepository.findById(ibanFrom).orElse(null);
        Account accountTo = accountRepository.findById(ibanTo).orElse(null);

        int UserIdFrom = accountFrom.getUserId();
        int UserIdTo = accountTo.getUserId();

        if(UserIdFrom == UserIdTo){
            return true;
        }
        return false;
    }

    private Boolean areAccountsBothOfTypeCurrent(String ibanFrom, String ibanTo){
        Account accountFrom = accountRepository.findById(ibanFrom).orElse(null);
        Account accountTo = accountRepository.findById(ibanTo).orElse(null);

        Account.AccounttypeEnum accounttypeFrom = accountFrom.getAccounttype();
        Account.AccounttypeEnum accounttypeTo = accountTo.getAccounttype();

        if(accounttypeFrom == accounttypeTo){
            return true;
        }
        return false;
    }
    private Boolean theTransactionIsValid(String ibanFrom, String ibanTo) throws ApiException {

        if (areAccountsSameUser(ibanFrom, ibanTo)) {
            return true;
        }
        if (areAccountsBothOfTypeCurrent(ibanFrom, ibanTo)) {
            return true;
        } else{
            throw new ApiException(406, "Can't create a transaction to another savings account than your own nor from a savings acount to another user's current account or vice versa");
        }
    }

    private Boolean theAccountIsCustomer(String iban){
        Account account = accountRepository.findById(iban).orElse(null);
        User user = account.getUser();

        if(user.getRole() == User.RoleEnum.USER){
            return true;
        }
        return false;
    }

    public void createTransaction(Transaction newTransaction) throws ApiException{
        String fromAccountIban = newTransaction.getFromIban();
        String toAccountIban = newTransaction.getTo();
        double amount = newTransaction.getAmount();

        if(theAccountIsCustomer(fromAccountIban)){
            if (theTransactionIsValid(fromAccountIban, toAccountIban)) {
                if (theTransactionDoesNotExceedTheDailyLimit(fromAccountIban)) {
                    if (theAmountFromAccountDoesNotExceedTheMaxAmount(amount)) {
                        if(withdrawalsService.withdrawIsValid(fromAccountIban, amount)) {
                            withdrawalsService.reductFromAccount(fromAccountIban, amount);
                            depositsService.addToAccount(toAccountIban, amount);
                            transactionRepository.save(newTransaction);
                        }
                    }
                }
            }
        } else{
            if(withdrawalsService.withdrawIsValid(fromAccountIban, amount)) {
                withdrawalsService.reductFromAccount(fromAccountIban, amount);
                depositsService.addToAccount(toAccountIban, amount);
                transactionRepository.save(newTransaction);
            }
        }
    }

    public Iterable<Transaction> getAllTransactions() throws ApiException {
        if (userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                ||userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return transactionRepository.findAll();
        }throw  new ApiException(403, "You are not authorized for this request");

    }

    public Iterable<Transaction> getTransactionByIban(String iban) throws ApiException {
        Account account = accountRepository.findById(iban).orElse(null);
        if (userRepository.getUserByName(
                securityController.currentUserName()).getId() == account.getUserId()
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.USER_EMPLOYEE)
                || userRepository.getUserByName(
                securityController.currentUserName()).getRole().equals(User.RoleEnum.EMPLOYEE)){
            return transactionRepository.getTransactionByIban(iban);
            }
       else throw new ApiException(403, "You are not authorized for this request");
    }
}
