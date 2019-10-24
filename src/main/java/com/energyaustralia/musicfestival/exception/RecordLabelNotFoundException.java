package com.energyaustralia.musicfestival.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Customized Exception Class for Record Label.
 * @author Carl Allan Vela
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordLabelNotFoundException extends RuntimeException {

	public RecordLabelNotFoundException(String message) {
		super(message);
	}
}
