package kata.sg.controllers;

import kata.sg.controllers.OperationController;
import kata.sg.dto.OperationDto;
import kata.sg.enums.OperationType;
import kata.sg.services.impl.OperationService;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OperationController.class)
class OperationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OperationService operationService;

    @BeforeEach
    void setUp() {
        OperationDto deposit = new OperationDto();
        deposit.setBankAccountClientLastName("Dupont");
        deposit.setBankAccountBalance(100d);

        deposit.setType(OperationType.DEPOSIT);
        deposit.setAmount(100d);
        deposit.setDate(LocalDateTime.now());
        
        when(operationService.deposit(100d))
                .thenReturn(deposit);

        OperationDto withdraw = new OperationDto();
        withdraw.setBankAccountClientLastName("Dupont");
        withdraw.setBankAccountBalance(0d);
        withdraw.setType(OperationType.WITHDRAWAL);
        withdraw.setAmount(100d);
        withdraw.setDate(LocalDateTime.now());
        when(operationService.withdraw(100d))
                .thenReturn(withdraw);
    }

    @Test
    public void allOperations() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/operations")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deposit() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/deposit")
                .content("{\"amount\":100}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(100));
    }

    @Test
    public void withdraw() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/withdraw")
                .content("{\"amount\":100}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(0));
    }

}