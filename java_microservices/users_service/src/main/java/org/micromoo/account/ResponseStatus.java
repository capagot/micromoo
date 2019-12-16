package org.micromoo.account;

public enum ResponseStatus {
    SUCCESS("Success"),
    ERROR("Error");

    ResponseStatus(String status) {
        this.status = status;
    }

    private String status;
}
