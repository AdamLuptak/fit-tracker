package io.rebase.fitnessapp.usermanagement.application.service;

import io.rebase.fitnessapp.usermanagement.domain.model.User;
import io.rebase.fitnessapp.usermanagement.infrastructure.repository.UserRepository;
import java.util.List;
import java.util.Optional;

public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void save(User user) {
    userRepository.save(user);
  }

  public Optional<User> findById(String id) {
    return userRepository.findById(id);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }
}
