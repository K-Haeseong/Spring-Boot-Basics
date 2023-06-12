package hello.core.member;

public interface MemberService {

    //회원가입
    void join(Member member);
    
    // 멤버찾기
    Member findMember(Long memberId);

}
