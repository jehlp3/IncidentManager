package br.com.incidentemanager.helpdesk.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public BusinessException(String error) {
		super(error);
	}
}