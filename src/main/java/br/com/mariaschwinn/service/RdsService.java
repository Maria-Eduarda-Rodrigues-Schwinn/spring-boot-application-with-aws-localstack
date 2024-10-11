package br.com.mariaschwinn.service;

import br.com.mariaschwinn.dto.StudentRequest;
import br.com.mariaschwinn.dto.StudentResponse;
import br.com.mariaschwinn.service.rds.CreateStudentService;
import br.com.mariaschwinn.service.rds.DeleteStudentService;
import br.com.mariaschwinn.service.rds.SearchStudentService;
import br.com.mariaschwinn.service.rds.UpdateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RdsService {

    @Autowired
    private CreateStudentService createStudentService;

    @Autowired
    private UpdateStudentService updateStudentService;

    @Autowired
    private SearchStudentService searchStudentService;

    @Autowired
    private DeleteStudentService deleteStudentService;

    public StudentResponse createStudent(StudentRequest studentRequest) {
        return createStudentService.create(studentRequest);
    }

    public StudentResponse updateStudent(StudentRequest studentRequest) {
        return updateStudentService.update(studentRequest);
    }

    public List<StudentResponse> searchStudent(String name) {
        return searchStudentService.search(name);
    }

    public StudentResponse deleteStudent(Integer studentId) {
        return deleteStudentService.delete(studentId);
    }
}
