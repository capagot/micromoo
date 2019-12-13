package user_manager;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;

public class RESTResponse {
    public RESTResponse(ResponseStatus response_status) {};
    public RESTResponse(ResponseStatus response_status, String message) {};
    public RESTResponse(ResponseStatus response_status, JsonElement data) {};

    private ResponseStatus response_status;
    private String message;
    private JsonElement data;
}