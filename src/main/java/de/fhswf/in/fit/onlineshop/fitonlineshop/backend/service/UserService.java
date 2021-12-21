package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.service;

import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.entities.User;
import de.fhswf.in.fit.onlineshop.fitonlineshop.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Der UserService implementiert die Business-Logic für die Benutzer.
 *
 * @author Ivonne Kneißig & Ramon Günther
 */

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.getById(id);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
