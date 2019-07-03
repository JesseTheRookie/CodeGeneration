package io.swagger.model;

import io.swagger.models.auth.In;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionTest {
    private Transaction transaction;

    @Before
    public void init(){transaction = new Transaction("NL01INHO0000000004", "NL01INHO0000000002", 50.0, "TRANSACTION", 1 );}

    @Test
    public void createTransactionShouldNotBeNull(){assertNotNull(transaction);}

    @Test
    public void newTransactionShouldHaveTheCorrectFromIban(){assertEquals("NL01INHO0000000004", transaction.getFromIban());}

    @Test
    public void newTransactionShouldHaveTheCorrectToIban(){assertEquals("NL01INHO0000000002", transaction.getToIban());}

    @Test
    public void newTransactionShouldHaveTheCorrectAmount(){assertEquals((Double)50.0, transaction.getAmount());}

    @Test
    public void newTransactionShouldHaveTheCorrectType(){assertEquals(Transaction.TransactionType.TRANSACTION, transaction.getType());}

    @Test
    public void newTransactionShouldHaveTheCorrectPerformer(){assertEquals((Integer)1, transaction.getPerformedBy());}

    @Test(expected = IllegalArgumentException.class)
    public void negativeAmountShouldThrowException(){transaction.setAmount(-1.0);}

}
