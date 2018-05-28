package com.gft.addressbook;

import com.gft.addressbook.core.dao.JpaCustomInterfaceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = JpaCustomInterfaceImpl.class)
public class AddressBookSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(AddressBookSpringBoot.class);
    }
}

