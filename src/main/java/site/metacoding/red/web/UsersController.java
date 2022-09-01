package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;
import site.metacoding.red.web.dto.response.RespDto;


@RequiredArgsConstructor //final로 붙은 필드를 생성자 생성해라
@RestController
public class UsersController {

	//final로 정의한 필드는 해당 클래스가 new되어야 한다.
	private final UsersDao usersDao;
	
	@PostMapping("/users")
	public RespDto<?> postInsert(JoinDto joinDto) {
		//유효성검사
		
		usersDao.insert(joinDto);
		return new RespDto<>(1,"회원가입성공",null); 
	}
	
	@GetMapping("/users/{id}")
	public RespDto<?> findById(@PathVariable Integer id) {
		return new RespDto<>(1,"성공",usersDao.findById(id));
	}
	
	@GetMapping("/users")
	public RespDto<?> findAll(){
		return new RespDto<>(1,"성공",usersDao.findAll());
	}
	
	@PutMapping("/users/{id}")
	public RespDto<?> update(@PathVariable Integer id,UpdateDto updateDto){
		//1번 : id로 select 하자.(영속화)
		Users usersPS = usersDao.findById(id);
		//2번 : 변경
		
		
		usersPS.allUpdate(updateDto);
		
		usersDao.update(usersPS);
		
		return new RespDto<>(1,"회원수정 완료",null);
	}

	@PutMapping("/users/{id}/password")
	public RespDto<?> updatePassword(@PathVariable Integer id, String password){
		// 1번 영속화
		Users usersPS = usersDao.findById(id);
		
		// 2번 변경
		usersPS.패스워드수정(password);
		
		// 3번 전체 업데이트
		usersDao.update(usersPS);
		return new RespDto<>(1, "회원패스워드 수정완료", null);
	}
	
	@DeleteMapping("/users/{id}")
	public RespDto<?> delete(@PathVariable Integer id){
		usersDao.delete(id);
		return new RespDto<>(1,"회원탈퇴 완료",null);
	}
}
