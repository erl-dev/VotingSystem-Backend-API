package com.votingsystem.votingsystembackend.Repository;

import com.votingsystem.votingsystembackend.Entity.VotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<VotesEntity, Integer> {
}
