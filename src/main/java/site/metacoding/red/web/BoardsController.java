package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor
@RestController
public class BoardsController {

	private final BoardsDao boardsDao;
	
	@PostMapping("/boards")
	public RespDto<?> boardsInsert(WriteDto writeDto){
		boardsDao.insert(writeDto);
		return new RespDto<>(1,"게시글 등록",null);
	}
	@GetMapping("/boards/{id}")
	public RespDto<?> boardsFindById(@PathVariable Integer id){
		return new RespDto<>(1,id+"번 게시물확인",boardsDao.findByIdToDetail(id));
	}
	@GetMapping("/boards")
	public RespDto<?> boardsFindAll(){
		return new RespDto<>(1,"모든게시물 확인",boardsDao.findAll());
	}
	@PutMapping("/boards/{id}")
	public RespDto<?> boardsAllUpdate(@PathVariable Integer id, UpdateDto updateDto){
		Boards boardsPS = boardsDao.findById(id);
		boardsPS.allUpdate(updateDto);
		boardsDao.update(boardsPS);
		
		return new RespDto<>(1,"게시물수정완료",boardsDao.findById(id));
	}
	@PutMapping("boards/{id}/content")
	public RespDto<?> boardsContentUpdate(@PathVariable Integer id, String content){
		Boards boardsPS = boardsDao.findById(id);
		boardsPS.contentUpdate(content);
		boardsDao.update(boardsPS);
		
		return new RespDto<>(1,"게시물내용수정완료",boardsDao.findById(id));
	}
	@PutMapping("boards/{id}/title")
	public RespDto<?> boardsTitleUpdate(@PathVariable Integer id, String title){
		Boards boardsPS = boardsDao.findById(id);
		boardsPS.titleUpdate(title);
		
		boardsDao.update(boardsPS);
		
		return new RespDto<>(1,"게시물제목수정완료",boardsDao.findById(id));
	}
	@DeleteMapping("/boards/{id}")//게시물번호로 삭제
	public RespDto<?> boardsDelete(@PathVariable Integer id){
		boardsDao.delete(id);
		return new RespDto<>(1,"게시물삭제 완료",null);
	}
}
