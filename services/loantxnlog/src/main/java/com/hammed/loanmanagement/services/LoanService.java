package com.hammed.loanmanagement.services;


import com.hammed.loanmanagement.controllers.LoanAppDto;
import com.hammed.loanmanagement.models.LoanApplication;


import com.hammed.loanmanagement.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


@Service
public class LoanService {
    BigDecimal loanAmount = new BigDecimal("5000");
    BigDecimal tenureYears = new BigDecimal("2");

    private static final Logger logger = Logger.getLogger(LoanService.class.getName());
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
        setupLogger();
    }
    private void setupLogger() {
        try {
            // Set up the file handler to log to a file
            FileHandler fileHandler = new FileHandler("loan_applications.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("Error setting up the file handler for logger: " + e.getMessage());
        }
    }

    public String applyForLoan(Integer userId, LoanAppDto loanAppDto) {
        System.out.println(loanAppDto + "your loan app");
        if (loanAppDto.getAmount() == null || loanAppDto.getTenureMonths() == null ||
                 loanAppDto.getStatus() == null) {
            throw new IllegalArgumentException("Mandatory fields are missing in the loan application");
        }
        BigDecimal interestRate = loanAmount.multiply(tenureYears)
                .divide(new BigDecimal("10000"), 6, RoundingMode.HALF_UP);



        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setUserId(loanAppDto.getUserId());

        loanApplication.setAmount(loanAppDto.getAmount());
        loanApplication.setTenureMonths(loanAppDto.getTenureMonths());
        loanApplication.setInterestRate(interestRate);
        loanApplication.setStatus(loanAppDto.getStatus());
        loanApplication.setAppliedAt(loanAppDto.getAppliedAt());
        loanApplication.setUpdatedAt(loanAppDto.getUpdatedAt() != null ? loanAppDto.getUpdatedAt() : LocalDateTime.now()); System.out.println("Received Loan Application: " + loanApplication);
        System.out.println(loanApplication +"hjdfhhfs");
        loanRepository.save(loanApplication);
        logLoanApplicationDetails(loanApplication);
        return "Loan application successfully submitted";
    }
    public List<LoanApplication> getLoanDetailsByUserId(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        List<LoanApplication> loanApplications = loanRepository.findByUserId(userId);

        if (loanApplications.isEmpty()) {
            throw new RuntimeException("No loan applications found for the given User ID");
        }

        return loanApplications;
    }


    private void logLoanApplicationDetails(LoanApplication loanApplication) {
        // Log the loan application details into the log file
        String logMessage = String.format("Loan Application: [User ID: %d, Amount: %.2f, Tenure: %d months, " +
                        "Interest Rate: %.6f, Status: %s, Applied At: %s, Updated At: %s]",
                loanApplication.getUserId(),
                loanApplication.getAmount(),
                loanApplication.getTenureMonths(),
                loanApplication.getInterestRate(),
                loanApplication.getStatus(),
                loanApplication.getAppliedAt(),
                loanApplication.getUpdatedAt());

        logger.info(logMessage); // Log the message
    }
    /**
     * Retrieves the status of a loan application.
     *
     * @param loanId The ID of the loan.
     * @return The loan status or an error message if not found.
     */
    public String getLoanStatus(Integer loanId) {
        Optional<LoanApplication> loanApplication = loanRepository.findById(loanId);
        if (loanApplication.isEmpty()) {
            return "Loan application not found";
        }
        return "Loan status for loan ID " + loanId + ": " ;
    }

}
