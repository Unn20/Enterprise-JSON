package pl.put.poznan.jsontools.logic;

abstract class JsonDecorator implements JsonTransformer {
    JsonTransformer decoratedJson;

    JsonDecorator(JsonTransformer jsonTransformer) {
        this.decoratedJson = jsonTransformer;
    }
}
