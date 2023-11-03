package com.kosta.board.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.Member;
import com.kosta.board.dto.PageInfo;
import com.kosta.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		return "main";
	}
	
	@RequestMapping(value = "/boardlist", method = RequestMethod.GET)
	public ModelAndView boardList(@RequestParam(value="page", required=false, defaultValue="1") Integer page) {
		//타입 자체가 Integer 타입이라서 1만 못 씀
		ModelAndView mav = new ModelAndView();
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			List<Board> boardList = boardService.boardListByPage(pageInfo);
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("boardList", boardList);
			mav.setViewName("boardlist");
		} catch(Exception e) {
			e.printStackTrace();
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping(value="/boardwrite", method=RequestMethod.GET)
	public String boardWrite() {
		return "writeform";
	}
	
	@RequestMapping(value="/boardwrite", method=RequestMethod.POST)
	public ModelAndView boardWrite(@ModelAttribute Board board, @RequestParam("file") MultipartFile file) {
		ModelAndView mav = new ModelAndView();
		try {
			Board writeboard = boardService.writeBoard(board, file);
			mav.addObject("board", writeboard);
			mav.setViewName("detailform");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", "글 등록 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping(value="/image/{num}")
	public void imageView(@PathVariable Integer num, HttpServletResponse response) {
		try {
			boardService.fileView(num, response.getOutputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/boarddetail/{num}", method=RequestMethod.GET)
	public ModelAndView boardDetail(@PathVariable Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Board board = boardService.boardDetail(num);
			mav.addObject("board", board);				
			Member user = (Member)session.getAttribute("user");
			if(user!=null) {
				Boolean select = boardService.isBoardLike(user.getId(), num);
				mav.addObject("select", select);
			}
			mav.setViewName("detailform");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", "글 상세 조회 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping(value="/boardmodify/{num}", method=RequestMethod.GET)
	public ModelAndView boardModify(@PathVariable Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Board board = boardService.boardDetail(num);
			mav.addObject("board", board);
			mav.setViewName("modifyform");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", "글 상세 조회 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping(value="/boardmodify", method=RequestMethod.POST)
	public ModelAndView boardModify(@ModelAttribute Board board, @RequestParam("file") MultipartFile file) {
		ModelAndView mav = new ModelAndView();
		try {
			Board modifyboard = boardService.boardModify(board, file);
			mav.addObject("board", modifyboard);
			mav.setViewName("detailform");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", "글 등록 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping(value="/boarddelete/{num}/{page}", method=RequestMethod.GET)
	public String boardDelete(@PathVariable Integer num, @PathVariable Integer page) {
		try {
			boardService.removeBoard(num);
			return "redirect:/boardlist?page="+page;
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value="/like", method=RequestMethod.POST)
	@ResponseBody
	//리턴되는 값이 뷰가 아니라 데이터
	public String boardLike(@RequestParam("num") Integer num) {
		Member user = (Member)session.getAttribute("user");
		try {
			if(user==null) throw new Exception("로그인 필요");
			Boolean select = boardService.selectBoardLike(user.getId(), num);
			return String.valueOf(select);
		} catch(Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
}
