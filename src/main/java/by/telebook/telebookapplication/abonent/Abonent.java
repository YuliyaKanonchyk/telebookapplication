package by.telebook.telebookapplication.abonent;

import by.telebook.telebookapplication.city.City;
import by.telebook.telebookapplication.telNum.Number;
import by.telebook.telebookapplication.telOperator.TelOperator;
import by.telebook.telebookapplication.teleBook.TeleBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Abonent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 10)
    private String abonentName;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 20)
    private String abonentSurname;

    @OneToOne
    private Number number;

    @OneToOne
    private City city;

    @OneToOne
    private TelOperator telOperator;

    @ManyToOne
    private TeleBook teleBook;
}
