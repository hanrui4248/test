package ca.uqtr.dmi.demo.controllers;

import ca.uqtr.dmi.demo.model.Student;
import ca.uqtr.dmi.demo.services.StudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/students")
@Api(value = "ActControllerAPI",
     produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/getAll/")
    public List<Student>  getAllStudents(){
        List<Student> s = studentService.getAllStudents();
        return s;
    }

    @GetMapping(path = "/get/{id}")
    public Student getStudent(@PathVariable("id") Long id){
        Optional<Student> s = studentService.getStudent(id);
        return s.orElseThrow(()->new RuntimeException("Étudiant non trouvé"));
    }
    @PostMapping(path = "/add")
    public void addStudent( @RequestBody Student student){
        studentService.saveStudent(student);
    }


}
