package Repo;

import Model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public UserRepository() {
        // Initializing with some sample users
        users.put("adithya@oksbi", new User("Adithya Mode", "adithya@oksbi", 5000));
        users.put("agneesh@oksbi", new User("Agneesh Dasgupta", "agneesh@oksbi", 8000));
        users.put("deepak@oksbi", new User("Deepak Reddy", "deepak@oksbi", 10000));
        users.put("aishveen@oksbi", new User("Aishveen Kaur", "aishveen@oksbi", 12000));
        users.put("hasini@oksbi", new User("Hasini", "hasini@oksbi", 15000));
    }

    public User findUserByUpiId(String upiId) {
        return users.get(upiId);
    }
}
