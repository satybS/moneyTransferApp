package com.revolut.moneytransfer.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @JsonProperty("from_account")
    @NotNull(message = "from_account field can't be null")
    private Long fromAccount;

    @JsonProperty("to_account")
    @NotNull(message = "to_account field can't be null")
    private Long toAccount;

    @JsonProperty("amount")
    @DecimalMin(value= "0", inclusive = false, message = "value must be larger than 0")
    private BigDecimal amount;
}
