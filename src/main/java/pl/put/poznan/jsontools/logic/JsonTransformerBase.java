package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JsonTransformerBase implements JsonTransformer {

    // Applies transform JSON file
    public String transform(String json) {
        JsonElement xel;
        // Checking for errors in JSON file
        try {
            xel = new JsonParser().parse(json);
        }
        catch (JsonSyntaxException e) {
            return "Error: Wrong JSON file syntax";
        }
        var x = xel.getAsJsonObject();

        return new Gson().toJson(x);
    }
}

