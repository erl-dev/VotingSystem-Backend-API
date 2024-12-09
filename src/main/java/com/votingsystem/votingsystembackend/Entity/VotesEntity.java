package com.votingsystem.votingsystembackend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@Getter
@Setter
public class VotesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id", nullable = false)
    private CandidateEntity candidateEntity;

    @ManyToOne
    @JoinColumn(name = "election_id", referencedColumnName = "election_id", nullable = false)
    private ElectionEntity electionEntity;

    @Column(name = "vote_date", nullable = false)
    private LocalDateTime voteDate;
}
