package kata.sg;

import kata.sg.dto.OperationDto;
import kata.sg.enums.OperationType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KataApplicationTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void allOperations() throws Exception {
		ResponseEntity<OperationDto[]> response =
				restTemplate.getForEntity(
						"http://localhost:" + port + "/operations",
						OperationDto[]. class);
		OperationDto[] operations = response.getBody();

		assertThat(operations).isNotNull();
		assertThat(operations).hasSize(2);

		assertThat(operations[0].getType()).isEqualTo(OperationType.DEPOSIT);
		assertThat(operations[0].getAmount()).isEqualTo(500d);

		assertThat(operations[1].getType()).isEqualTo(OperationType.WITHDRAWAL);
		assertThat(operations[1].getAmount()).isEqualTo(100d);
	}

}