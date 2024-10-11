package br.com.mariaschwinn.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"idStudent", "name", "cpf", "rg", "birth_date", "address", "cep", "city", "state", "course"})
public class StudentResponse extends StudentRequest {

}
