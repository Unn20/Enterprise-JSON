package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

public class JsonBeautify extends JsonDecorator {
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
