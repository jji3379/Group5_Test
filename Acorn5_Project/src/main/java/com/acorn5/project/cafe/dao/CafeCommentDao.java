package com.acorn5.project.cafe.dao;



import java.util.List;

import com.acorn5.project.cafe.dto.CafeCommentDto;

public interface CafeCommentDao {
	//�뙎湲� 紐⑸줉 �뼸�뼱�삤湲�
	public List<CafeCommentDto> getList(CafeCommentDto dto);
	//�뙎湲� 異붽�
	public void insert(CafeCommentDto dto);
	//�뙎湲� �닔�젙
	public void update(CafeCommentDto dto);
	//�뙎湲� �궘�젣
	public void delete(int num);
	//�뙎湲��쓽 �떆���뒪媛�(湲�踰덊샇) 瑜� 由ы꽩�븯�뒗 硫붿냼�뱶
	public int getSequence();
	//�뙎湲� �븯�굹�쓽 �젙蹂대�� 由ы꽩�븯�뒗 硫붿냼�뱶
	public CafeCommentDto getData(int num);
	//�뙎湲��쓽 媛��닔瑜� 由ы꽩�븯�뒗 硫붿냼�뱶
	public int getCount(int ref_group);
}