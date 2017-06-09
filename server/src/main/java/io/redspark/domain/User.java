package io.redspark.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(name = "UK_LOGIN", columnNames = "user_login") )
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{

	@GeneratedValue
	@Id
	@Column(name = "user_id")
	private Long id;

	@Column(name = "user_login", nullable = false, updatable = false)
	private String login;

	@Column(name = "user_name", nullable = false)
	private String name;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "user_admin")
	private Boolean admin;

	
	//FIXME retirar get e set?
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}
