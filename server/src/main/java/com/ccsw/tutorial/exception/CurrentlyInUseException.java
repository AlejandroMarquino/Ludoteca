package com.ccsw.tutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already exists")
public class CurrentlyInUseException extends Exception {

    /**
     * Autocompletado por el IDE
     */
    private static final long serialVersionUID = -7852477718996577918L;

}
