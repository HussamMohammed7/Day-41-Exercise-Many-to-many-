package com.example.exercisejparelationi.Service;

import com.example.exercisejparelationi.Api.ApiException;
import com.example.exercisejparelationi.Model.Course;
import com.example.exercisejparelationi.Model.Teacher;
import com.example.exercisejparelationi.Repository.CourseRepository;
import com.example.exercisejparelationi.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }


    public void updateCourse(Integer id, Course course) {
        Course updateCourse = courseRepository.findById(id).orElse(null);
        if (updateCourse == null) {
            throw new ApiException("Course not found");
        }
        updateCourse.setName(course.getName());
        updateCourse.setTeacher(course.getTeacher());
        courseRepository.save(updateCourse);
    }

    public void deleteCourse(Integer id) {
        Course deleteCourse = courseRepository.findById(id).orElse(null);
        if (deleteCourse == null) {
            throw new ApiException("Course not found");
        }
        courseRepository.delete(deleteCourse);
    }

    public void assignTeacherToCourse(Integer teacher_id, Integer course_id) {
        Course course = courseRepository.findById(course_id).orElse(null);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        Teacher teacher = teacherRepository.findById(teacher_id).orElse(null);
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public String getTeacherName(Integer id) {
        Course course = courseRepository.findCourseById(id);

        if (course == null) {
            throw new ApiException("Course not found");
        }
        if (course.getTeacher() == null) {
            throw new ApiException("This course have not teacher");

        }
        return course.getTeacher().getName();
    }
}
