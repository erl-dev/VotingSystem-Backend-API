package com.votingsystem.votingsystembackend.Repository;

import com.votingsystem.votingsystembackend.Entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Integer> {
}
