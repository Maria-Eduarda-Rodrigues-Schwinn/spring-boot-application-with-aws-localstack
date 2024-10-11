package br.com.mariaschwinn.dto;

import br.com.mariaschwinn.utils.DataDeserializer;
import br.com.mariaschwinn.utils.DataSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonPropertyOrder({"idStudent", "name", "cpf", "rg", "birth_date", "address", "cep", "city", "state", "course"})
public class StudentRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id_student")
    private Integer idStudent;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("name")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("cpf")
    private String cpf;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("rg")
    private String rg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("birth_date")
    @JsonDeserialize(using = DataDeserializer.class)
    @JsonSerialize(using = DataSerializer.class)
    private LocalDate birthDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("address")
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("cep")
    private String cep;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("city")
    private String city;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("state")
    private String state;
}
