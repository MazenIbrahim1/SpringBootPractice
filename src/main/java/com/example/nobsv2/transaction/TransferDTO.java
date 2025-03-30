package com.example.nobsv2.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {
    private String fromUser;
    private String toUser;
    private Double amount;
}
