package com.email.email_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.email.email_service.entity.EmailConfiguration;

import jakarta.transaction.Transactional;

@Transactional
public interface EmailConfigurationRepository extends JpaRepository<EmailConfiguration, Long> {

	@Query(value = "SELECT * FROM email_configuration where email_token=:requestToken LIMIT 1", nativeQuery = true)
	EmailConfiguration getByEmailToken(String requestToken);

	@Query(value = "SELECT * FROM email_configuration where is_active = 1", nativeQuery = true)
	List<EmailConfiguration> findAllActiveConfig();

}
