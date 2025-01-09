package io.codething;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> userMap;

    public UserService(){
        this.userMap = new HashMap<>();
    }

    public void addUser(User user) {
        userMap.put(user.name(), user);
    }

    public User findUser(String name) {
        return userMap.get(name);
    }

}
