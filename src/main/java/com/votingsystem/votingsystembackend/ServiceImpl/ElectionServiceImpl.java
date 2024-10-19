package com.votingsystem.votingsystembackend.ServiceImpl;

import com.votingsystem.votingsystembackend.DTO.ElectionReq;
import com.votingsystem.votingsystembackend.Entity.ElectionEntity;
import com.votingsystem.votingsystembackend.Repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionServiceImpl {
    @Autowired
    private ElectionRepository electionRepository;

    public void addElection (ElectionReq electionReq){
        ElectionEntity electionEntity = new ElectionEntity();

        electionEntity.setElectionName(electionReq.getElectionName());
        electionEntity.setStartDate(electionReq.getStartDate());
        electionEntity.setEndDate(electionReq.getEndDate());

        electionRepository.save(electionEntity);
    }

    public List<ElectionEntity> getAllElections() {
        return electionRepository.findAll();
    }

    public Optional<ElectionEntity> getElectionById(int electionId) {
        return electionRepository.findById(electionId);
    }

    public void deleteElection(int electionId) {
        // Check if the election exists before deleting
        Optional<ElectionEntity> electionOptional = electionRepository.findById(electionId);
        if (electionOptional.isPresent()) {
            electionRepository.deleteById(electionId);
        } else {
            throw new RuntimeException("Election not found with ID: " + electionId);
        }
    }

}
