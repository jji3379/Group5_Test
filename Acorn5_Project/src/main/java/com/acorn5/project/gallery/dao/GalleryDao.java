package com.acorn5.project.gallery.dao;

import java.util.List;

import com.acorn5.project.gallery.dto.GalleryDto;

public interface GalleryDao {
	public GalleryDto getData(int num);
	public void insert(GalleryDto dto);
	public List<GalleryDto> getList(GalleryDto dto);
	public int getCount();
}
