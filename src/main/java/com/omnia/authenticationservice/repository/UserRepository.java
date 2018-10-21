package com.omnia.authenticationservice.repository;


import com.omnia.authenticationservice.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);

}
