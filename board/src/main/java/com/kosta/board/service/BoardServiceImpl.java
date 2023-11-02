package com.kosta.board.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dao.BoardDao;
import com.kosta.board.dao.BoardDaoImpl;
import com.kosta.board.dto.Board;
import com.kosta.board.dto.FileVO;
import com.kosta.board.dto.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Board> boardListByPage(PageInfo pageInfo) throws Exception {
		int boardCount = boardDao.selectBoardCount();
		int allPage = (int)Math.ceil((double)boardCount/10);
		int startPage = (pageInfo.getCurPage()-1)/10*10+1;
		int endPage = Math.min(startPage+10-1, allPage);
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		if(pageInfo.getCurPage()>allPage) pageInfo.setCurPage(allPage);
		
		int row = (pageInfo.getCurPage()-1)*10+1;
		return boardDao.selectBoardList(row-1);
	}

	@Override
	public Board writeBoard(Board board, MultipartFile file) throws Exception {
		if(file!=null && !file.isEmpty()) {
			String dir = "c:/jsb/upload/";
			FileVO fileVO = new FileVO();
			fileVO.setDirectory(dir);
			fileVO.setName(file.getOriginalFilename());
			fileVO.setSize(file.getSize());
			fileVO.setContenttype(file.getContentType());
			fileVO.setData(file.getBytes());
			boardDao.insertFile(fileVO);
			Integer num = fileVO.getNum();
			
			//디렉토리에 파일이 바로 저장됨
			//업로드 할 파일 객체 생성. 데이터는 들어가 있지 않음
			//새로 생성된 번호로 파일 업로드
			File uploadFile = new File(dir+num);
			file.transferTo(uploadFile);
			board.setFileurl(num+"");
		}
		//얘는 보드에 파일 첨부하는 것
		boardDao.insertBoard(board);
		return boardDao.selectBoard(board.getNum());
	}
	
//	@Override
//	public Board boardDetail(Integer num) throws Exception {
//		boardDao.updateBoardViewCount(num);
//		return boardDao.selectBoard(num);
//	}
//
//	@Override
//	public void boardModify(Board board) throws Exception {
//		boardDao.updateBoard(board);
//	}
//
//	@Override
//	public void boardRemove(Integer num) throws Exception {
//		boardDao.deleteBoard(num);
//	}
//
//	@Override
//	public Map<String, Object> boardSearch(String type, String keyword, Integer page) throws Exception {
//		Map<String,Object> param = new HashMap<>();
//		param.put("type", type);
//		param.put("keyword", keyword);
//		
//		PageInfo pageInfo = new PageInfo();
//		int boardCount = boardDao.searchBoardCount(param);
//		int maxPage = (int)Math.ceil((double)boardCount/10);
//		int startPage = (page-1)/10*10+1;  //1,11,21,31...
//		int endPage = startPage+10-1; //10,20,30...
//		if(endPage>maxPage) endPage=maxPage;
//		if(page>maxPage) page=maxPage;
//		
//		pageInfo.setAllPage(maxPage);
//		pageInfo.setCurPage(page);
//		pageInfo.setStartPage(startPage);
//		pageInfo.setEndPage(endPage);
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("pageInfo", pageInfo);
//
//		if(page==0) {
//			return map;
//		}
//		
//		int row = (page-1)*10+1;  //�쁽�옱 �럹�씠吏��쓽 �떆�옉 row
//		param.put("row", row-1);
//		List<Board> boardList = boardDao.searchBoardList(param);
//		
//		map.put("boardList", boardList);
//		map.put("type", type);
//		map.put("keyword", keyword);
//		return map;
//	}
//
//
//	@Override
//	public String boardLike(String id, Integer num) throws Exception {
//		Map<String,Object> param = new HashMap<>();
//		param.put("id", id);
//		param.put("num", num);
//		//1. boardlike 테이블에 데이터 있는지 확인(member_id, board_num)
//		Integer likenum = boardDao.selectBoardLike(param);
//		
//		Map<String,Object> res = new HashMap<>();
//		
//		if(likenum==null) { //2-1. 없으면 
//			boardDao.insertBoardLike(param); //boardlike에 삽입
//			boardDao.plusBoardLikeCount(num);  //board 테이블에 좋아요수 증가
//			res.put("select", true);
//		} else { //2-2. 있으면 
//			boardDao.deleteBoardLike(param);  //boardlike에서 삭제
//			boardDao.minusBoardLikeCount(num); //board 테이블에 좋아요수 감소
//			res.put("select", false);
//		}
//		//4. 좋아요 수
//		Integer likecount = boardDao.selectLikeCount(num);
//		res.put("likecount", likecount);
//		
//		JSONObject jsonObj = new JSONObject(res);
//		return jsonObj.toJSONString();
//	}
//
//	@Override
//	public Boolean isBoardLike(String id, Integer num) throws Exception {
//		Map<String,Object> param = new HashMap<>();
//		param.put("id", id);
//		param.put("num", num);
//		Integer likenum = boardDao.selectBoardLike(param);
//		if(likenum==null) return false;
//		return true;
//	}

}
