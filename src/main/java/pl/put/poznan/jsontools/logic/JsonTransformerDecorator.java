package pl.put.poznan.jsontools.logic;

abstract class JsonTransformerDecorator implements JsonTransformer {
    JsonTransformer decoratedJson;

    JsonTransformerDecorator(JsonTransformer jsonTransformer) {
        this.decoratedJson = jsonTransformer;
    }
}
