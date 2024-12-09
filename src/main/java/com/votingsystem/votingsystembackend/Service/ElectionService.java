package com.votingsystem.votingsystembackend.Service;


import com.votingsystem.votingsystembackend.DTO.ElectionReq;

public interface ElectionService {
    void addElection(ElectionReq electionReq);
}
