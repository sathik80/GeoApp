package com.exam.service;

import java.util.List;

import com.exam.model.User;

public interface IService {

  public User[] getLondonUsers();

  public User[] getAllUsers();

  public User[] getLondonWithInFiftyMilesUsers();
}
