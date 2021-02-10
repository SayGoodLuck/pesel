package dev.konstantin.repository;

import dev.konstantin.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface UserRepository extends JpaRepository<UserInfo, String> {
public interface UserRepository extends Repository {

  boolean save(UserInfo userInfo);

  boolean deleteById(String pesel);

  List<UserInfo> findAll();

  UserInfo findByEmail(String email);

  UserInfo findByPesel(String pesel);
}
