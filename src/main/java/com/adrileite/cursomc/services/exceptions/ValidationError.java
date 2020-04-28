package com.adrileite.cursomc.services.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List <FieldMessage> errors = new ArrayList<>();
	
	
	
	public List<FieldMessage> getErrors() {
		return errors;
	}



	public void setList(List<FieldMessage> list) {
		this.errors = errors;
	}



	public ValidationError(Integer status, String msg, Long timeStamp){
		super (status , msg, timeStamp);
	}
	
	public void addError (String fieldName, String mensagem) {
		errors.add(new FieldMessage(fieldName, mensagem));
	}

}
