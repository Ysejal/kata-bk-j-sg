package kata.sg.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import kata.sg.entities.Client;
import kata.sg.repositories.ClientRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private static Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setLastName("Chikar");
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        assertEquals(this.clientRepository.findById(savedClient.getId()), client);
    }

    @Test
    public void saveClientAndFindByName() {
        Client savedClient = this.clientRepository.save(client);
        List<Client> clients = this.clientRepository.findByLastName(savedClient.getLastName());
        assertEquals(clients.get(0).getLastName(), "Chikar");
    }
}