package com.hammed.loanmanagement.controllers;

import com.hammed.loanmanagement.models.LoanApplication;
import com.hammed.loanmanagement.services.LoanService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")

public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public String applyForLoan( Integer username, @RequestBody LoanAppDto loanAppDto) {

        return loanService.applyForLoan(username, loanAppDto);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<LoanApplication>> getLoanDetailsByUserId(@PathVariable Integer userId) {
        try {
            List<LoanApplication> loanApplications = loanService.getLoanDetailsByUserId(userId);
            return ResponseEntity.ok(loanApplications);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
    @GetMapping("/status/{loanId}")
    public String getLoanStatus(@PathVariable Integer loanId) {
        return loanService.getLoanStatus(loanId);
    }
}
