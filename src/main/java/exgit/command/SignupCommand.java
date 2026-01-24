package exgit.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupCommand {
	@NotBlank(message = "아이디는 필수입니다.")
	@Size(min = 4, max = 20, message = "아이디는 4 ~ 20자 사이여야 합니다.")
	private String userId;
	
	@NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6~20자 사이여야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$",
             message = "비밀번호는 영어와 숫자를 포함해야 합니다.")
	private String password;
	
	@NotBlank(message = "이메일은 필수입니다.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String email;


}
