package com.example.getrepositories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"GithubAPIConsumer", "controllers"})
public class GetRepositoriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetRepositoriesApplication.class, args);
    }

}
