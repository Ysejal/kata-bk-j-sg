package kata.sg.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import kata.sg.entities.BankAccount;
import kata.sg.entities.Client;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class BankAccountTest {

    @Autowired
    private TestEntityManager entityManager;

    private BankAccount bankAccount;
    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setLastName("Dupont");

        bankAccount = new BankAccount();
        bankAccount.setBalance(900d);
    }

    @Test
    public void saveClient() {
        Client savedClient = this.entityManager.persistAndFlush(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.entityManager.persistAndFlush(bankAccount);
        assertEquals(savedBankAccount.getBalance().toString(), "900.0");
    }

    @Test
    public void createClientNullException() throws Exception {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            this.entityManager.persist(new BankAccount());
        });

        String expectedMessage = "Client is mandatory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}