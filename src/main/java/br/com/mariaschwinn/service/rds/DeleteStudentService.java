package br.com.mariaschwinn.service.rds;

import br.com.mariaschwinn.dto.StudentResponse;
import br.com.mariaschwinn.exception.StudentException;
import br.com.mariaschwinn.repository.RdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteStudentService {

    private static final String STUDENT_NOT_FOUND = "Aluno não encontrado";

    @Autowired
    private RdsRepository rdsRepository;

    public StudentResponse delete(Integer studentId) {
        if (rdsRepository.findById(studentId).isEmpty())
            throw new StudentException(STUDENT_NOT_FOUND);

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setIdStudent(studentId);
        rdsRepository.deleteById(studentId);

        return studentResponse;
    }
}
