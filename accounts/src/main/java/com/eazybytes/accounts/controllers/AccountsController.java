package com.eazybytes.accounts.controllers;

import com.eazybytes.accounts.constants.AccountsConstant;
import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.services.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for accounts in easyBank",
        description = "CRUD Rest APIs in easyBank for create, update, delete and fetch account details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
public class AccountsController {

    private final IAccountsService accountsService;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Autowired
    public AccountsController(IAccountsService iAccountsService){
        this.accountsService = iAccountsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Create Account REST API",
            description = "Create Account REST API is used to create account in easyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status 201 CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status 500 INTERNAL SERVER ERROR"
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstant.STATUS_201, AccountsConstant.MESSAGE_201));
    }
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "^[1-9]{1}[0-9]{9}$",message = "Mobile number is not valid")
                                                               String mobileNumber){
        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(isUpdated ? AccountsConstant.STATUS_200 : AccountsConstant.STATUS_500, isUpdated ? AccountsConstant.MESSAGE_200 : AccountsConstant.MESSAGE_500));

    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto>deleteAccountDetails(@RequestParam
                                                               @Pattern(regexp = "^[1-9]{1}[0-9]{9}$",message = "Mobile number is not valid")
                                                               String mobileNumber){
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(isDeleted ? AccountsConstant.STATUS_200 : AccountsConstant.STATUS_500, isDeleted ? AccountsConstant.MESSAGE_200 : AccountsConstant.MESSAGE_500));
    }

    @Operation(
            summary = "Get Build Version Number",
            description = "Get Build version REST API is used to get build version number in easyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status 201 CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status 500 INTERNAL SERVER ERROR"
            )
    })
    @GetMapping("/buildVersion")
    public ResponseEntity<String>getBuildVersion(){
        return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(buildVersion);
    }

    @GetMapping("/javaVersion")
    public ResponseEntity<String>getJavaVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto>getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}
