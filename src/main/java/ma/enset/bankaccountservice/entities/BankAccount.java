package ma.enset.bankaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bankaccountservice.enums.AccountType;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String currency;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customer customer;
}
