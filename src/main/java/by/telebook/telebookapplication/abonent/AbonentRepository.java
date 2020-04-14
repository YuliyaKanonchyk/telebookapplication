package by.telebook.telebookapplication.abonent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbonentRepository extends JpaRepository<Abonent, Long> {
    void removeAbonentByAbonentNameAndAbonentSurname(String abonentName, String abonentSurname);
}
