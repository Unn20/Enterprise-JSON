package pl.put.poznan.jsontools.logic;

import com.google.gson.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;

public class JsonTransformer {
    private static Gson beautifier = new GsonBuilder().setPrettyPrinting().create();
    private static Gson minifier = new Gson();
    private ArrayList<Function<JsonObject, JsonObject>> transforms = new ArrayList<>();
    private boolean minify = false;

    // these functions actually do something

    private static Function<JsonObject, JsonObject> only(List<String> keys) {
        return (JsonObject x) -> {
            var y = new JsonObject();
            for (var entry : x.entrySet()) {
                if (keys.contains(entry.getKey())) {
                    y.add(entry.getKey(), entry.getValue());
                }
            }
            return y;
        };
    }

    private static Function<JsonObject, JsonObject> except(List<String> keys) {
        // if java were better... lambda x: {k: v for k, v in x.items() if k not in keys}
        return (JsonObject x) -> {
            var y = new JsonObject();
            for (var entry : x.entrySet()) {
                if (!keys.contains(entry.getKey())) {
                    y.add(entry.getKey(), entry.getValue());
                }
            }
            return y;
        };
    }

    public String transform(String json) {
        var x = new JsonParser().parse(json).getAsJsonObject();
        // check for errors here
        for (var f : transforms) x = f.apply(x);
        return minify ? minifier.toJson(x) : beautifier.toJson(x);
    }

    // these functions are basically useless

    public void addBeautify() {
        minify = true;
    }

    public void addMinify() {
        minify = false;
    }

    public void addFilterOnly(List<String> keys) {
        transforms.add(only(keys));
    }

    public void addFilterExcept(List<String> keys) {
        transforms.add(except(keys));
    }
}

