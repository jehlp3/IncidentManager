package br.com.incidentemanager.helpdesk.exception;


public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public ObjectNotFoundException(String error) {
        super(error);
    }


}