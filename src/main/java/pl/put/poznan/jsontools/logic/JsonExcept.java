package pl.put.poznan.jsontools.logic;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.util.List;

/**
 * A concrete decorator class, that adds filtering to a JSON transformation
 * @see #transform(String) for the transformation description
 */
public class JsonExcept extends JsonDecorator {
    private List<String> keys;

    /**
     * Decorates a JsonTransformer with a filtering transformation
     * @param decoratedJson JsonTransformer to be decorated
     * @param keys Keys tat will be excluded in the JSON dictionary  after the transformation
     * @see #transform(String) for the transformation description
     */
    public JsonExcept(JsonTransformer decoratedJson, List<String> keys) {
        super(decoratedJson);
        this.keys = keys;
    }

    /**
     * Transforms a JSON string, so that the resulting JSON dictionary excludes the specified keys.
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
     * @param keys Keys to include in the output
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
