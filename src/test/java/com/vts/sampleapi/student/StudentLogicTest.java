package com.vts.sampleapi.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentLogicTest {

    @Mock
    private StudentRepository repository;
    @Mock
    private Student student;
    //private AutoCloseable autoCloseable;
    private StudentLogic underTest;

    @BeforeEach
    void mount(){
        //autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentLogic(repository);
    }

    /*@AfterEach
    void teardown() throws Exception {
        autoCloseable.close();
    }*/

    @Test
    void shouldGetStudent() {
        //given undertest

        //when
        underTest.getStudent();

        //then
        verify(repository).findAll();
    }

    @Test
    void shouldAddNewStudent() {
        //given
        Student mikey = new Student("Mike Jambos", LocalDate.of(2003, Month.APRIL, 23),"jambos43@meswithme.com");

        //when
        underTest.addNewStudent(mikey);

        //then
        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(repository).save(argumentCaptor.capture());

        Student captured = argumentCaptor.getValue();

        assertThat(captured).isEqualTo(mikey);
    }

    @Test
    void ifEmailExceptionThrows(){
        //given
        Student mikey = new Student("Mike Jambos", LocalDate.of(2003, Month.APRIL, 23),"jambos43@meswithme.com");
        given(repository.findStudentsByEmail(mikey.getEmail())).willReturn(mikey);

        //when  => add student
        //then  => error is thrown
        assertThatThrownBy(() -> underTest.addNewStudent(mikey)).isInstanceOf(IllegalStateException.class).hasMessageContaining("Existing Account");

        verify(repository, never()).save(any());
    }

    @Test
    void shouldRemoveStudent() {
        //given
        long id = 1L;
        given(repository.existsById(id)).willReturn(true);

        //when
        underTest.removeStudent(id);

        //then
        verify(repository).deleteById(id);
    }

    @Test
    void doesStudentExist(){
        //given
        long id = 1L;
        long newId = id + 1;

        //when
        //then
        assertThatThrownBy(() -> underTest.removeStudent(id)).isInstanceOf(IllegalStateException.class).hasMessageContaining("Student with id:" + id + " doesn't exist");
    }

    @Test
    void shouldThrowNoSuchIdException() {
        //given
        long id = 1L;
        String newName = "Jemima";
        String newEmail = "Jemima@gmail.com";

        //when
        //then
        assertThatThrownBy(() -> underTest.updateStudent(id, newName, newEmail)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Disabled
    void shouldFindStudent() {
        //given
        long id = 1L;
        String newName = "Jemima";
        String newEmail = "Jemima@gmail.com";
        Student mikey = new Student("Mike Jambos", LocalDate.of(2003, Month.APRIL, 23),"jambos43@meswithme.com");

        given(repository.findById(id).orElseThrow()).willReturn(mikey);

        //when
        //then
        assertThatThrownBy(() -> underTest.updateStudent(id, newName, newEmail)).doesNotThrowAnyException();
    }

    @Test
    void hasNameChanged(){
        //given
        long id = 1L;
        String newName = "Jemima";
        String newEmail = "Jemima@gmail.com";
        Student mikey = new Student("Mike Jambos", LocalDate.of(2003, Month.APRIL, 23),"jambos43@meswithme.com");

        given(repository.any().orElseThrow()).willReturn(mikey);

        //when
        underTest.updateStudent(id, newName, newEmail);

        //then
        verify(student).setName(newName);

    }

    @Test
    @Disabled
    void hasEmailChanged(){

    }
}