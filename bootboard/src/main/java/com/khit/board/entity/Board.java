package com.khit.board.entity;

import com.khit.board.dto.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "tbl_board")
@Entity   
public class Board extends BaseEntity{ //테이블이 생성되는 역할을 하는 클래스
	@Id   //pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String boardTitle;
	
	@Column(length = 30, nullable = false)
	private String boardWriter;
	
	@Column(length = 2000, nullable = false)
	private String boardContent;
	
	@Column
	private Integer boardHits;
	
	//write.html에서 name 값과 다른 이름으로 만들것
	//MulitpartFile과 String 타입이 서로 다르므로...
	@Column
	private String filename;
	
	@Column
	private String filepath;
	
	//dto를 entity로 변환하는 정적 메서드
	public static Board toSaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.filename(boardDTO.getFilename())
				.filepath(boardDTO.getFilepath())
				.boardHits(0)
				.build();
		return board;
	}
	
	//dto를 entity로 수정하여 변환하는 정적 메서드(첨부파일이 없는 경우)
	/*public static Board toUpdateNoFile(BoardDTO boardDTO) {
		Board board = Board.builder()
				.id(boardDTO.getId())
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.boardHits(boardDTO.getBoardHits())
				.build();
		return board;
	}*/
	
	//dto를 entity로 수정하여 변환하는 정적 메서드(첨부 파일이 있는 경우)
	public static Board toUpdateEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.id(boardDTO.getId())
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.boardHits(boardDTO.getBoardHits())
				.filename(boardDTO.getFilename())
				.filepath(boardDTO.getFilepath())
				.boardHits(boardDTO.getBoardHits())
				.build();
		return board;
	}
}
