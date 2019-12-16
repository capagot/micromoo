package org.micromoo.account;

import com.google.gson.*;

public final class RESTResponse {
    public RESTResponse(final ResponseStatus response_status) {
        this.response_status = response_status;
    };

    public RESTResponse(final ResponseStatus response_status, final String message) {
        this.response_status = response_status;
        this.message = message;
    };

    public RESTResponse(final ResponseStatus response_status, final JsonElement data) {
        this.response_status = response_status;
        this.data = data;
    };

    private ResponseStatus response_status;
    private String message;
    private JsonElement data;
}
