package site.metacoding.red.domain.boards;

import java.sql.Timestamp;

import lombok.Getter;
import site.metacoding.red.web.dto.request.boards.UpdateDto;

@Getter
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer usersId;
	private Timestamp createdAt;
	
	public void allUpdate(UpdateDto updateDto) {
		this.title = updateDto.getTitle();
		this.content = updateDto.getContent();
	}
	public void contentUpdate(String content) {
		this.content = content;
	}
	public void titleUpdate(String title) {
		this.title = title;
	}
}
