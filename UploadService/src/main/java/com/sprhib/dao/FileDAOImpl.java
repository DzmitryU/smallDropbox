package com.sprhib.dao;

import java.util.List;

import com.sprhib.model.FileMeta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAOImpl implements FileDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addFile(FileMeta file) {
		getCurrentSession().save(file);
	}

	public void updateFile(FileMeta file) {
		FileMeta fileToUpdate = getFile(file.getId());
		fileToUpdate.setName(file.getName());
        fileToUpdate.setSize(file.getSize());
        fileToUpdate.setDate(file.getDate());
		getCurrentSession().update(fileToUpdate);
	}

	public FileMeta getFile(String id) {
		FileMeta file = (FileMeta) getCurrentSession().get(FileMeta.class, id);
		return file;
	}

	public void deleteFile(String id) {
		FileMeta file = getFile(id);
		if (file != null)
			getCurrentSession().delete(file);
	}

	public List<FileMeta> getFiles() {
		return getCurrentSession().createQuery("from FileMeta").list();
	}

}
