package pl.put.poznan.jsontools.logic;

import com.google.gson.JsonSyntaxException;

/**
 * A base JSON transformer (concrete component), that does nothing.
 */
public class JsonTransform implements JsonTransformer {
    /**
     * Returns the given JSON string unchanged
     * @param json Input JSON string
     * @return Input JSON string
     */
    public String transform(String json) throws JsonSyntaxException {
        return json;
    }
}

