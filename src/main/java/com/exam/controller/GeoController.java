package com.exam.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.User;
import com.exam.service.IService;

@RestController
public class GeoController {

  @Autowired
  private IService service;

  @GetMapping("/allUsers")
  public User[] getUsers() {
    return service.getAllUsers();
  }


  @GetMapping("/londonUsers")
  public User[] getLondonUsers() {
    return service.getLondonWithInFiftyMilesUsers();
  }


}
