package com.egov.citizen.exception;

public class AddressRegistrationException extends RuntimeException {
    public AddressRegistrationException(String message) {
        super(message);
    }

    public AddressRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
