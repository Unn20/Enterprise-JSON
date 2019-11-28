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

    // these functions actually do something

    private static JsonObject only(JsonObject x, List<String> keys) {
        var y = new JsonObject();
        for (var entry : x.entrySet()) {
            if (keys.contains(entry.getKey())) {
                y.add(entry.getKey(), entry.getValue());
            }
        }
        return y;
    }

    private static JsonObject except(JsonObject x, List<String> keys) {
        // if java were better... lambda x: {k: v for k, v in x.items() if k not in keys}
        var y = new JsonObject();
        for (var entry : x.entrySet()) {
            if (!keys.contains(entry.getKey())) {
                y.add(entry.getKey(), entry.getValue());
            }
        }
        return y;
    }

    public String transform(String json) {
        var x = new JsonParser().parse(json).getAsJsonObject();
        // check for errors here
        for (var f : transforms) x = f.apply(x);
        return minify ? minifier.toJson(x) : beautifier.toJson(x);
    }

    // these functions are basically useless

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

