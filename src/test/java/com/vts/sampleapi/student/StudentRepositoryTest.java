package com.vts.sampleapi.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void cleanDB(){
        underTest.deleteAll();
    }

    @Test
    void itfindsStudentsByEmail() {
        //given
        String email = "bimbo @gmail.com";

        Student student = new Student(
                //1L,
                "bimbo",
                LocalDate.of(1994, Month.AUGUST,14),
                email
        );
        underTest.save(student);

        //when
        Student newStudent = underTest.findStudentsByEmail(email);

        //then
        assertThat(email).isEqualTo(student.getEmail());

    }
}