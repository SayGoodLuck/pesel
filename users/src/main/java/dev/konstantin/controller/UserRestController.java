package dev.konstantin.controller;

import dev.konstantin.dao.UserServiceDaoImpl;
import dev.konstantin.entity.UserInfo;
import dev.konstantin.dao.InMemoryUserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@RequestMapping("")
public class UserRestController {

  @Autowired
  private UserServiceDaoImpl userServiceDaoImpl;
  //todo правильно подать параметры

  @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserInfo> getUserById(@PathVariable("id") String id) {
    UserInfo foundUser = userServiceDaoImpl.findByPesel(id);
    if(foundUser == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<UserInfo>(foundUser, HttpStatus.CREATED);
    }
  }
}
