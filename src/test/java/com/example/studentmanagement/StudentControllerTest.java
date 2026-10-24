package com.example.studentmanagement;

import com.example.studentmanagement.controller.StudentController;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setId(1);
        student.setNom("Mido");
        when(studentService.save(any(Student.class))).thenReturn(student);

        ResponseEntity<Student> response = studentController.save(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Mido", response.getBody().getNom());
        verify(studentService, times(1)).save(student);
    }

    @Test
    void testDeleteStudent() {
        when(studentService.delete(1)).thenReturn(true);

        ResponseEntity<Void> response = studentController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(studentService, times(1)).delete(1);
    }

    @Test
    void testDeleteStudentNotFound() {
        when(studentService.delete(999)).thenReturn(false);

        ResponseEntity<Void> response = studentController.delete(999);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(studentService, times(1)).delete(999);
    }

    @Test
    void testFindAllStudents() {
        Student student1 = new Student();
        Student student2 = new Student();
        when(studentService.findAll()).thenReturn(Arrays.asList(student1, student2));

        ResponseEntity<List<Student>> response = studentController.findAllStudent();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testCountStudents() {
        when(studentService.countStudents()).thenReturn(10L);

        ResponseEntity<Long> response = studentController.countStudent();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(10L, response.getBody());
    }

    @Test
    void testFindByYear() {
        when(studentService.findNbrStudentByYear()).thenReturn(Collections.emptyList());

        ResponseEntity<Collection<?>> response = studentController.findByYear();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }
}
