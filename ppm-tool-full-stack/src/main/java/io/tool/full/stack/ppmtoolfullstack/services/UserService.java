package io.tool.full.stack.ppmtoolfullstack.services;

import io.tool.full.stack.ppmtoolfullstack.domain.User;
import io.tool.full.stack.ppmtoolfullstack.exceptions.UsernameAlreadyExistsException;
import io.tool.full.stack.ppmtoolfullstack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author badrikant.soni on 16/02/19
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {

        try {
            // Encode the password
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            // username has to be unique, if not, throw an exception

            // Make sure that password and confirm password should match, else throw an exception

            // don't persist or show the confirm password

            return userRepository.save(newUser);

        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
        }
    }
}
