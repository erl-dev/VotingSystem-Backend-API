package com.votingsystem.votingsystembackend.Repository;

import com.votingsystem.votingsystembackend.Entity.ElectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ElectionRepository extends JpaRepository<ElectionEntity, Integer> {
    Optional<ElectionEntity> findByElectionName(String electionName);
}