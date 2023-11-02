/**
 * 
 */
package com.kosta.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.FileVO;

/**
 * @author KOSTA
 *
 */

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	//객체는 new 해야만 생성이 되는 거임. 얘는 걍 null 값을 갖고 있는 거임
	
	@Override
	public void insertBoard(Board board) throws Exception {
		sqlSession.insert("mapper.board.insertboard",board);
	}

	@Override
	public List<Board> selectBoardList(Integer row) throws Exception {
		return sqlSession.selectList("mapper.board.selectBoardList", row);
	}

	@Override
	public Integer selectBoardCount() throws Exception {
		return sqlSession.selectOne("mapper.board.selectBoardCount");
	}

	@Override
	public Board selectBoard(Integer num) throws Exception {
		return sqlSession.selectOne("mapper.board.selectBoard");
	}

	@Override
	public void updateBoard(Board board) throws Exception {
		sqlSession.update("mapper.board.updateboard",board);
	}

	@Override
	public void deleteBoard(Integer num) throws Exception {
		sqlSession.delete("mapper.board.deleteboard",num);
	}

	@Override
	public List<Board> searchBoardList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.board.searchBoardList", param);
	}

	@Override
	public Integer searchBoardCount(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.board.searchBoardCount", param);
	}

	@Override
	public void updateBoardViewCount(Integer num) throws Exception {
		sqlSession.update("mapper.board.updateBoardViewCount", num);
	}

	@Override
	public Integer selectLikeCount(Integer num) throws Exception {
		return sqlSession.selectOne("mapper.board.selectLikeCount", num);
	}

	@Override
	public void plusBoardLikeCount(Integer num) throws Exception {
		sqlSession.update("mapper.board.plusBoardLikeCount", num);
	}

	@Override
	public void minusBoardLikeCount(Integer num) throws Exception {
		sqlSession.update("mapper.board.minusBoardLikeCount", num);
	}

	@Override
	public void insertFile(FileVO fileVO) throws Exception {
		sqlSession.insert("mapper.board.insertFile", fileVO);
	}

	@Override
	public FileVO selectFile(Integer num) throws Exception {
		return sqlSession.selectOne("mapper.board.selectFile", num);
	}
	
}
