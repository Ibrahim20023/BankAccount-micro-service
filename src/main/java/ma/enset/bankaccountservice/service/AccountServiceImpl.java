package ma.enset.bankaccountservice.service;

import ma.enset.bankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountservice.entities.BankAccount;
import ma.enset.bankaccountservice.mappers.AccountMapper;
import ma.enset.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    AccountMapper mapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = mapper.fromBankAccountDTO(bankAccountDTO);
        bankAccount.setId(UUID.randomUUID().toString());
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = mapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDTO;
    }
    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = mapper.fromBankAccountDTO(bankAccountDTO);
        bankAccount.setId(id);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = mapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDTO;
    }
}
