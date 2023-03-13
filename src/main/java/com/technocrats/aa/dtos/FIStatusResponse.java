package com.technocrats.aa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FIStatusResponse {

    public String fipID;

    public List<Account> Accounts;

}
