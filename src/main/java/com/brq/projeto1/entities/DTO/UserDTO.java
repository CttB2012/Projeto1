package com.brq.projeto1.entities.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("telefone")
    private String phone;

}
