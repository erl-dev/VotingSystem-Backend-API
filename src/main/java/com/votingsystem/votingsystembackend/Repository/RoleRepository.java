package com.votingsystem.votingsystembackend.Repository;

import com.votingsystem.votingsystembackend.Entity.ElectionEntity;
import com.votingsystem.votingsystembackend.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findById(int roleId);
}
