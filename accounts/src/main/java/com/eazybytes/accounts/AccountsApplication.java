package com.eazybytes.accounts;

import com.eazybytes.accounts.audit.AuditAwareImpl;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.*;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				version = "v1",
				description = "Accounts microservice REST API Documentation",
				contact = @Contact(
						name = "Deepanshu Katiyar",
						email = "tY8Q2@example.com"
		               ),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Accounts microservice REST API Documentation",
				url = "https://github.com/deepanshu-katiyar/accounts"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
		boolean[] booleans = sieveOfEratoSthenes(12);
		for(int i = 0; i<12; i++){
			System.out.println(i+" "+booleans[i]);
		}
	}

	public static boolean[] sieveOfEratoSthenes(int n) {
		boolean[] isPrime = new boolean[n+1];
		Arrays.fill(isPrime,true);
		isPrime[0] = false;
		isPrime[1] = false;
		for(int i = 2; i*i<=n; i++){
			for(int j = 2*i; j<=n; j+=i){
				isPrime[j] = false;
			}
		}
		return isPrime;
	}

	@Bean
	public AuditorAware<String> auditAwareImpl() {
		return new AuditAwareImpl();
	}
}
