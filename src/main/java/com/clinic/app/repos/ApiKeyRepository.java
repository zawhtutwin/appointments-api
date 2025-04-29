package com.clinic.app.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.app.models.ApiKey;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, String> {
    Optional<ApiKey> findByKey(String key);
}