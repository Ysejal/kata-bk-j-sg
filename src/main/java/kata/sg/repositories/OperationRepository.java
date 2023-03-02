package kata.sg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kata.sg.entities.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
