package kata.sg.services.impl;

import kata.sg.dto.OperationDto;
import kata.sg.entities.BankAccount;
import kata.sg.entities.Operation;
import kata.sg.enums.OperationType;
import kata.sg.repositories.BankAccountRepository;
import kata.sg.repositories.OperationRepository;
import kata.sg.services.IOperationService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationService implements IOperationService {

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OperationDto deposit(Double amount) {
        Operation operation = new Operation(OperationType.DEPOSIT, amount);

        BankAccount bankAccount = bankAccountRepository.findById(1);
        operation.setBankAccount(bankAccount);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
        return modelMapper.map(operationRepository.save(operation), OperationDto.class);
    }

    @Override
    public OperationDto withdraw(Double amount) {
        amount = amount > 0 ? (-1 * amount) : amount;

        Operation operation = new Operation(OperationType.WITHDRAWAL, amount);

        BankAccount bankAccount = bankAccountRepository.findById(1);
        operation.setBankAccount(bankAccount);

        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
        return modelMapper.map(operationRepository.save(operation), OperationDto.class);
    }

    @Override
    public List<OperationDto> allOperations() {
        List<Operation> operations = operationRepository.findAll();
        return operations
                .stream()
                .map(operation -> modelMapper.map(operation, OperationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Double getBalance() {
        BankAccount bankAccount = bankAccountRepository.findById(1);
        return bankAccount.getBalance();
    }
    
   
}
