package com.votingsystem.votingsystembackend.Controller;

import com.votingsystem.votingsystembackend.DTO.ElectionReq;
import com.votingsystem.votingsystembackend.Entity.ElectionEntity;
import com.votingsystem.votingsystembackend.ServiceImpl.ElectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/election")
public class ElectionController {

    @Autowired
    private ElectionServiceImpl electionService;

    @PostMapping("/add")
    public ResponseEntity<String> saveElection(@RequestBody ElectionReq electionReq) {

        try {
            electionService.addElection(electionReq);
            return ResponseEntity.ok("New Election Added!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/retrieveAll")
    public ResponseEntity<List<ElectionEntity>> getAllElections() {
        List<ElectionEntity> elections = electionService.getAllElections();
        return ResponseEntity.ok(elections);
    }

    @GetMapping("/getElectionId/{electionId}")
    public ResponseEntity<ElectionEntity> getElectionById(@PathVariable int electionId) {
        Optional<ElectionEntity> electionOptional = electionService.getElectionById(electionId);
        return electionOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null));
    }

    @DeleteMapping("/delete/{electionId}")
    public ResponseEntity<String> deleteElection(@PathVariable int electionId) {
        try {
            electionService.deleteElection(electionId);
            return ResponseEntity.ok("Election deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
