package academy.kata.pp_3_1_4.service;

import academy.kata.pp_3_1_4.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(long id);

    User getUserByUsername(String username);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);
}
