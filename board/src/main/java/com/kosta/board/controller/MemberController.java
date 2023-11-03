package com.kosta.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.Member;
import com.kosta.board.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("id") String id, @RequestParam("password") String password) {
		ModelAndView mav = new ModelAndView();
		try {
			Member member = memberService.login(id, password);
			session.setAttribute("user", member);
			mav.setViewName("redirect:/");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute Member member, Model model) {
		try {
			memberService.join(member);
			return "login";
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value="/idcheck", method=RequestMethod.POST)
	@ResponseBody
	public String idcheck(@RequestParam("id") String id) {
		try {
			Member member = memberService.userInfo(id);
			if(member!=null) return "exist";
			return "notexist";
		} catch(Exception e) {
			e.printStackTrace();
			return "exist";
		}
		
//		Member user  = (Member)session.getAttribute("user");
//		try {
//			if(user!=null) throw new Exception("이미 있는 아이디입니다.");
//			String nonexist = memberService.idCheck(id);
//			return nonexist;
//		} catch(Exception e) {
//			e.printStackTrace();
//			return "false";
//		}
	}
}
