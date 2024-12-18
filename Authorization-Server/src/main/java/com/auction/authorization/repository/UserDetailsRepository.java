package com.auction.authorization.repository;

import com.auction.authorization.entity.UserCredentialDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserCredentialDetails, Long> {

    Optional<UserCredentialDetails> findByUserName(String userName);
}
