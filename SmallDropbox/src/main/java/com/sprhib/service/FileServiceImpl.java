package com.sprhib.service;

import java.util.List;

import com.sprhib.dao.FileDAO;
import com.sprhib.model.FileMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileDAO fileDAO;

	public void addFile(FileMeta file) {
		fileDAO.addFile(file);
	}

	public void updateFile(FileMeta file) {
		fileDAO.updateFile(file);
	}

	public FileMeta getFile(String id) {
		return fileDAO.getFile(id);
	}

	public void deleteFile(String id) {
		fileDAO.deleteFile(id);
	}

	public List<FileMeta> getFiles() {
		return fileDAO.getFiles();
	}
}
