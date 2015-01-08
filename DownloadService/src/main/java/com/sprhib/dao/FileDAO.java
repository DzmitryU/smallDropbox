package com.sprhib.dao;

import java.util.List;

import com.sprhib.model.FileMeta;

public interface FileDAO {
	
	public void addFile(FileMeta file);
	public void updateFile(FileMeta file);
	public FileMeta getFile(String id);
	public void deleteFile(String id);
	public List<FileMeta> getFiles();

}
