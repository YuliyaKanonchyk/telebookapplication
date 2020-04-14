package by.telebook.telebookapplication.telNum;

import by.telebook.telebookapplication.telOperator.TelOperator;
import by.telebook.telebookapplication.teleBook.TeleBook;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonPropertyOrder({"telOperator", "number"})
public class Number {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 7, max = 7)
    private String number;

    @OneToOne
    private TelOperator telOperator;

    @ManyToOne
    @JsonSerialize
    private TeleBook teleBook;

    @Override
    public String toString() {
        return "Number{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", telOperator=" + telOperator +
                '}';
    }
}
