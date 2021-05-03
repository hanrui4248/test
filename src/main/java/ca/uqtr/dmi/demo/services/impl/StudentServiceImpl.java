package ca.uqtr.dmi.demo.services.impl;

import ca.uqtr.dmi.demo.model.Student;
import ca.uqtr.dmi.demo.repository.StudentRepository;
import ca.uqtr.dmi.demo.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepo;

    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }


    @Override
    public Optional<Student> getStudent(Long id) {

       return studentRepo.findById(id);
    }

    @Override
    public Optional<List<Student>> getStudents(Long... id) {
        return Optional.empty();
    }

    @Override
    public void saveStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return StreamSupport
                .stream(studentRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


}
