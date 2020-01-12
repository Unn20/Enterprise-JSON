package pl.put.poznan.jsontools.logic;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonMinifyTest {

    @Test
    void transform() {
        var res = new JsonMinify(new JsonTransform()).transform("{}");
        Assert.assertEquals(res, "{}");

        res = new JsonMinify(new JsonTransform()).transform("{\n  \"a\": \"b\"\n}");
        Assert.assertEquals(res, "{\"a\":\"b\"}");

        res = new JsonMinify(new JsonTransform()).transform("{\n  \"integer\": 1\n}");
        Assert.assertEquals(res, "{\"integer\":1}");

        res = new JsonMinify(new JsonTransform()).transform("{\n  \"boolean\": false\n}");
        Assert.assertEquals(res, "{\"boolean\":false}");

        res = new JsonMinify(new JsonTransform()).transform("{\n  \"list\": [\n    \"a\",\n    \"b\",\n    \"c\"\n  ]\n}");
        Assert.assertEquals(res, "{\"list\":[\"a\",\"b\",\"c\"]}");

        res = new JsonMinify(new JsonTransform()).transform("{\n  \"nested\": {\n    \"a\": \"b\"\n  }\n}");
        Assert.assertEquals(res, "{\"nested\":{\"a\":\"b\"}}");

        res = new JsonMinify(new JsonTransform()).transform("{\n  \"dnested\": {\n    \"nest\": {\n      \"end\": 0\n    }\n  }\n}");
        Assert.assertEquals(res, "{\"dnested\":{\"nest\":{\"end\":0}}}");

        res = new JsonMinify(new JsonTransform()).transform("{\n  \"title\": \"Nowy\",\n  \"price\": \"100\",\n  \"author\": \"Malinowski\"\n}");
        Assert.assertEquals(res, "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}");

        res = new JsonMinify(new JsonTransform()).transform("{\n  \"phoneNumbers\": [\n    {\n      \"type\": \"home\",\n      \"number\": \"212 555-1234\"\n    },\n    {\n      \"type\": \"office\",\n      \"number\": \"646 555-4567\"\n    }\n  ]\n}");
        Assert.assertEquals(res, "{\"phoneNumbers\":[{\"type\":\"home\",\"number\":\"212 555-1234\"},{\"type\":\"office\",\"number\":\"646 555-4567\"}]}");

        res = new JsonMinify(new JsonTransform()).transform("{\n  \"firstName\": \"John\",\n  \"lastName\": \"Smith\",\n  \"isAlive\": true,\n  \"age\": 27,\n  \"address\": {\n    \"streetAddress\": \"21 2nd Street\",\n    \"city\": \"New York\",\n    \"state\": \"NY\",\n    \"postalCode\": \"10021-3100\"\n  }\n}");
        Assert.assertEquals(res, "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"isAlive\":true,\"age\":27,\"address\":{\"streetAddress\":\"21 2nd Street\",\"city\":\"New York\",\"state\":\"NY\",\"postalCode\":\"10021-3100\"}}");

    }
}