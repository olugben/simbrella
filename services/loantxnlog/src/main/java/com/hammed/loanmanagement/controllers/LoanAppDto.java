package com.hammed.loanmanagement.controllers;



import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanAppDto {
    private Integer userId;
    private BigDecimal amount;
    private Integer tenureMonths;

    private String status;
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;

    // Getters
    public Integer getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getTenureMonths() {
        return tenureMonths;
    }



    public String getStatus() {
        return status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTenureMonths(Integer tenureMonths) {
        this.tenureMonths = tenureMonths;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
