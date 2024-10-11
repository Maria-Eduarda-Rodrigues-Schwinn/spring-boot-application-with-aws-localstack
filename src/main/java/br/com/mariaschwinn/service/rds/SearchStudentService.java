package br.com.mariaschwinn.service.rds;

import br.com.mariaschwinn.dto.StudentResponse;
import br.com.mariaschwinn.repository.RdsRepository;
import br.com.mariaschwinn.utils.ConvertStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchStudentService {

    @Autowired
    private RdsRepository rdsRepository;

    public List<StudentResponse> search(String name) {
        return rdsRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ConvertStudent::toStudentResponse)
                .collect(Collectors.toList());
    }
}
