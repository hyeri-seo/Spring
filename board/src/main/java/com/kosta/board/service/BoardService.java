package com.kosta.board.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.PageInfo;

public interface BoardService {
	List<Board> boardListByPage(PageInfo pageInfo) throws Exception;
	Board writeBoard(Board board, MultipartFile file) throws Exception;
	void fileView(Integer num, OutputStream out) throws Exception;
	Board boardDetail(Integer num) throws Exception;
	Board boardModify(Board board, MultipartFile file) throws Exception;
	void removeBoard(Integer num) throws Exception;
	
	Boolean isBoardLike(String userId, Integer boardNum) throws Exception;	//사용자가 글을 선택했는지 여부 가져오기
	Boolean selectBoardLike(String userId, Integer boardNum) throws Exception;	//사용자가 좋아요를 눌렀을 경우 처리하고 선택 여부 가져오기
	
//	Map<String, Object> boardSearch(String type, String keyword, Integer page) throws Exception;
//	String boardLike(String id, Integer num) throws Exception;
}
