package com.kosta.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.PageInfo;

public interface BoardService {
	List<Board> boardListByPage(PageInfo pageInfo) throws Exception;
	Board writeBoard(Board board, MultipartFile file) throws Exception;
	Board boardDetail(Integer num) throws Exception;
	void boardModify(Board board) throws Exception;
	void boardRemove(Integer num) throws Exception;
	Map<String, Object> boardSearch(String type, String keyword, Integer page) throws Exception;
	String boardLike(String id, Integer num) throws Exception;
	Boolean isBoardLike(String id, Integer num) throws Exception;	
}
