package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

/**
 * A concrete decorator class, that adds minifying to a JSON transformation
 * @see #transform(String) for the transformation description
 */
public class JsonMinify extends JsonDecorator {
    /**
     * Decorates a JsonTransformer with a minifying transformation
     * @param decoratedJson JsonTransformer to be decorated
     * @see #transform(String) for the transformation description
     */
    public JsonMinify(JsonTransformer decoratedJson) {
        super(decoratedJson);
    }

    /**
     * Minifies the given JSON string
     * @param json JSON string to be minifed
     * @return Minified JSON string
     * @throws JsonSyntaxException Thrown if given an valid JSON string
     */
    public String transform(String json) throws JsonSyntaxException {
        var transformed = decoratedJson.transform(json);
        var jsonObject = new Gson().fromJson(transformed, JsonObject.class);
        return new GsonBuilder().create().toJson(jsonObject);
    }
}
