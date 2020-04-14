package by.telebook.telebookapplication.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String login;

    private UUID uid = UUID.randomUUID();

    @NotNull
    @NotBlank
    @Size(min = 3, max = 8)
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 8)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String lastName;
}
