package com.SG.Stealth.Garage.API.controllers.exceptions;

public class ResourceNotFoundException extends RuntimeException {3
    public static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id " + id);
    }
}
