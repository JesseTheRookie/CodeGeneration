package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Service
public class SwaggerService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public SwaggerService(UserRepository userRepository, AccountRepository accountRepository) {

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(User newUser) {
        userRepository.save(newUser);
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAccountsByUserId(Integer id) {
        List<Account> results = new List<Account>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Account> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Account account) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Account> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Account> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Account get(int index) {
                return null;
            }

            @Override
            public Account set(int index, Account element) {
                return null;
            }

            @Override
            public void add(int index, Account element) {

            }

            @Override
            public Account remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Account> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Account> listIterator(int index) {
                return null;
            }

            @Override
            public List<Account> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        List<Account> allAccounts = (List<Account>) accountRepository.findAll();
        for (Account a:allAccounts){
            if (a.getUserId().equals(id)){
                results.add(a);
            }
        }
        return results;
    }
}
