package com.acorn5.project.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn5.project.cafe.dto.CafeCommentDto;

@Repository
public class CafeCommentDaoImpl implements CafeCommentDao{
	@Autowired
	private SqlSession session;
	
	@Override
	public List<CafeCommentDto> getList(CafeCommentDto dto) {
		List<CafeCommentDto> list=
				session.selectList("cafeComment.getList", dto);
		return list;
	}

	@Override
	public void insert(CafeCommentDto dto) {
		session.insert("cafeComment.insert", dto);
	}

	@Override
	public void update(CafeCommentDto dto) {
		session.update("cafeComment.update", dto);
	}

	@Override
	public void delete(int num) {
		//�뙎湲� �궘�젣�뒗 deleted 移쇰읆�쓽 �궡�슜�쓣 'yes' 濡� �닔�젙�븯�뒗 �옉�뾽�쓣 �븳�떎. 
		session.update("cafeComment.delete", num);
	}
	/*
	 *  �깉濡쒖슫 �뙎湲��쓣 ���옣�븳 吏곹썑�뿉 諛붾줈 �빐�떦 �뙎湲��쓽 踰덊샇媛� �븘�슂 �븯湲� �븣臾몄뿉
	 *  �뙎湲��쓽 湲�踰덊샇�뒗 誘몃━ �뼸�뼱�궡�꽌 �옉�뾽�쓣 �빐�빞�븳�떎. 
	 *  �뵲�씪�꽌 �깉 �뙎湲��쓽 湲�踰덊샇瑜� 由ы꽩�빐二쇰뒗 硫붿냼�뱶媛� �븘�슂�븯�떎. 
	 */
	@Override
	public int getSequence() {
		/*  parameterType => x
		 *  resultType => int 
		 *  sql id => getSequence
		 */
		int seq=session.selectOne("cafeComment.getSequence");
		return seq;
	}

	@Override
	public CafeCommentDto getData(int num) {
		return session.selectOne("cafeComment.getData", num);
	}

	@Override
	public int getCount(int ref_group) {
		
		return session.selectOne("cafeComment.getCount", ref_group);
	}

}