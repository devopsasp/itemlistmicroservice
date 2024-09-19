package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Optional: Add custom query methods or other repository-specific logic here
}