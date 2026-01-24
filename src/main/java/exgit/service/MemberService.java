package exgit.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import exgit.command.SignupCommand;
import exgit.entity.MemberEntity;
import exgit.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
	
	public void signup(SignupCommand command) {
        MemberEntity member = new MemberEntity();
        member.setUserId(command.getUserId());
        member.setPassword(passwordEncoder.encode(command.getPassword()));
        member.setEmail(command.getEmail());

        memberRepository.save(member);
    }

    public boolean isUserIdTaken(String userId) {
        return memberRepository.existsByUserId(userId);
    }

}
