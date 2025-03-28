package com.example.nobsv2.transaction;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Command;

import jakarta.transaction.Transactional;

@Service
@Transactional // Automatically rollsback if runtime error and saves data no need for repo.save
public class TransferService implements Command<TransferDTO, String> {

    private final BankAccountRepository bankAccountRepository;

    public TransferService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public ResponseEntity<String> execute(TransferDTO transfer) {
        // Find both bank accounts by id
        Optional<BankAccount> fromBankAccount = bankAccountRepository.findById(transfer.getFromUser());
        Optional<BankAccount> toBankAccount = bankAccountRepository.findById(transfer.getToUser());

        if(fromBankAccount.isEmpty() || toBankAccount.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        BankAccount from = fromBankAccount.get();
        BankAccount to = toBankAccount.get();

        // Handle transaction
        add(to, transfer.getAmount());
        System.out.println("After adding, before deducting: ");
        System.out.println(bankAccountRepository.findById(to.getName()));
        deduct(from, transfer.getAmount());

        return ResponseEntity.ok("Success");
    }

    private void deduct(BankAccount account, Double amount) {
        if(amount > account.getBalance()) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
    }

    private void add(BankAccount account, Double amount) {
        account.setBalance(account.getBalance() + amount);
    }
}
