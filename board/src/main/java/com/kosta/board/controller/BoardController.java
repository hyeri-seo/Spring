package com.kosta.board.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.PageInfo;
import com.kosta.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
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

}
