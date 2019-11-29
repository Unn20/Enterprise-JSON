package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class JsonTransformerDecoratorOnly extends JsonTransformerDecorator{
    private ArrayList<String> keys;

    public JsonTransformerDecoratorOnly (JsonTransformer decoratedJson, ArrayList<String> keys) {
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
        x = this.only(x, keys);
        var transformed = new Gson().toJson(x);

        return decoratedJson.transform(transformed);
    }

    // As the name suggests this method only retrieves selected attributes from JSON file
    private JsonObject only(JsonObject x, List<String> keys) {
        var y = new JsonObject();
        for (var entry : x.entrySet()) {
            if (keys.contains(entry.getKey())) {
                y.add(entry.getKey(), entry.getValue());
            }
        }
        return y;
    }
}
