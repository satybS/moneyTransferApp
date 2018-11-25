package com.revolut.moneytransfer.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Data
public class CustomerDto {

    public CustomerDto(@NotNull Long customerId, @NotNull String firstName, @NotNull String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonProperty("customer_id")
    @NotNull
    private Long customerId;

    @JsonProperty("first_name")
    @NotNull
    private String firstName;

    @JsonProperty("last_name")
    @NotNull
    private String lastName;

    @JsonProperty("account_ids")
    private List<Long> accountIds;
}
