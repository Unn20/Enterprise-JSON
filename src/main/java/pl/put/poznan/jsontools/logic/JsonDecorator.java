package pl.put.poznan.jsontools.logic;

/**
 * JsonTransformer decorator class
 */
abstract class JsonDecorator implements JsonTransformer {
    /**
     * The decorated JsonTransformer
     */
    JsonTransformer decoratedJson;

    /**
     * Decorates a JsonTransformer with itself
     * @param jsonTransformer JsonTransformer to be decorated
     */
    JsonDecorator(JsonTransformer jsonTransformer) {
        this.decoratedJson = jsonTransformer;
    }
}
