package com.vts.sampleapi.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository rep){
        return args -> {
            Student one = new Student(
                    "mimi",
                    LocalDate.of(2000, Month.JULY, 5),
                    "mimi@gmail.com"
            );
            Student two = new Student(
                            "ben",
                            LocalDate.of(1996, Month.JULY, 5),
                            "mimi@gmail.com"
                    );

            rep.saveAll(List.of(one,two));
        };
    }
}
