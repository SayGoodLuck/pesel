package dev.konstantin.entity;

import dev.konstantin.config.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

import static dev.konstantin.config.Const.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = USER_TABLE)
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = USER_ID)
  private String id;

  @NotBlank(message = "Name is mandatory")
  @Column(name = USER_NAME)
  private String name;

  @NotBlank(message = "Surname is mandatory")
  @Column(name = USER_LASTNAME)
  private String lastname;

  @NotBlank(message = "Email is mandatory")
  @Column(name = USER_EMAIL)
  private String email;

  @NotBlank(message = "Gender is mandatory")
  @Column(name = USER_GENDER)
  private Gender gender;

  @NotBlank(message = "Date of birthday is mandatory")
  @Column(name = USER_BIRTHDAY)
  private LocalDate birthday;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserInfo userInfo = (UserInfo) o;
    return id.equals(userInfo.id) &&
            name.equals(userInfo.name) &&
            lastname.equals(userInfo.lastname) &&
            email.equals(userInfo.email) &&
            gender == userInfo.gender &&
            birthday.equals(userInfo.birthday);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname, email, gender, birthday);
  }

}
