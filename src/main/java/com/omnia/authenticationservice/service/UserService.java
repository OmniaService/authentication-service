package com.omnia.authenticationservice.service;

import com.omnia.authenticationservice.exceptions.AlreadyExistsException;
import com.omnia.authenticationservice.exceptions.NotFoundException;
import com.omnia.authenticationservice.model.UserEntity;
import com.omnia.authenticationservice.repository.UserRepository;
import com.omnia.authenticationservice.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserEntity saveUser(UserEntity user) {
        Validator.validateUser(user);
        user.setPassword(encoder.encode(user.getPassword()));
        if (!repository.existsByUsername(user.getUsername())) {
            return repository.save(user);
        } else {
            throw new AlreadyExistsException("User with username: " + user.getUsername() + " already taken!");
        }
    }

    public Collection<UserEntity> findAll() {
        return repository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new NotFoundException("User with username: " + username + " not found"));
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
