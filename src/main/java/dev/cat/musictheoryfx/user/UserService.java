package dev.cat.musictheoryfx.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public void saveUser(String username) {
        User newUser = new User();
        newUser.setUserName(username);
        userRepository.save(newUser);
    }

    public void updateUser(User updatedUser) {
        userRepository.save(updatedUser);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
