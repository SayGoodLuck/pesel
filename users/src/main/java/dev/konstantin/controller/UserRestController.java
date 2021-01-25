package dev.konstantin.controller;

import dev.konstantin.dao.UserServiceDaoImpl;
import dev.konstantin.entity.UserInfo;
import dev.konstantin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

  private UserServiceDaoImpl userServiceDaoImpl;

  @RequestMapping(
      value = "{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserInfo> getUser(@PathVariable("id") String id) {
    if (id == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    UserInfo foundUser = this.userServiceDaoImpl.findByPesel(id);
    if (foundUser == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(foundUser, HttpStatus.CREATED);
    }
  }

  @RequestMapping(
      value = "",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserInfo> saveUser(@RequestBody @Valid UserInfo userInfo) {
    HttpHeaders headers = new HttpHeaders();

    if (userInfo == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    this.userServiceDaoImpl.saveUser(
        userInfo.getPesel(), userInfo.getName(), userInfo.getSurname(), userInfo.getEmail());
    return new ResponseEntity<>(userInfo, headers, HttpStatus.CREATED);
  }

  public ResponseEntity<UserInfo> updateUser(UserInfo userInfo) {
    HttpHeaders headers = new HttpHeaders();
    if (userInfo == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    this.userServiceDaoImpl.saveUser(
        userInfo.getPesel(), userInfo.getName(), userInfo.getSurname(), userInfo.getEmail());
    return new ResponseEntity<>(userInfo, headers, HttpStatus.OK);
  }

  @RequestMapping(
      value = "{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserInfo> deleteUser(@PathVariable("id") String id) {
    if (id == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    UserInfo userInfo = this.userServiceDaoImpl.findByPesel(id);
    if (userInfo == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    this.userServiceDaoImpl.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(
      value = "",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserInfo>> getAllUsers() {
    List<UserInfo> users = this.userServiceDaoImpl.findAll();

    if (users.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}
