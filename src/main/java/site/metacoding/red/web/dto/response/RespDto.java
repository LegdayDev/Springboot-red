package site.metacoding.red.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor

public class RespDto<T> {
	private Integer code; //1정상 , -1실패
	private String msg; //통신결과 메시지
	private T body;
}
