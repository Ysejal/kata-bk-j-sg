package kata.sg.dto;

import kata.sg.dto.OperationDto;
import kata.sg.entities.BankAccount;
import kata.sg.entities.Client;
import kata.sg.entities.Operation;
import kata.sg.enums.OperationType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationDtoTest {
    private ModelMapper modelMapper = new ModelMapper();

    private static Operation operation;
    private static OperationDto operationDto;

    @BeforeAll
    public static void setUp() {
        Client client = new Client();
        client.setLastName("Dupont");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setClient(client);
        bankAccount.setBalance(900d);

        operation = new Operation();
        operation.setBankAccount(bankAccount);
        operation.setType(OperationType.DEPOSIT);
        operation.setAmount(500d);

        operationDto = new OperationDto();
        operationDto.setBankAccountClientLastName("Chikar");
        operationDto.setBankAccountBalance(900d);
        operationDto.setType(OperationType.WITHDRAWAL);
        operationDto.setAmount(100d);
        operationDto.setDate(LocalDateTime.now());
    }

    @Test
    public void mapOperationEntityToOperationDto() {
        OperationDto dto = modelMapper.map(operation, OperationDto.class);
        assertEquals(operation.getType(), dto.getType());
        assertEquals(operation.getAmount().toString(), dto.getAmount().toString());
        assertEquals(operation.getBankAccount().getBalance().toString(), dto.getBankAccountBalance().toString());
        assertEquals(operation.getBankAccount().getClient().getLastName(), dto.getBankAccountClientLastName());
    }

    @Test
    public void mapOperationDtoToOperationEntity() {
        Operation op = modelMapper.map(operationDto, Operation.class);
        assertEquals(op.getType(), operationDto.getType());
        assertEquals(op.getAmount().toString(), operationDto.getAmount().toString());
        assertEquals(op.getBankAccount().getBalance().toString(), operationDto.getBankAccountBalance().toString());
        assertEquals(op.getBankAccount().getClient().getLastName(), operationDto.getBankAccountClientLastName());
    }

}