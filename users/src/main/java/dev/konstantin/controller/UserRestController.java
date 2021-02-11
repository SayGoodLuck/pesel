//package dev.konstantin.controller;
//
//import dev.konstantin.entity.UserInfo;
//import dev.konstantin.service.UserServiceImpl;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api")
//public class UserRestController {
//
//  private UserServiceImpl userServiceImpl;
//
//  @RequestMapping(
//      value = "{id}",
//      method = RequestMethod.GET,
//      produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity<UserInfo> getUser(@PathVariable("id") String id) {
//    if (id == null) {
//      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//    Optional<UserInfo> foundUser = this.userServiceImpl.findUserByPesel(id);
//    if (foundUser == null) {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    } else {
//      return new ResponseEntity<>(foundUser, HttpStatus.CREATED);
//    }
//  }
//
//  @RequestMapping(
//      value = "",
//      method = RequestMethod.POST,
//      produces = MediaType.APPLICATION_JSON_VALUE)
//
//  //todo заменить UserInfo на другой тип, потому что поля gender и birthday == нулю
//  public ResponseEntity<UserInfo> saveUser(@RequestBody @Valid UserInfo userInfo) {
//    HttpHeaders headers = new HttpHeaders();
//
//    if (userInfo == null) {
//      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//    this.userServiceImpl.saveUser(
//        userInfo.getPesel(), userInfo.getName(), userInfo.getSurname(), userInfo.getEmail());
//    return new ResponseEntity<>(userInfo, headers, HttpStatus.CREATED);
//  }
//
//  public ResponseEntity<UserInfo> updateUser(UserInfo userInfo) {
//    HttpHeaders headers = new HttpHeaders();
//    if (userInfo == null) {
//      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//    this.userServiceImpl.saveUser(
//        userInfo.getPesel(), userInfo.getName(), userInfo.getSurname(), userInfo.getEmail());
//    return new ResponseEntity<>(userInfo, headers, HttpStatus.OK);
//  }
//
//  @RequestMapping(
//      value = "{id}",
//      method = RequestMethod.DELETE,
//      produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity<UserInfo> deleteUser(@PathVariable("id") String id) {
//    if (id == null) {
//      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//    Optional<UserInfo> userInfo = this.userServiceImpl.findUserByPesel(id);
//    if (userInfo == null) {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    this.userServiceImpl.deleteUser(id);
//    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//  }
//
//  @RequestMapping(
//      value = "",
//      method = RequestMethod.GET,
//      produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity<List<UserInfo>> getAllUsers() {
//    List<UserInfo> users = this.userServiceImpl.getAllUsers();
//
//    if (users.isEmpty()) {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    return new ResponseEntity<>(users, HttpStatus.OK);
//  }
//}
