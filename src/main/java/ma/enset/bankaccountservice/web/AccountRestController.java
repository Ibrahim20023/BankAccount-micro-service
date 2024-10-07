package ma.enset.bankaccountservice.web;

import lombok.AllArgsConstructor;
import ma.enset.bankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountservice.entities.BankAccount;
import ma.enset.bankaccountservice.repositories.BankAccountRepository;
import ma.enset.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;

    @GetMapping("/bankAccounts/{id}")
    public BankAccount getBankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found", id)));
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> getAllAccounts(){
        return bankAccountRepository.findAll();
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        BankAccount bankAccount1 = bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance() != null) bankAccount1.setBalance(bankAccount.getBalance());
        if (bankAccount.getCurrency() != null) bankAccount1.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getCreatedAt() != null) bankAccount1.setCreatedAt(new Date());
        if (bankAccount.getType() != null) bankAccount1.setType(bankAccount.getType());
        return bankAccountRepository.save(bankAccount1);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
