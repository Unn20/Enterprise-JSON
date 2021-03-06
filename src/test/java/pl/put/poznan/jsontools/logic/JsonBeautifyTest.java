package pl.put.poznan.jsontools.logic;

import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.Test;

class JsonBeautifyTest {
    @Test
    void testBeautifyEmptyJson() {
        var res = new JsonBeautify(new JsonTransform()).transform("{}");
        Assert.assertEquals(res, "{}");
    }
    @Test
    void testBeautifyChar() {
        var res = new JsonBeautify(new JsonTransform()).transform("{\"a\":\"b\"}");
        Assert.assertEquals(res, "{\n  \"a\": \"b\"\n}");
    }
    @Test
    void testBeautifyInteger() {
        var res = new JsonBeautify(new JsonTransform()).transform("{\"integer\":1}");
        Assert.assertEquals(res, "{\n  \"integer\": 1\n}");
    }
    @Test
    void testBeautifyBoolean() {
        var res = new JsonBeautify(new JsonTransform()).transform("{\"boolean\":false}");
        Assert.assertEquals(res, "{\n  \"boolean\": false\n}");
    }
    @Test
    void testBeautifyList() {
        var res = new JsonBeautify(new JsonTransform()).transform("{\"list\":[a,b,c]}");
        Assert.assertEquals(res, "{\n  \"list\": [\n    \"a\",\n    \"b\",\n    \"c\"\n  ]\n}");
    }
    @Test
    void testBeautifyNestedJson() {
        var res = new JsonBeautify(new JsonTransform()).transform("{\"nested\":{\"a\":\"b\"}}");
        Assert.assertEquals(res, "{\n  \"nested\": {\n    \"a\": \"b\"\n  }\n}");
    }
    @Test
    void testBeautifyDoubleNestedJson() {
        var res = new JsonBeautify(new JsonTransform()).transform("{\"dnested\":{\"nest\":{\"end\":0}}}");
        Assert.assertEquals(res, "{\n  \"dnested\": {\n    \"nest\": {\n      \"end\": 0\n    }\n  }\n}");
    }
    @Test
    void testBeautifyExtendedJson1() {
        var res = new JsonBeautify(new JsonTransform()).transform("{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}");
        Assert.assertEquals(res, "{\n  \"title\": \"Nowy\",\n  \"price\": \"100\",\n  \"author\": \"Malinowski\"\n}");
    }
    @Test
    void testBeautifyExtendedJson2() {
        var res = new JsonBeautify(new JsonTransform()).transform("{\"phoneNumbers\":[{\"type\":\"home\",\"number\":\"212 555-1234\"},{\"type\":\"office\",\"number\":\"646 555-4567\"}]}");
        Assert.assertEquals(res, "{\n  \"phoneNumbers\": [\n    {\n      \"type\": \"home\",\n      \"number\": \"212 555-1234\"\n    },\n    {\n      \"type\": \"office\",\n      \"number\": \"646 555-4567\"\n    }\n  ]\n}");
    }
    @Test
    void testBeautifyExtendedJson3() {
        var res = new JsonBeautify(new JsonTransform()).transform("{\"firstName\":\"John\",\"lastName\":\"Smith\",\"isAlive\":true,\"age\":27,\"address\":{\"streetAddress\":\"21 2nd Street\",\"city\":\"New York\",\"state\":\"NY\",\"postalCode\":\"10021-3100\"}}");
        Assert.assertEquals(res, "{\n  \"firstName\": \"John\",\n  \"lastName\": \"Smith\",\n  \"isAlive\": true,\n  \"age\": 27,\n  \"address\": {\n    \"streetAddress\": \"21 2nd Street\",\n    \"city\": \"New York\",\n    \"state\": \"NY\",\n    \"postalCode\": \"10021-3100\"\n  }\n}");
    }
}