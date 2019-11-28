package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JsonTransformer {
    private static Gson beautifier = new GsonBuilder().setPrettyPrinting().create();
    private static Gson minifier = new Gson();
    private ArrayList<Function<JsonObject, JsonObject>> transforms = new ArrayList<>();
    private boolean minify = false;

    // As the name suggests this method only retrieves selected attributes from JSON file
    private static JsonObject only(JsonObject x, List<String> keys) {
        var y = new JsonObject();
        for (var entry : x.entrySet()) {
            if (keys.contains(entry.getKey())) {
                y.add(entry.getKey(), entry.getValue());
            }
        }
        return y;
    }

    // This method retrieves all but selected attributes from JSON file
    private static JsonObject except(JsonObject x, List<String> keys) {
        var y = new JsonObject();
        for (var entry : x.entrySet()) {
            if (!keys.contains(entry.getKey())) {
                y.add(entry.getKey(), entry.getValue());
            }
        }
        return y;
    }

    // Applies all selected filters to our JSON file
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
        for (var f : transforms) x = f.apply(x);
        return minify ? minifier.toJson(x) : beautifier.toJson(x);
    }

    public void addBeautify() {
        minify = false;
    }

    public void addMinify() {
        minify = true;
    }

    public void addFilterOnly(List<String> keys) {
        transforms.add(x -> only(x, keys));
    }

    public void addFilterExcept(List<String> keys) {
        transforms.add(x -> except(x, keys));
    }
}

