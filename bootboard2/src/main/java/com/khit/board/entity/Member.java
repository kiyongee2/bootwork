package com.khit.board.entity;

import java.util.ArrayList;
import java.util.List;

import com.khit.board.dto.MemberDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor //모든 필드를 매개변수를 가진 생성자
@NoArgsConstructor  //기본 생성자
@Builder
@ToString(exclude="boardList")  //순환참조 방지
@Setter
@Getter
@Table(name = "t_member")
@Entity
public class Member extends BaseEntity{
	@Id  //필수 입력 - PK임
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;  //회원번호
	
	@Column(unique = true)
	private String memberId;  //아이디
	
	@Column(nullable = false)
	private String password;  //비밀번호
	
	@Column(nullable = false, length = 30)
	private String name;
	
	//@Column
	//private String role;   //권한
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	//Board와 관계 매핑
	//주인 설정(다쪽이-board 주인)
	//cascade : 참조된 객체가 삭제되면 참조하는 객체도 삭제됨
	@OneToMany(mappedBy="member", cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<>();
	
	//Reply와 연관 관계 설정
	@OneToMany(mappedBy="member", cascade = CascadeType.ALL)
	private List<Reply> replyList;
	
	//dto(view에 온 입력값) -> entity(db에 저장)
	//회원 가입(id(회원번호)가 자동생성되므로 명시하면 안됨)
	public static Member toSaveEntity(MemberDTO memberDTO) {
		Member member = Member.builder()
				.memberId(memberDTO.getMemberId())
				.password(memberDTO.getPassword())
				.name(memberDTO.getName())
				.role(memberDTO.getRole())
				.build();
		return member;
	}
	
	//회원 수정시에는 id가 존재함
	public static Member toSaveUpdate(MemberDTO memberDTO) {
		Member member = Member.builder()
				.id(memberDTO.getId())
				.memberId(memberDTO.getMemberId())
				.password(memberDTO.getPassword())
				.name(memberDTO.getName())
				.role(memberDTO.getRole())
				.build();
		return member;
	}
	
}
