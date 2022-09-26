package com.brq.projeto1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* Classe contendo os parâmetros para a criação de Usuário na banco de dados
* @author WGomes
* @since release 1.0
*/
@Entity
@Table(name = "usuario")
@Validated
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Parâmetros que devem ser utilizados para criacão do Usuário
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotNull(message= "Nome é obrigatorio" )
	@NotBlank(message = "O nome deve ser informado")
	@NotEmpty(message = "O nome não pode ser vazio")
	@Pattern(regexp = "[a-zA-Z\\s]+", message = "O nome não pode ter caracteres especiais")
	@JsonProperty("nome")
	private String name;

	@NotNull(message = "O campo 'email' deve ser informado")
	@NotBlank(message = "O email deve ser informado")
	@NotEmpty(message = "O email não pode ser vazio")
	@JsonProperty("email")
	private String email;

	@NotNull(message = "O campo 'telefone' deve ser informado")
	@NotBlank(message = "O telefone deve ser informado")
	@NotEmpty(message = "O telefone não pode ser vazio")
	@JsonProperty("telefone")
	private String phone;

	@NotNull(message = "O campo 'senha' deve ser informado")
	@NotBlank(message = "A senha deve ser informada")
	@NotEmpty(message = "A senha não pode ser vazia")
	@JsonProperty("senha")
	private String password;

	public User() {

	}

	/**
	 * Sobrecarga dos Parâmetros que devem ser utilizados para criação de novo usuário
	 * @param id
	 * @param name
	 * @param email
	 * @param phone
	 * @param password
	 */
	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.userId = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	/**
	 * Sobrecarga dos Parâmetros que devem ser utilizados para criação de novo usuário
	 * @param name
	 * @param email
	 * @param phone
	 * @param password
	 */
	public User(String name, String email, String phone, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	/**
	 * Getters e Setters dos parâmetros
	 * @return
	 */
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * Método "interno" do Java que cria um identificador para cada entidade User criada
	 * @return
	 */
	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}
	/**
	 * Método de validação para cada entidade User criada na base de dados
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return userId == other.userId;
	}
}
