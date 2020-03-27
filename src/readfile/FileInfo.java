package readfile;

import java.io.File;
import java.io.Serializable;

public class FileInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeRequest;
	private File file[];
	private String fileName;
	private String readLine;
	
	public String getTypeRequest() {
		return typeRequest;
	}
	public void setTypeRequest(String typeRequest) {
		this.typeRequest = typeRequest;
	}
	public File[] getFile() {
		return file;
	}
	public void setFile(File[] file) {
		this.file = file;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getReadLine() {
		return readLine;
	}
	public void setReadLine(String readLine) {
		this.readLine = readLine;
	}
}
