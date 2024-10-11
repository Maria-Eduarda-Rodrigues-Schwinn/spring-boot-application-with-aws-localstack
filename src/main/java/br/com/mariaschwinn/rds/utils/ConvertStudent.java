package br.com.mariaschwinn.rds.utils;

import br.com.mariaschwinn.dto.StudentRequest;
import br.com.mariaschwinn.dto.StudentResponse;
import br.com.mariaschwinn.rds.entity.Student;

public class ConvertStudent {

    public static Student toStudent(StudentRequest studentRequest) {

        Student student = new Student();

        student.setId(studentRequest.getIdStudent());
        student.setName(studentRequest.getName());
        student.setCpf(studentRequest.getCpf());
        student.setRg(studentRequest.getRg());
        student.setBirthDate(studentRequest.getBirthDate());
        student.setAddress(studentRequest.getAddress());
        student.setCep(studentRequest.getCep());
        student.setCity(studentRequest.getCity());
        student.setState(studentRequest.getState());

        return student;
    }

    public static StudentResponse toStudentResponse(Student student) {

        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setIdStudent(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setCpf(student.getCpf());
        studentResponse.setRg(student.getRg());
        studentResponse.setBirthDate(student.getBirthDate());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setCep(student.getCep());
        studentResponse.setCity(student.getCity());
        studentResponse.setState(studentResponse.getState());

        return studentResponse;
    }
}
