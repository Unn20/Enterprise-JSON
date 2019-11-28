package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class JsonTransformerDecoratorExcept extends JsonTransformerDecorator{
    private ArrayList<String> keys;

    public JsonTransformerDecoratorExcept (JsonTransformer decoratedJson, ArrayList<String> keys) {
        super(decoratedJson);
        this.keys = keys;
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
        x = this.except(x, keys);
        var transformed = new Gson().toJson(x);

        return decoratedJson.transform(transformed);
    }

    // This method retrieves all but selected attributes from JSON file
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
