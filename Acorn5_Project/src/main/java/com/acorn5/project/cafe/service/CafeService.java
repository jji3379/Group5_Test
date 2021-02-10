package com.acorn5.project.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.acorn5.project.cafe.dto.CafeCommentDto;
import com.acorn5.project.cafe.dto.CafeDto;

public interface CafeService {
	//�깉湲��쓣 ���옣�븯�뒗 硫붿냼�뱶 
	public void saveContent(CafeDto dto);
	//湲�紐⑸줉�쓣 �뼸�뼱�삤怨� �럹�씠吏� 泥섎━�뿉 �븘�슂�븳 媛믩뱾�쓣 ModelAndView 媛앹껜�뿉 �떞�븘二쇰뒗 硫붿냼�뱶 
	public void getList(ModelAndView mView, HttpServletRequest request);
	//湲��븯�굹�쓽 �젙蹂대�� ModelAndView 媛앹껜�뿉 �떞�븘二쇰뒗 硫붿냼�뱶
	public void getDetail(int num, ModelAndView mView);
	//湲��쓣 �닔�젙�븯�뒗 硫붿냼�뱶
	public void updateContent(CafeDto dto);
	//湲��쓣 �궘�젣�븯�뒗 硫붿냼�뱶
	public void deleteContent(int num);
	//�뙎湲��쓣 ���옣�븯�뒗 硫붿냼�뱶
	public void saveComment(HttpServletRequest request);
	public void deleteComment(HttpServletRequest request);//�뙎湲� �궘�젣
	public void updateComment(CafeCommentDto dto);//�뙎湲� �닔�젙
	public void moreCommentList(HttpServletRequest request);//�뙎湲� 異붽� �쓳�떟
}