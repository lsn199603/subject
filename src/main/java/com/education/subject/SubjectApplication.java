package com.education.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.education.subject.mapper")

public class SubjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class, args);
    }


}
