package com.votingsystem.votingsystembackend.DTO;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
public class ElectionReq {

    @JsonAlias("ElectionName")
    private String electionName;

    @JsonAlias("StartDate")
    private LocalDate startDate;

    @JsonAlias("EndDate")
    private LocalDate endDate;
}
