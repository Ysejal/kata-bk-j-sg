package kata.sg.services;

import kata.sg.dto.OperationDto;
import kata.sg.entities.BankAccount;
import kata.sg.entities.Operation;
import kata.sg.repositories.BankAccountRepository;
import kata.sg.repositories.OperationRepository;
import kata.sg.services.impl.OperationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class OperationServiceTest {

    @Autowired
    OperationService operationService;

    @MockBean
    OperationRepository operationRepository;

    @MockBean
    BankAccountRepository bankAccountRepository;


    @BeforeEach
    void setUp() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000d);

        Operation operation = new Operation();
        operation.setBankAccount(bankAccount);

        when(bankAccountRepository.findById(anyInt()))
                .thenReturn(bankAccount);

        when(bankAccountRepository.save(any(BankAccount.class)))
                .thenReturn(bankAccount);

        when(operationRepository.save(any(Operation.class)))
                .thenReturn(operation);

    }

    @Test
    void deposit() {
        OperationDto deposit = operationService.deposit(100d);
        assertNotNull(deposit);
        assertEquals(deposit.getBankAccountBalance().toString(), "1100.0");
    }

    @Test
    void withdraw() {
        OperationDto withdraw = operationService.withdraw(100d);
        assertNotNull(withdraw);
        assertEquals(withdraw.getBankAccountBalance().toString(), "900.0");
    }
}