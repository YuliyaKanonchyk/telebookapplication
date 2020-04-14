package by.telebook.telebookapplication.telNum;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NumberRepository extends JpaRepository<Number, Long> {
    Optional<Number> findByNumber (String number);
    Optional<List<Number>> findNumbersByTeleBook_Id(Long id);
}
