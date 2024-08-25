package com.example.exercisejparelationi.Controller;

import com.example.exercisejparelationi.Model.Course;
import com.example.exercisejparelationi.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(
                courseService.getAllCourses()
        );
    }
    @GetMapping("/get-name/{id}")
    public ResponseEntity getTeacherNameByCourse(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
                courseService.getTeacherName(id
                )
        );
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody Course course) {
        courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Course added successfully"
        );
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@Valid @RequestBody Course course, @PathVariable Integer id) {
        courseService.updateCourse(id, course);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Course updated successfully"
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Course deleted successfully"
        );
    }

    @PutMapping("/assign/{teacher_id}/{course_id}")
    public ResponseEntity assignTeacherToCourse(@PathVariable Integer teacher_id, @PathVariable Integer course_id) {
        courseService.assignTeacherToCourse(teacher_id, course_id);
        return ResponseEntity.status(HttpStatus.OK).body(
                "Teacher assigned to course successfully"
        );
    }
}
