package site.metacoding.red.domain.users;



import java.sql.Timestamp;

import lombok.Getter;
import site.metacoding.red.web.dto.request.users.UpdateDto;


@Getter
public class Users {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Timestamp createdAt;
	
	public void allUpdate(UpdateDto updateDto) {
		this.username = updateDto.getUsername();
		this.password = updateDto.getPassword();
		this.email = updateDto.getEmail();
	}

	public void passwordUpdate(String password) {
		this.password = password;
	}
}
