package com.hammed.loanmanagement.repository;




import com.hammed.loanmanagement.models.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanApplication, Integer> {
    List<LoanApplication> findByUserId(Integer userId);

    // You can add custom query methods if needed, for example:
    // LoanApplication findByUserId(Integer userId);
}

