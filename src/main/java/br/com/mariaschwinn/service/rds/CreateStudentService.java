package br.com.mariaschwinn.service.rds;

import br.com.mariaschwinn.dto.StudentRequest;
import br.com.mariaschwinn.dto.StudentResponse;
import br.com.mariaschwinn.entity.Student;
import br.com.mariaschwinn.repository.RdsRepository;
import br.com.mariaschwinn.utils.ConvertStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentService {

    @Autowired
    private RdsRepository rdsRepository;

    public StudentResponse create(StudentRequest studentRequest) {
        Student newStudent = ConvertStudent.toStudent(studentRequest);

        return ConvertStudent.toStudentResponse(rdsRepository.save(newStudent));
    }
}
