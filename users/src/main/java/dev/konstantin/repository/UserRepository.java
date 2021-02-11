package dev.konstantin.repository;

import dev.konstantin.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
// import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// @Repository
// public interface UserRepository extends JpaRepository<UserInfo, String> {
public interface UserRepository extends Repository {

  void save(UserInfo userInfo);

  void deleteById(String id);

  UserInfo findById(String pesel);

  UserInfo findByEmail(String email);

  List<UserInfo> findAll();

  boolean isUserExist(String pesel);
}
