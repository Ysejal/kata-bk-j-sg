package kata.sg.controllers;

import kata.sg.dto.OperationDto;
import kata.sg.dto.OperationRequestBody;
import kata.sg.services.IOperationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class OperationController {

    @Autowired
    private IOperationService operationService;

    @GetMapping(value="/operations")
    public List<OperationDto> getAllOperations() {

        return operationService.allOperations();
    }

    @GetMapping(value="/balance")
    public Double getBalance() {
        return operationService.getBalance();
    }

    @PostMapping(value = "/deposit")
    public ResponseEntity<OperationDto> deposit(@RequestBody OperationRequestBody operation) {
        OperationDto newOperation = operationService.deposit(operation.getAmount());

        if(newOperation == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOperation);
    }

    @PostMapping(value = "/withdraw")
    public ResponseEntity<OperationDto> withdraw(@RequestBody OperationRequestBody operation) {
        OperationDto newOperation = operationService.withdraw(operation.getAmount());

        if(newOperation == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOperation);
    }

}