package com.data.session10.service;

import com.data.session10.entity.Account;
import com.data.session10.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(UUID id, Account updatedAccount) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        System.out.println("Old Account: " + existingAccount);
        existingAccount.setFullName(updatedAccount.getFullName());
        existingAccount.setPhone(updatedAccount.getPhone());
        existingAccount.setCccd(updatedAccount.getCccd());
        existingAccount.setEmail(updatedAccount.getEmail());
        existingAccount.setMoney(updatedAccount.getMoney());
        existingAccount.setStatus(updatedAccount.getStatus());
        System.out.println("Updated Account: " + existingAccount);
        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setStatus(Account.Status.INACTIVE);
        accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByCccd(String cccd) {
        return accountRepository.findByCccd(cccd)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}