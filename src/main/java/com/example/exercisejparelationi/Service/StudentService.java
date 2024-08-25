package com.example.exercisejparelationi.Service;

import com.example.exercisejparelationi.Api.ApiException;
import com.example.exercisejparelationi.Model.Course;
import com.example.exercisejparelationi.Model.Student;
import com.example.exercisejparelationi.Repository.CourseRepository;
import com.example.exercisejparelationi.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Integer id, Student student) {
        Student updateStudent = studentRepository.findStudentById(id);

        if (updateStudent == null) {
            throw new ApiException("Student not found");
        }
        updateStudent.setName(student.getName());
        updateStudent.setAge(student.getAge());
        updateStudent.setMajor(student.getMajor());
        studentRepository.save(updateStudent);
    }

    public void deleteStudent(Integer id) {
        Student deleteStudent = studentRepository.findStudentById(id);

        if (deleteStudent == null) {
            throw new ApiException("Student not found");
        }

        studentRepository.delete(deleteStudent);
    }



    public void assignCourseAndStudent(Integer courseId, Integer studentId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            throw new ApiException("Student not found");
        }
        course.getStudents().add(student);
        student.getCourses().add(course);
        courseRepository.save(course);
        studentRepository.save(student);
    }

    public void changeMajor(Integer id, String major) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("Student not found");
        }
        student.setMajor(major);

        for (Course course : student.getCourses()) {
            course.getStudents().remove(student);
        }
        studentRepository.save(student);

    }

}
