package com.acorn5.project.exception;
/*
 *  �씪諛섏쟻�씤 �삁�쇅 �겢�옒�뒪�뒗 RuntimeException �겢�옒�뒪瑜� �긽�냽 諛쏆븘�꽌 留뚮뱺�떎.
 *  - �깮�꽦�옄�쓽 �씤�옄濡� �삁�쇅 硫붿꽭吏�瑜� �쟾�떖諛쏆븘�꽌 遺�紐� �깮�꽦�옄�뿉 �꽆寃⑥＜硫�
 *    .getMessage() 硫붿냼�뱶瑜� �샇異쒗빐�꽌 �삁�쇅 硫붿꽭吏�瑜� 諛쏆븘 媛덉닔 �엳�떎.
 */
public class DBFailException extends RuntimeException{
	//�깮�꽦�옄
	public DBFailException(String message) {
		super(message);
	}
}