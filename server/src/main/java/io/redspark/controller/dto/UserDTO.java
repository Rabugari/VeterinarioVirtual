package io.redspark.controller.dto;

import io.redspark.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	private Long id;
	private String name;
	private Boolean admin;

	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.admin = user.getAdmin();
	}

	//retirar get e set? FIXME
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}