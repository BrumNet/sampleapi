package com.vts.sampleapi.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository< Student, Long > {

    @Query("SELECT s from Student s WHERE s.email = ?1")
    <Optional> Student findStudentsByEmail(String email);
}
