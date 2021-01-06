package dev.konstantin.entity;

import dev.konstantin.config.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String pesel;

  @NotBlank(message = "Name is mandatory")
  @Column(name = "name")
  private String name;

  @NotBlank(message = "Surname is mandatory")
  @Column(name = "surname")
  private String surname;

  @NotBlank(message = "Email is mandatory")
  @Column(name = "email")
  private String email;

  @NotBlank(message = "Gender is mandatory")
  @Column(name = "sex")
  private Gender gender;

  @NotBlank(message = "Date of birthday is mandatory")
  @Column(name = "birthday")
  private LocalDate birthday;
}
