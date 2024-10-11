package br.com.mariaschwinn.service.rds;

import br.com.mariaschwinn.dto.StudentRequest;
import br.com.mariaschwinn.dto.StudentResponse;
import br.com.mariaschwinn.entity.Student;
import br.com.mariaschwinn.exception.StudentException;
import br.com.mariaschwinn.repository.RdsRepository;
import br.com.mariaschwinn.utils.ConvertStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateStudentService {

    private static final String STUDENT_NOT_FOUND = "Aluno não encontrado";

    @Autowired
    private RdsRepository rdsRepository;

    public StudentResponse update(StudentRequest studentRequest) {

        if (rdsRepository.findById(studentRequest.getIdStudent()).isEmpty())
            throw new StudentException(STUDENT_NOT_FOUND);

        Student updatedStudent = ConvertStudent.toStudent(studentRequest);

        return ConvertStudent.toStudentResponse(rdsRepository.save(updatedStudent));
    }
}
