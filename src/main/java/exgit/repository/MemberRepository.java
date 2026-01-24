package exgit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import exgit.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
	
	boolean existsByUserId(String userId);  // 중복 체크용
	
	Optional<MemberEntity> findByUserId(String userId); // 로그인용

}
