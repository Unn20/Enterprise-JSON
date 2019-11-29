package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

import java.util.*;

public class JsonExcept extends JsonDecorator {
    private List<String> keys;

    public JsonExcept(JsonTransformer decoratedJson, List<String> keys) {
        super(decoratedJson);
        this.keys = keys;
    }

    /**
     * Transforms a JSON string, so that the resulting JSON dictionary doesn't include the specified keys.
     * Keys are filtered only at the first level of the JSON dictionary.
     * @param json JSON string to be transformed
     * @return Transformed JSON string
     * @throws JsonSyntaxException Thrown if given an invalid JSON string
     */
    public String transform(String json) throws JsonSyntaxException {
        var jsonObject = new Gson().fromJson(json, JsonObject.class);
        var transformed = new Gson().toJson(except(jsonObject, keys));
        return decoratedJson.transform(transformed);
    }

    /**
     * Return a JSON object, excluding the specified keys.
     * Keys are filtered only at the first level of the JSON dictionary.
     * @param x A JSON object to be filtered
     * @param keys Keys to exclude in the output
     * @return The filtered JSON object
     */
    private JsonObject except(JsonObject x, List<String> keys) {
        var y = new JsonObject();
        for (var entry : x.entrySet()) {
            if (!keys.contains(entry.getKey())) {
                y.add(entry.getKey(), entry.getValue());
            }
        }
        return y;
    }
}
