package io.swagger.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    private Account account;
    private User bank;

    @Before
    public void setUp(){
        bank = new User(100, "Bank", "bank123", "USER_EMPLOYEE");
        account = new Account("NL01INHO0000000001", bank, "SwaggerBank inc.", 100., "CURRENT", "ACTIVE");{
        }
    }

    @Test
    public void accountIsNotNull(){
        assertNotNull(account);
    }

    @Test
    public void accountUserShouldBeBank() {
        assertEquals(bank,account.getUser() );
    }

    @Test
    public void accountUsersUserIdShouldBe100() {
        assertEquals((Integer)100,account.getUserId());
    }

    @Test
    public void accountIbanShouldBeNL01INHO0000000001() {
        assertEquals("NL01INHO0000000001", account.getIban());
    }

    @Test
    public void accountIbanShouldBeSetToIban1() {
        account.setIban("iban1");
        assertEquals("iban1", account.getIban());
    }

    @Test
    public void accountNameShouldBeSwaggerBankInc() {
        assertEquals("SwaggerBank inc.",account.getName());
    }

    @Test
    public void accountNameShouldBeSetToBank() {
        account.setName("Bank");
        assertEquals("Bank",account.getName());
    }

    @Test
    public void accountBalanceShouldBe100() {
        assertEquals((Double)100.,account.getBalance());
    }

    @Test
    public void accountBalanceShouldBeSetTo50() {
        account.setBalance(50.);
        assertEquals((Double)50.,account.getBalance());
    }


    @Test
    public void accountTypeShouldBeCurrent() {
        assertEquals(Account.AccounttypeEnum.CURRENT,account.getAccounttype());
    }

    @Test
    public void accountTypeShouldBeSetToSavings() {
        account.setAccounttype(Account.AccounttypeEnum.SAVINGS);
        assertEquals(Account.AccounttypeEnum.SAVINGS,account.getAccounttype());
    }

    @Test
    public void accountStatusShouldBeActive() {
        assertEquals(Account.StatusEnum.ACTIVE,account.getStatus());
    }

    @Test
    public void accountStatusShouldBeSetToFrozen() {
        account.setStatus(Account.StatusEnum.FROZEN);
        assertEquals(Account.StatusEnum.FROZEN,account.getStatus());
    }

    @Test
    public void accountToStringShouldPrintCorrect(){
        StringBuilder sb = new StringBuilder();
        sb.append("class Account {\n");
        sb.append("    iban: NL01INHO0000000001").append("\n");
        sb.append("    user: class User {").append("\n");
        sb.append("        id: 100").append("\n");
        sb.append("        username: Bank").append("\n");
        sb.append("        password: bank123").append("\n");
        sb.append("        role: USER_EMPLOYEE").append("\n");
        sb.append("    }").append("\n");
        sb.append("    name: SwaggerBank inc.").append("\n");
        sb.append("    balance: 100.0").append("\n");
        sb.append("    accounttype: CURRENT").append("\n");
        sb.append("    status: ACTIVE").append("\n");
        sb.append("}");
        assertEquals(sb.toString(),account.toString());
    }

}