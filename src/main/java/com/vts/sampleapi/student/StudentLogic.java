package com.vts.sampleapi.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Service
public class StudentLogic {

    private static StudentRepository studentRepository;

    @Autowired
    public StudentLogic(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudent (){
        //System.out.println("Exec");
        return studentRepository.findAll();
    }

    public static void addNewStudent(Student student) {

        Optional<Student> studentExists = Optional.ofNullable(studentRepository.findStudentsByEmail(student.getEmail()));

        if(studentExists.isPresent()){
            throw new IllegalStateException("Existing Account");
        }
        studentRepository.save(student);
    }

    public static void removeStudent(Long id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
        else throw new IllegalStateException("Student with id:" + id + " doesn't exist");
    }

    @Transactional
    public void updateStudent(Long id, String name, String email){
        Student student = studentRepository.findById(id).orElseThrow();

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentExists = Optional.ofNullable(studentRepository.findStudentsByEmail(student.getEmail()));

            if(!studentExists.isPresent()){
                student.setEmail(email);
            }
        }
    }


}
