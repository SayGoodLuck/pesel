package dev.konstantin.repository;

import dev.konstantin.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {

  UserInfo findByEmail(String email);

  UserInfo findByPesel(String pesel);
}
