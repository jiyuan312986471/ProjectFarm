package model;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;

import model.exception.InvalidDataException;

public class Document implements Serializable {

	private static final long serialVersionUID = 3404763898326246494L;
	
	private String documentPath;
	private Timestamp added;
	private Project project;
	
	public Document() {
	}
	
	public Document(String documentPath, Project project) throws InvalidDataException {
		setDocumentPath(documentPath);
		setAdded(new Timestamp(System.currentTimeMillis()));
		setProject(project);
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) throws InvalidDataException {
//		File file = new File(documentPath);
//		
//		if(!file.exists()) {
//			throw new InvalidDataException("File " + documentPath + " does not exists");
//		}
//		
//		if(!file.isFile()) {
//			throw new InvalidDataException("Path " + documentPath + " does not point to a file");
//		}
		
		this.documentPath = documentPath;
	}

	public Timestamp getAdded() {
		return added;
	}

	public void setAdded(Timestamp added) {
		this.added = added;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}

}
