package br.com.mariaschwinn.service;

import br.com.mariaschwinn.dto.StudentRequest;
import br.com.mariaschwinn.dto.StudentResponse;
import br.com.mariaschwinn.entity.Student;
import br.com.mariaschwinn.exception.StudentException;
import br.com.mariaschwinn.rds.utils.ConvertStudent;
import br.com.mariaschwinn.repository.RdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RdsService {

    private static final String STUDENT_NOT_FOUND = "Aluno não encontrado";

    @Autowired
    private RdsRepository rdsRepository;

    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student newStudent = ConvertStudent.toStudent(studentRequest);

        return ConvertStudent.toStudentResponse(rdsRepository.save(newStudent));
    }

    public StudentResponse updateStudent(StudentRequest studentRequest) {
        if (rdsRepository.findById(studentRequest.getIdStudent()).isEmpty())
            throw new StudentException(STUDENT_NOT_FOUND);

        Student newStudent = ConvertStudent.toStudent(studentRequest);
        return ConvertStudent.toStudentResponse(rdsRepository.save(newStudent));
    }

    public List<StudentResponse> searchStudent(String name) {
        return rdsRepository.findByNameContainingIgnoreCase(name)
                .stream().map(student -> ConvertStudent.toStudentResponse(rdsRepository.save(student)))
                .collect(Collectors.toList());
    }

    public StudentResponse deleteStudent(Integer studentId) {
        if (rdsRepository.findById(studentId).isEmpty())
            throw new StudentException(STUDENT_NOT_FOUND);

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setIdStudent(studentId);

        rdsRepository.deleteById(studentId);

        return studentResponse;
    }
}
