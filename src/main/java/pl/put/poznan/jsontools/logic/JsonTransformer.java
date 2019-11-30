package pl.put.poznan.jsontools.logic;

import com.google.gson.JsonSyntaxException;

/**
 * Interface specifying a JSON transformer
 */
public interface JsonTransformer {
    /**
     * Transforms the given JSON string
     * @param json JSON string to be transformed
     * @return Transformed JSON string
     * @throws JsonSyntaxException Thrown if given an valid JSON string
     */
    String transform(String json) throws JsonSyntaxException;
}
