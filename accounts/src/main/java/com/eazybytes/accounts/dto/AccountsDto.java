package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
    name = "Accounts",
    description = "Schema to hold Account details"
)
public class AccountsDto {

    @Schema(description = "Account number", example = "123456789")
    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}$",message = "Account number is not valid")
    private Long accountNumber;
    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;
    @NotEmpty(message = "Branch address can not be null or empty")
    private String branchAddress;
}

