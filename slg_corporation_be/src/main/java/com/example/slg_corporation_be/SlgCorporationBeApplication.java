package com.example.slg_corporation_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class SlgCorporationBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlgCorporationBeApplication.class, args);
//		        String a = "vu123456";
//        String b = BCrypt.hashpw(a, BCrypt.gensalt(12));
//        System.out.println(b);
	}

}
