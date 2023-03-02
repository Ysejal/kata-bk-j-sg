package kata.sg.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import kata.sg.entities.BankAccount;
import kata.sg.entities.Client;
import kata.sg.repositories.BankAccountRepository;
import kata.sg.repositories.ClientRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class BankAccountRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private static Client client;
    private static BankAccount bankAccount;


    @BeforeEach
    void setUp() {
        client = new Client();
        client.setLastName("Chikar");

        bankAccount = new BankAccount();
        bankAccount.setBalance(900.0);
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.bankAccountRepository.save(bankAccount);
        assertEquals(savedBankAccount.getBalance().toString(), "900.0");
    }
}