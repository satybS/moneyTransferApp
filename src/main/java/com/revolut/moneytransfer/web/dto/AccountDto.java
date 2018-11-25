package com.revolut.moneytransfer.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @NotNull(message = "id can't be null")
    private Long id;

    @NotNull(message = "balance can't be null")
    private BigDecimal balance;

    @NotNull(message = "customer id can't be null")
    @JsonProperty("customer_id")
    private Long customerId;


}
