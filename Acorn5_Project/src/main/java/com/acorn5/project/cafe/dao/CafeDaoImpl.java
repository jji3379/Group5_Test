package com.acorn5.project.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn5.project.cafe.dto.CafeDto;
import com.acorn5.project.exception.DBFailException;

@Repository
public class CafeDaoImpl implements CafeDao{
	//�빑�떖 �쓽議닿컼泥� DI
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(CafeDto dto) {
		session.insert("cafe.insert", dto);
	}

	@Override
	public void update(CafeDto dto) {
		session.update("cafe.update", dto);
	}

	@Override
	public void delete(int num) {
		//�궘�젣�맂 row �쓽 媛��닔瑜� �뼸�뼱�궦�떎 
		int count=session.delete("cafe.delete", num);
		if(count==0) {//0 �씠硫� �궘�젣 �떎�뙣�씠�떎.
			throw new DBFailException(num+" 踰� 湲��쓣 �궘�젣 �븷�닔媛� �뾾�뒿�땲�떎.");
		}
	}

	@Override
	public CafeDto getData(int num) {
		CafeDto dto=session.selectOne("cafe.getData", num);
		return dto;
	}

	@Override
	public List<CafeDto> getList(CafeDto dto) {
		/*
		 *  parameterType => CafeDto
		 *  
		 *  parameterType  �뿉�뒗 �럹�씠吏� 泥섎━瑜� �쐞�븳 startRowNum 怨� endRowNum �씠 �뱾�뼱 �엳怨�
		 *  
		 *  title  寃��깋�씠硫� title  �븘�뱶�뿉 寃��깋 �궎�썙�뱶媛� �뱾�뼱�엳�떎.
		 *  writer 寃��깋�씠硫� writer �븘�뱶�뿉 寃��깋 �궎�썙�뱶媛� �뱾�뼱�엳�떎.
		 *  title+content 寃��깋�씠硫� title and content �븘�뱶�뿉 寃��깋 �궎�썙�뱶媛� �뱾�뼱 �엳�떎.
		 *  寃��깋 �궎�썙�뱶媛� �뾾�쑝硫� title,writer,content �븘�뱶�뒗 紐⑤몢 null �씠�떎.
		 *  
		 *  resultType => CafeDto 
		 */
		List<CafeDto> list=session.selectList("cafe.getList", dto);
		return list;
	}

	@Override
	public int getCount(CafeDto dto) {
		/*
		 *  parameterType => CafeDto
		 *  parameterType  �뿉�뒗 寃��깋�궎�썙�뱶媛� 議댁옱�븳�떎硫� �뱾�뼱 �엳�떎.
		 *  
		 *  resultType => int
		 */
		int count=session.selectOne("cafe.getCount", dto);
		return count;
	}

	@Override
	public void addViewCount(int num) {
		session.update("cafe.addViewCount", num);
	}

}

