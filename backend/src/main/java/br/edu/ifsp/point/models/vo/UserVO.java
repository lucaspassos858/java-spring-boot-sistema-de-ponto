package br.edu.ifsp.point.models.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;
import com.github.dozermapper.core.Mapping;

@Getter
@Setter
@SuppressWarnings("rawtypes")
public class UserVO extends RepresentationModel implements Serializable {
	
	private static final long serialVersionUID= 1L;
	
	@Mapping("id")
	private Long key;

    private String nome;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    
    public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
