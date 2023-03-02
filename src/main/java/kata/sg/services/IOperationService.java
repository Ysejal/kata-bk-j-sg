package kata.sg.services;

import java.util.List;

import kata.sg.dto.OperationDto;


public interface IOperationService {

    OperationDto deposit(Double amount);

    OperationDto withdraw(Double amount);

    List<OperationDto> allOperations();

    Double getBalance();
}
