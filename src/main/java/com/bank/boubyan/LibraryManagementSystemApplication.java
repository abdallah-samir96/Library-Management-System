package com.bank.boubyan;

import com.bank.boubyan.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementSystemApplication {
    public static void main(String [] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class);
    }
}
