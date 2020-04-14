package by.telebook.telebookapplication.telOperator;

import jdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelOperatorRepository extends JpaRepository<TelOperator, Long> {
    Optional<TelOperator> findByName(String name);
}
