package by.telebook.telebookapplication.teleBook;

import by.telebook.telebookapplication.telNum.Number;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeleBookRepository extends JpaRepository<TeleBook, Long> {
    Optional<List<Number>> findByIdOrderByNumberNumber(Long id);
}
