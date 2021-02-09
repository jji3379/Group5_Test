  
package com.acorn5.project.cafe.dao;



import java.util.List;

import com.acorn5.project.cafe.dto.CafeDto;

public interface CafeDao {
	//湲� 異붽�
	public void insert(CafeDto dto);
	//�뙆�씪 �궘�젣
	public void delete(int num);
	//�뙆�씪 �븯�굹�쓽 �젙蹂� �뼸�뼱�삤湲�
	public CafeDto getData(int num);
	//湲� 紐⑸줉 �뼸�뼱�삤湲� (�럹�씠吏� 泥섎━�� 寃��깋 �궎�썙�뱶瑜� 怨좊젮�븳 紐⑸줉)
	public List<CafeDto> getList(CafeDto dto);
	//湲��쓽 媛��닔 �뼸�뼱�삤湲�(寃��깋 �궎�썙�뱶�뿉 �빐�떦�븯�뒗 媛��닔)
	public int getCount(CafeDto dto);
	//湲� 議고쉶�닔 �삱由ш린
	public void addViewCount(int num);
	
	public void update(CafeDto dto);
}



