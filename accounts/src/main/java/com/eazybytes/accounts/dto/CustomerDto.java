package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name can not be null or empty")
    @Size(max = 30, min = 5,message = "Name must be between 5 to 30 characters")
    private String name;
    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email is not valid")
    private String email;
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}$",message = "Mobile number is not valid")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
