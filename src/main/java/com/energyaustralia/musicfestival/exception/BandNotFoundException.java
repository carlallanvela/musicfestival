package com.energyaustralia.musicfestival.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Customized Exception Class for Band.
 * @author Carl Allan Vela
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BandNotFoundException extends RuntimeException {
	public BandNotFoundException(String message) {
		super(message);
	}
}
