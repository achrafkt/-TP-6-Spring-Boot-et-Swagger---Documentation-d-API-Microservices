package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
@Tag(name = "Students", description = "Gestion des étudiants")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Enregistrer un nouvel étudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Étudiant enregistré avec succès")
    })
    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @Operation(summary = "Supprimer un étudiant par son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Étudiant supprimé"),
            @ApiResponse(responseCode = "404", description = "Étudiant introuvable")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant de l'étudiant à supprimer", required = true, example = "1")
            @PathVariable("id") int id) {
        boolean deleted = studentService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Lister tous les étudiants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des étudiants")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAllStudent() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Operation(summary = "Compter le nombre d'étudiants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre total d'étudiants")
    })
    @GetMapping("/count")
    public ResponseEntity<Long> countStudent() {
        long count = studentService.countStudents();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @Operation(summary = "Nombre d'étudiants par année de naissance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste de paires [année, nombre]")
    })
    @GetMapping("/byYear")
    public ResponseEntity<Collection<?>> findByYear() {
        Collection<?> studentsByYear = studentService.findNbrStudentByYear();
        return new ResponseEntity<>(studentsByYear, HttpStatus.OK);
    }
}
