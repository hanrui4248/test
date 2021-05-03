package ca.uqtr.dmi.demo.services;

import ca.uqtr.dmi.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> getStudent(Long id);
    Optional<List<Student>> getStudents(Long... id);
    void saveStudent(Student student);
    List<Student> getAllStudents();
}
