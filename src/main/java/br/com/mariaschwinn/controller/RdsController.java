package br.com.mariaschwinn.controller;

import br.com.mariaschwinn.dto.StudentRequest;
import br.com.mariaschwinn.dto.StudentResponse;
import br.com.mariaschwinn.service.RdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RdsController {

    @Autowired
    private RdsService rdsService;

    @GetMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentResponse>> searchStudent(
            @RequestParam(value = "name") String name) {
        return ResponseEntity.ok(rdsService.searchStudent(name));
    }

    @PostMapping(value = "/student",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(rdsService.createStudent(studentRequest));
    }

    @PutMapping(value = "/student",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponse> updateStudent(@RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(rdsService.updateStudent(studentRequest));
    }

    @DeleteMapping(value = "/student/{student_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponse> deleteStudent(@PathVariable(value = "student_id") Integer studentId) {
        return ResponseEntity.ok(rdsService.deleteStudent(studentId));
    }
}
