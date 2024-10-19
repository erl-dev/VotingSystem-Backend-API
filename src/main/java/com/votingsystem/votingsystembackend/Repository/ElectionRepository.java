package com.votingsystem.votingsystembackend.Repository;

import com.votingsystem.votingsystembackend.Entity.ElectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<ElectionEntity, Integer> {

}