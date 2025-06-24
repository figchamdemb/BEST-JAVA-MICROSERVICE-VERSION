package com.egov.repository;

import com.egov.entity.Role;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
    boolean existsByRoleName(String roleName);
    Set<Role> findByRoleNameIn(List<String> roleNames);
}
