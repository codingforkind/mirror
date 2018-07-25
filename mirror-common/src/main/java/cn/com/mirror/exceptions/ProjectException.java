package cn.com.mirror.exceptions;

public class ProjectException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ProjectException(String exception) {
		super(exception);
	}

}
