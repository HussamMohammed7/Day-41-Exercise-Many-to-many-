package com.example.exercisejparelationi.Controller;

import com.example.exercisejparelationi.Model.Student;
import com.example.exercisejparelationi.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body("Student added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@Valid @RequestBody Student student, @PathVariable Integer id) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.OK).body("Student updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
    }

    @PutMapping("/change-major/{id}")
    public ResponseEntity changeMajor(@PathVariable Integer id, @RequestParam String major) {
        studentService.changeMajor(id, major);
        return ResponseEntity.status(HttpStatus.OK).body("Student major changed successfully");
    }

    @PutMapping("/assign/{courseId}/{studentId}")
    public ResponseEntity assignCourseAndStudent(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        studentService.assignCourseAndStudent(courseId, studentId);
        return ResponseEntity.status(HttpStatus.OK).body("Course assigned to student successfully");
    }


}
