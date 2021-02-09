package com.acorn5.project.cafe.service;


import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.acorn5.project.cafe.dao.CafeCommentDao;
import com.acorn5.project.cafe.dao.CafeDao;
import com.acorn5.project.cafe.dto.CafeCommentDto;
import com.acorn5.project.cafe.dto.CafeDto;
import com.acorn5.project.exception.DBFailException;

@Service
public class CafeServiceImpl implements CafeService{
	//�쓽議닿컼泥� DI 
	@Autowired
	private CafeDao cafeDao;
	//�쓽議닿컼泥� DI
	@Autowired
	private CafeCommentDao cafeCommentDao;
	
	@Override
	public void saveContent(CafeDto dto) {
		cafeDao.insert(dto);
	}

	@Override
	public void getList(ModelAndView mView, HttpServletRequest request) {
		//�븳 �럹�씠吏��뿉 紐뉕컻�뵫 �몴�떆�븷 寃껋씤吏�
		final int PAGE_ROW_COUNT=5;
		//�븯�떒 �럹�씠吏�瑜� 紐뉕컻�뵫 �몴�떆�븷 寃껋씤吏�
		final int PAGE_DISPLAY_COUNT=5;
		
		//蹂댁뿬以� �럹�씠吏��쓽 踰덊샇瑜� �씪�떒 1�씠�씪怨� 珥덇린媛� 吏��젙
		int pageNum=1;
		//�럹�씠吏� 踰덊샇媛� �뙆�씪誘명꽣濡� �쟾�떖�릺�뒗吏� �씫�뼱�� 蹂몃떎.
		String strPageNum=request.getParameter("pageNum");
		//留뚯씪 �럹�씠吏� 踰덊샇媛� �뙆�씪誘명꽣濡� �꽆�뼱 �삩�떎硫�
		if(strPageNum != null){
			//�닽�옄濡� 諛붽퓭�꽌 蹂댁뿬以� �럹�씠吏� 踰덊샇濡� 吏��젙�븳�떎.
			pageNum=Integer.parseInt(strPageNum);
		}
		
		//蹂댁뿬以� �럹�씠吏��쓽 �떆�옉 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//蹂댁뿬以� �럹�씠吏��쓽 �걹 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		/*
			[ 寃��깋 �궎�썙�뱶�뿉 愿��젴�맂 泥섎━ ]
			-寃��깋 �궎�썙�뱶媛� �뙆�씪誘명꽣濡� �꽆�뼱�삱�닔�룄 �엳怨� �븞�꽆�뼱 �삱�닔�룄 �엳�떎.		
		*/
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		//留뚯씪 �궎�썙�뱶媛� �꽆�뼱�삤吏� �븡�뒗�떎硫� 
		if(keyword==null){
			//�궎�썙�뱶�� 寃��깋 議곌굔�뿉 鍮� 臾몄옄�뿴�쓣 �꽔�뼱以��떎. 
			//�겢�씪�씠�뼵�듃 �쎒釉뚮씪�슦���뿉 異쒕젰�븷�븣 "null" �쓣 異쒕젰�릺吏� �븡寃� �븯湲� �쐞�빐�꽌  
			keyword="";
			condition=""; 
		}
		
		//�듅�닔湲고샇瑜� �씤肄붾뵫�븳 �궎�썙�뱶瑜� 誘몃━ 以�鍮꾪븳�떎. 
		String encodedK=URLEncoder.encode(keyword);
		
		//startRowNum 怨� endRowNum  �쓣 CafeDto 媛앹껜�뿉 �떞怨�
		CafeDto dto=new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//ArrayList 媛앹껜�쓽 李몄“媛믪쓣 �떞�쓣 吏��뿭蹂��닔瑜� 誘몃━ 留뚮뱺�떎.
		List<CafeDto> list=null;
		//�쟾泥� row �쓽 媛��닔瑜� �떞�쓣 吏��뿭蹂��닔瑜� 誘몃━ 留뚮뱺�떎.
		int totalRow=0;
		//留뚯씪 寃��깋 �궎�썙�뱶媛� �꽆�뼱�삩�떎硫� 
		if(!keyword.equals("")){
			//寃��깋 議곌굔�씠 臾댁뾿�씠�깘�뿉 �뵲�씪 遺꾧린 �븯湲�
			if(condition.equals("title_content")){//�젣紐� + �궡�슜 寃��깋�씤 寃쎌슦
				//寃��깋 �궎�썙�뱶瑜� CafeDto �뿉 �떞�븘�꽌 �쟾�떖�븳�떎.
				dto.setTitle(keyword);
				dto.setContent(keyword);	
			}else if(condition.equals("title")){ //�젣紐� 寃��깋�씤 寃쎌슦
				dto.setTitle(keyword);			
			}else if(condition.equals("writer")){ //�옉�꽦�옄 寃��깋�씤 寃쎌슦
				dto.setWriter(keyword);	
			} // �떎瑜� 寃��깋 議곌굔�쓣 異붽� �븯怨� �떢�떎硫� �븘�옒�뿉 else if() 瑜� 怨꾩냽 異붽� �븯硫� �맂�떎.
		}
		//湲�紐⑸줉 �뼸�뼱�삤湲�
		list=cafeDao.getList(dto);
		//湲��쓽 媛��닔
		totalRow=cafeDao.getCount(dto);
		
		//�븯�떒 �떆�옉 �럹�씠吏� 踰덊샇 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//�븯�떒 �걹 �럹�씠吏� 踰덊샇
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		
		//�쟾泥� �럹�씠吏��쓽 媛��닔 援ы븯湲�
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//�걹 �럹�씠吏� 踰덊샇媛� �씠誘� �쟾泥� �럹�씠吏� 媛��닔蹂대떎 �겕寃� 怨꾩궛�릺�뿀�떎硫� �옒紐삳맂 媛믪씠�떎.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //蹂댁젙�빐 以��떎. 
		}
		
		//view page �뿉�꽌 �븘�슂�븳 �궡�슜�쓣 ModelAndView 媛앹껜�뿉 �떞�븘以��떎
		mView.addObject("list", list);
		mView.addObject("pageNum", pageNum);
		mView.addObject("startPageNum", startPageNum);
		mView.addObject("endPageNum", endPageNum);
		mView.addObject("totalPageCount", totalPageCount);
		mView.addObject("condition", condition);
		mView.addObject("keyword", keyword);
		mView.addObject("encodedK", encodedK);
		mView.addObject("totalRow", totalRow);
	}

	@Override
	public void getDetail(int num, ModelAndView mView) {
		//湲�踰덊샇瑜� �씠�슜�빐�꽌 湲��젙蹂대�� �뼸�뼱�삤怨� 
		CafeDto dto=cafeDao.getData(num);
		//湲��젙蹂대�� ModelAndView 媛앹껜�뿉 �떞怨�
		mView.addObject("dto", dto);
		//湲� 議고쉶�닔瑜� 利앷� �떆�궓�떎.
		cafeDao.addViewCount(num);
		
		/* �븘�옒�뒗 �뙎湲� �럹�씠吏� 泥섎━ 愿��젴 鍮꾩쫰�땲�뒪 濡쒖쭅 �엯�땲�떎.*/
		final int PAGE_ROW_COUNT=5;

		//蹂댁뿬以� �럹�씠吏��쓽 踰덊샇
		int pageNum=1;

		//蹂댁뿬以� �럹�씠吏� �뜲�씠�꽣�쓽 �떆�옉 ResultSet row 踰덊샇
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//蹂댁뿬以� �럹�씠吏� �뜲�씠�꽣�쓽 �걹 ResultSet row 踰덊샇
		int endRowNum=pageNum*PAGE_ROW_COUNT;

		//�쟾泥� row �쓽 媛��닔瑜� �씫�뼱�삩�떎.
		//�옄�꽭�엳 蹂댁뿬以� 湲��쓽 踰덊샇媛� ref_group  踰덊샇 �씠�떎. 
		int totalRow=cafeCommentDao.getCount(num);
		//�쟾泥� �럹�씠吏��쓽 媛��닔 援ы븯湲�
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);

		// CafeCommentDto 媛앹껜�뿉 �쐞�뿉�꽌 怨꾩궛�맂 startRowNum 怨� endRowNum �쓣 �떞�뒗�떎.
		CafeCommentDto commentDto=new CafeCommentDto();
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);
		//ref_group 踰덊샇�룄 �떞�뒗�떎.
		commentDto.setRef_group(num);

		//DB �뿉�꽌 �뙎湲� 紐⑸줉�쓣 �뼸�뼱�삩�떎.
		List<CafeCommentDto> commentList=cafeCommentDao.getList(commentDto);
		//ModelAndView 媛앹껜�뿉 �뙎湲� 紐⑸줉�룄 �떞�븘以��떎.
		mView.addObject("commentList", commentList);
		mView.addObject("totalPageCount", totalPageCount);
	}

	@Override
	public void updateContent(CafeDto dto) {
		cafeDao.update(dto);
	}

	@Override
	public void deleteContent(int num) {
		cafeDao.delete(num);
	}

	@Override
	public void saveComment(HttpServletRequest request) {
		//�뙎湲� �옉�꽦�옄(濡쒓렇�씤�맂 �븘�씠�뵒)
		String writer=(String)request.getSession().getAttribute("id");
		//�뤌 �쟾�넚�릺�뒗 �뙎湲��쓽 �젙蹂� �뼸�뼱�궡湲�
		int ref_group=Integer.parseInt(request.getParameter("ref_group"));
		String target_id=request.getParameter("target_id");
		String content=request.getParameter("content");
		/*
		 * �썝湲��쓽 �뙎湲��� comment_group 踰덊샇媛� �쟾�넚�씠 �븞�릺怨�
		 * �뙎湲��쓽 �뙎湲��� comment_group 踰덊샇媛� �쟾�넚�씠 �맂�떎.
		 * �뵲�씪�꽌 null �뿬遺�瑜� 議곗궗�븯硫� �썝湲��쓽 �뙎湲��씤吏� �뙎湲��쓽 �뙎湲��씤吏� �뙋蹂꾪븷�닔 �엳�떎.
		 */
		String comment_group=request.getParameter("comment_group");
		//�깉 �뙎湲��쓽 湲�踰덊샇�뒗 誘몃━ �뼸�뼱�궦�떎.
		int seq=cafeCommentDao.getSequence();
		//���옣�븷 �깉 �뙎湲� �젙蹂대�� dto �뿉 �떞湲�
		CafeCommentDto dto=new CafeCommentDto();
		dto.setNum(seq);
		dto.setWriter(writer);
		dto.setTarget_id(target_id);
		dto.setContent(content);
		dto.setRef_group(ref_group);
		if(comment_group == null) {//�썝湲��쓽 �뙎湲��씤 寃쎌슦 
			//�뙎湲��쓽 湲�踰덊샇�� comment_group 踰덊샇瑜� 媛숆쾶 �븳�떎.
			dto.setComment_group(seq);
		}else {//�뙎湲��쓽 �뙎湲��씤 寃쎌슦 
			//�뤌 �쟾�넚�맂 comment_group 踰덊샇瑜� �닽�옄濡� 諛붽퓭�꽌 dto �뿉 �꽔�뼱以��떎.
			dto.setComment_group(Integer.parseInt(comment_group));
		}
		//�뙎湲� �젙蹂대�� DB �뿉 ���옣�븳�떎.
		cafeCommentDao.insert(dto);
	}

	@Override
	public void deleteComment(HttpServletRequest request) {
		//GET 諛⑹떇 �뙆�씪誘명꽣濡� �쟾�떖�릺�뒗 �궘�젣�븷 �뙎湲� 踰덊샇 
		int num=Integer.parseInt(request.getParameter("num"));
		//�꽭�뀡�뿉 ���옣�맂 濡쒓렇�씤�맂 �븘�씠�뵒
		String id=(String)request.getSession().getAttribute("id");
		//�뙎湲��쓽 �젙蹂대�� �뼸�뼱���꽌 �뙎湲��쓽 �옉�꽦�옄�� 媛숈�吏� 鍮꾧탳 �븳�떎.
		String writer=cafeCommentDao.getData(num).getWriter();
		if(!writer.equals(id)) {
			throw new DBFailException("�궓�쓽 �뙎湲��쓣 �궘�젣 �븷�닔 �뾾�뒿�땲�떎.");
		}
		cafeCommentDao.delete(num);
	}

	@Override
	public void updateComment(CafeCommentDto dto) {
		cafeCommentDao.update(dto);
	}

	@Override
	public void moreCommentList(HttpServletRequest request) {
		//�뙆�씪誘명꽣濡� �쟾�떖�맂 pageNum 怨� ref_group 踰덊샇瑜� �씫�뼱�삩�떎. 
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		int ref_group=Integer.parseInt(request.getParameter("ref_group"));

		CafeDto dto=cafeDao.getData(ref_group);
		request.setAttribute("dto", dto);

		/* �븘�옒�뒗 �뙎湲� �럹�씠吏� 泥섎━ 愿��젴 鍮꾩쫰�땲�뒪 濡쒖쭅 �엯�땲�떎.*/
		final int PAGE_ROW_COUNT=5;

		//蹂댁뿬以� �럹�씠吏� �뜲�씠�꽣�쓽 �떆�옉 ResultSet row 踰덊샇
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//蹂댁뿬以� �럹�씠吏� �뜲�씠�꽣�쓽 �걹 ResultSet row 踰덊샇
		int endRowNum=pageNum*PAGE_ROW_COUNT;

		//�쟾泥� row �쓽 媛��닔瑜� �씫�뼱�삩�떎.
		//�옄�꽭�엳 蹂댁뿬以� 湲��쓽 踰덊샇媛� ref_group  踰덊샇 �씠�떎. 
		int totalRow=cafeCommentDao.getCount(ref_group);
		//�쟾泥� �럹�씠吏��쓽 媛��닔 援ы븯湲�
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);

		// CafeCommentDto 媛앹껜�뿉 �쐞�뿉�꽌 怨꾩궛�맂 startRowNum 怨� endRowNum �쓣 �떞�뒗�떎.
		CafeCommentDto commentDto=new CafeCommentDto();
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);
		//ref_group 踰덊샇�룄 �떞�뒗�떎.
		commentDto.setRef_group(ref_group);

		//DB �뿉�꽌 �뙎湲� 紐⑸줉�쓣 �뼸�뼱�삩�떎.
		List<CafeCommentDto> commentList=cafeCommentDao.getList(commentDto);
		//request �뿉 �떞�븘以��떎.
		request.setAttribute("commentList", commentList);
		request.setAttribute("totalPageCount", totalPageCount);		
	}
}



