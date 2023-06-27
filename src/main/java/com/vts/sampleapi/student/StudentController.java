package com.vts.sampleapi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/default")
public class StudentController {
    private final StudentLogic studentLogic;

    @Autowired //dependency injection
    public StudentController(StudentLogic studentLogic){
        this.studentLogic = studentLogic;
    }

    @GetMapping
    public List<Student> getStudent (){
        return studentLogic.getStudent();
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student){
        studentLogic.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentLogic.removeStudent(studentId);
    }


    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable Long studentId,
                              @RequestParam(required = false)  String name,
                              @RequestParam(required = false) String email){
        studentLogic.updateStudent(studentId, name, email);
    }

}
