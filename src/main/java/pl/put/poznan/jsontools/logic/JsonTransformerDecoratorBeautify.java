package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

public class JsonTransformerDecoratorBeautify extends JsonTransformerDecorator {
    public JsonTransformerDecoratorBeautify(JsonTransformer decoratedJson) {
        super(decoratedJson);
    }

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

        var  transformed = decoratedJson.transform(json);

        var xel2 = new JsonParser().parse(transformed);

        return new GsonBuilder().setPrettyPrinting().create().toJson(xel2);
    }
}
