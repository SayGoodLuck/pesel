//package dev.konstantin.controller;
//
//import dev.konstantin.dao.UserServiceDaoImpl;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class UserController {
//
//  private UserServiceDaoImpl userServiceDaoImpl;
//
//  @GetMapping("/greeting")
//  public String greeting(
//      @RequestParam(name = "name", required = false, defaultValue = "World") String name,
//      Model model) {
//    model.addAttribute("name", name);
//    return "greeting";
//  }
//
//  @GetMapping("/")
//  public String getIndex() {
//    return "index";
//  }
//
//  @GetMapping("/user")
//  public String createUserPage() {
//    return "createUser";
//  }
//
//  @PostMapping(value = "createUser")
//  public String newUser(
//      @RequestParam(name = "id") String id,
//      @RequestParam(name = "name") String name,
//      @RequestParam(name = "lastname") String lastname,
//      @RequestParam(name = "email") String email,
//      Model model) {
//    model.addAttribute("name", name);
//    userServiceDaoImpl.saveUser(id, name, lastname, email);
//    return "redirect:/api/index";
//  }
//}
