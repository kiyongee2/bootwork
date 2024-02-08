package com.khit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;

	public void save(BoardDTO boardDTO, MultipartFile boardFile) throws Exception {
		//1. 파일을 서버에 저장하고, 
		if(!boardFile.isEmpty()) { //전달된 파일이 있으면
		  //저장 경로
		  //String filepath = "C:\\bootworks\\bootboard\\src\\main\\resources\\static\\upload\\";
		  UUID uuid = UUID.randomUUID();  //무작위 아이디 생성(중복파일의 이름을 생성해줌)
		  String filename = uuid + "_" +boardFile.getOriginalFilename(); //원본 파일
		  String filepath = "C:/springfiles/" + filename;
		  //File 클래스 객체 생성
		  File savedFile = new File(filepath); //실제 저장되는 파일
		  boardFile.transferTo(savedFile);
		  //2.파일 이름은 db에 저장
		  boardDTO.setFilename(filename);
		  boardDTO.setFilepath(filepath); //파일 경로 설정함
		}
		//dto -> entity로 변환
		Board board = Board.toSaveEntity(boardDTO);
		//entity를 db에 저장
		boardRepository.save(board);
	}

	public List<BoardDTO> findAll() {
		//db에서 entity list를 가져옴
		//List<Board> boardList = boardRepository.findAll();
		//내림차순 정렬 - Sort.by(정렬방식, 해당필드)
		List<Board> boardList = 
				boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		//게시글을 담을 빈 리스트 생성
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
		for(Board board : boardList) {
			//entity -> dto로 변환
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		return boardDTOList;
	}

	public BoardDTO findById(Long id) {
		Optional<Board> findBoard = boardRepository.findById(id);
		if(findBoard.isPresent()) { //검색한 게시글이 있으면 dto를 컨트롤러로 반환함
			BoardDTO boardDTO = BoardDTO.toSaveDTO(findBoard.get());
			return boardDTO;
		}else { //게시글이 없으면 에러 처리
			throw new BootBoardException("게시글을 찾을 수 없습니다.");
		}
	}

	@Transactional  //컨트롤러 작업(매서드)이 2개 이상이면 사용함
	public void updateHits(Long id) {
		//이 메서드를 boardRepository에 생성
		boardRepository.updateHits(id);
	}

	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}

	public BoardDTO update(BoardDTO boardDTO, MultipartFile boardFile) throws Exception {
		if(!boardFile.isEmpty()) { //전달된 파일이 있으면
			UUID uuid = UUID.randomUUID();  //무작위 아이디 생성(중복파일의 이름을 생성해줌)
			  String filename = uuid + "_" +boardFile.getOriginalFilename(); //원본 파일
			  String filepath = "C:/springfiles/" + filename;
			  //File 클래스 객체 생성
			  File savedFile = new File(filepath); //실제 저장되는 파일
			  boardFile.transferTo(savedFile);
			  //2.파일 이름은 db에 저장
			  boardDTO.setFilename(filename);
			  boardDTO.setFilepath(filepath); //파일 경로 설정함
		}else {
			//수정할 파일이 없으면 게시글 번호 경로만 보여줌
			boardDTO.setFilename(findById(boardDTO.getId()).getFilename());
			boardDTO.setFilepath(findById(boardDTO.getId()).getFilepath());
		}
		
		Board board = Board.toUpdateEntity(boardDTO);
	    boardRepository.save(board);
	    return findById(boardDTO.getId());
	}

	public Page<BoardDTO> findListAll(Pageable pageable) {
		int page = pageable.getPageNumber() - 1; //db는 현재페이지보다 1 작아야함
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardRepository.findAll(pageable);
		
		log.info("boardList.isFirst()=" + boardList.isFirst());
		log.info("boardList.isLast()=" + boardList.isLast());
		log.info("boardList.getNumber()=" + boardList.getNumber());
		
		//생성자 방식으로 boardDTOList 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> 
		new BoardDTO(board.getId(), board.getBoardTitle(), board.getBoardWriter(),
				  board.getBoardContent(), board.getBoardHits(), board.getFilepath(),
				  board.getFilename(), board.getCreatedDate(), board.getUpdatedDate()));
		
		return boardDTOList;
	}

	public Page<BoardDTO> findByBoardTitleContaining(String keyword, Pageable pageable) {
		int page = pageable.getPageNumber() - 1; //db는 현재페이지보다 1 작아야함
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardRepository.findByBoardTitleContaining(keyword, pageable);

		//생성자 방식으로 boardDTOList 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> 
		new BoardDTO(board.getId(), board.getBoardTitle(), board.getBoardWriter(),
				  board.getBoardContent(), board.getBoardHits(), board.getFilepath(),
				  board.getFilename(), board.getCreatedDate(), board.getUpdatedDate()));
		return boardDTOList;
	}

	public Page<BoardDTO> findByBoardContentContaining(String keyword, Pageable pageable) {
		int page = pageable.getPageNumber() - 1; //db는 현재페이지보다 1 작아야함
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardRepository.findByBoardContentContaining(keyword, pageable);

		//생성자 방식으로 boardDTOList 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> 
		new BoardDTO(board.getId(), board.getBoardTitle(), board.getBoardWriter(),
				  board.getBoardContent(), board.getBoardHits(), board.getFilepath(),
				  board.getFilename(), board.getCreatedDate(), board.getUpdatedDate()));
		return boardDTOList;
	}

}
