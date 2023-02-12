package com.susu.projectthestyle.repository;

import com.susu.projectthestyle.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
