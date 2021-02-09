package com.acorn5.project.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acorn5.project.cafe.dto.CafeCommentDto;
import com.acorn5.project.cafe.dto.CafeDto;
import com.acorn5.project.cafe.service.CafeService;

@Controller
public class CafeController {
	//�쓽議닿컼泥� DI
	@Autowired
	private CafeService service;
	
	@RequestMapping("/cafe/ajax_comment_list")
	public ModelAndView ajaxCommentList(HttpServletRequest request,
			ModelAndView mView) {
		service.moreCommentList(request);
		mView.setViewName("cafe/ajax_comment_list");
		return mView;
	}

	//�뙎湲� �닔�젙 ajax �슂泥��뿉 ���븳 �슂泥� 泥섎━ 
	@RequestMapping(value = "/cafe/private/comment_update", 
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> commentUpdate(CafeCommentDto dto){
		//�뙎湲��쓣 �닔�젙 諛섏쁺�븯怨� 
		service.updateComment(dto);
		//JSON 臾몄옄�뿴�쓣 �겢�씪�씠�뼵�듃�뿉寃� �쓳�떟�븳�떎.
		Map<String, Object> map=new HashMap<>();
		map.put("num", dto.getNum());
		map.put("content", dto.getContent());
		return map;
	}
	
	@RequestMapping("/cafe/private/comment_delete")
	public ModelAndView commentDelete(HttpServletRequest request,
			ModelAndView mView, @RequestParam int ref_group) {
		service.deleteComment(request);
		mView.setViewName("redirect:/cafe/detail.do?num="+ref_group);
		return mView;
	}
	//�깉 �뙎湲� ���옣 �슂泥� 泥섎━
	@RequestMapping(value = "/cafe/private/comment_insert", 
			method = RequestMethod.POST)
	public String commentInsert(HttpServletRequest request,
			@RequestParam int ref_group) {
		//�깉 �뙎湲��쓣 ���옣�븯怨�
		service.saveComment(request);
		//湲� �옄�꽭�엳 蹂닿린濡� �떎�떆 由щ떎�씪�젆�듃 �씠�룞 �떆�궓�떎.
		//ref_group �� �옄�꽭�엳 蹂닿린 �뻽�뜕 湲�踰덊샇 
		return "redirect:/cafe/detail.do?num="+ref_group;
	}
	
	@RequestMapping("/cafe/private/delete")
	public String delete(@RequestParam int num) {
		service.deleteContent(num);
		return "cafe/private/delete";
	}
	@RequestMapping("/cafe/private/updateform")
	public ModelAndView updateform(@RequestParam int num,
			ModelAndView mView) {
		service.getDetail(num, mView);
		mView.setViewName("cafe/private/updateform");
		return mView;
	}
	@RequestMapping("/cafe/private/update")
	public String update(@ModelAttribute("dto") CafeDto dto) {
		service.updateContent(dto);
		return "cafe/private/update";
	}
	
	@RequestMapping("/cafe/detail")
	public ModelAndView detail(@RequestParam int num, ModelAndView mView) {
		//�옄�꽭�엳 蹂댁뿬以� 湲�踰덊샇媛� �뙆�씪誘명꽣濡� �꽆�뼱�삩�떎.
		service.getDetail(num, mView);
		//view page 濡� forward �씠�룞�빐�꽌 �쓳�떟
		mView.setViewName("cafe/detail");
		return mView;
	}
	
	//湲� 紐⑸줉 �슂泥�泥섎━
	@RequestMapping("/cafe/list")
	public ModelAndView list(ModelAndView mView, 
			HttpServletRequest request) {
		
		service.getList(mView, request);
		
		mView.setViewName("cafe/list");
		return mView;
	}
	
	//移댄럹 �깉湲� ���옣 �슂泥� 泥섎━
	@RequestMapping(value = "/cafe/private/insert", 
			method = RequestMethod.POST)
	public String insert(CafeDto dto, HttpSession session) {
		//湲��옉�꽦�옄�뒗 �꽭�뀡�뿉�꽌 �뼸�뼱�궡�꽌
		String id=(String)session.getAttribute("id");
		//dto �뿉 �떞�뒗�떎 
		dto.setWriter(id);
		//�꽌鍮꾩뒪瑜� �넻�빐�꽌 �깉湲��쓣 DB �뿉���옣
		service.saveContent(dto);
		
		return "cafe/private/insert";
	}
	
	//移댄럹 �깉 湲��벐湲� �슂泥� 泥섎━
	@RequestMapping("/cafe/private/insertform")
	public String insertform() {
		
		// /WEB-INF/views/    cafe/private/insertform      .jsp 
		return "cafe/private/insertform";
	}
}