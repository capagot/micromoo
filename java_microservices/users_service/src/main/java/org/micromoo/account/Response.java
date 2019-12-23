package org.micromoo.account;

import com.google.gson.*;

public class Response {
    public Response(final ResponseStatus response_status) {
        this.response_status = response_status;
    };

    public Response(final ResponseStatus response_status, final String message) {
        this.response_status = response_status;
        this.message = message;
    };

    public Response(final ResponseStatus response_status, final JsonElement data) {
        this.response_status = response_status;
        this.data = data;
    };

    private ResponseStatus response_status;
    private String message;
    private JsonElement data;
}
