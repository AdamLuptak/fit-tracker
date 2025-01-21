package io.rebase.fitnessapp.usermanagement.infrastructure.repository;

import io.rebase.fitnessapp.usermanagement.domain.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepository {

  private final Map<String, User> users = new HashMap<>();

  public void save(User user) {
    users.put(user.id(), user);
  }

  public Optional<User> findById(String id) {
    return Optional.ofNullable(users.get(id));
  }

  public List<User> findAll() {
    return users.values().stream().toList();
  }
}
