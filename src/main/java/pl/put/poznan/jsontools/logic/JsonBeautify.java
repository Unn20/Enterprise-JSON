package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

/**
 * A concrete decorator class, that adds beautifying to a JSON transformation
 * @see #transform(String) for the transformation description
 */
public class JsonBeautify extends JsonDecorator {
    /**
     * Decorates a JsonTransformer with a beautifying transformation
     * @param decoratedJson JsonTransformer to be decorated
     * @see #transform(String) for the transformation description
     */
    public JsonBeautify(JsonTransformer decoratedJson) {
        super(decoratedJson);
    }

    /**
     * Beautifies the given JSON string
     * @param json JSON string to be beautified
     * @return Beautifed JSON string
     * @throws JsonSyntaxException Thrown if given an invalid JSON string
     */
    public String transform(String json) throws JsonSyntaxException {
        var  transformed = decoratedJson.transform(json);
        var jsonObject = new Gson().fromJson(json, JsonObject.class);
        return new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject);
    }
}
