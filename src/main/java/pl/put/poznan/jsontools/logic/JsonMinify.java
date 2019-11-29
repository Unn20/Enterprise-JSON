package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

public class JsonMinify extends JsonDecorator {
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
