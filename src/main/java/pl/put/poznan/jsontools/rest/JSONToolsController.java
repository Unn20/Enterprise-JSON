package pl.put.poznan.jsontools.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.jsontools.logic.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class JSONToolsController {

    private static final Logger logger = LoggerFactory.getLogger(JSONToolsController.class);

    @RequestMapping(value = "/only/{text}", method = RequestMethod.GET, produces = "application/json")
    public String getOnly(@PathVariable String text, @RequestParam(value = "keys", defaultValue = "") String[] keys) {
        logger.debug(text);
        logger.debug(Arrays.toString(keys));
        var transformer = new JsonTransformerDecoratorOnly(new JsonTransformerBase(), new ArrayList<>(Arrays.asList(keys)));
        return transformer.transform(text);
    }

    @RequestMapping(value = "/beautify/only/{text}", method = RequestMethod.GET, produces = "application/json")
    public String getBeautifyOnly(@PathVariable String text, @RequestParam(value = "keys", defaultValue = "") String[] keys) {
        logger.debug(text);
        logger.debug(Arrays.toString(keys));
        var transformer = new JsonTransformerDecoratorBeautify(new JsonTransformerDecoratorOnly(new JsonTransformerBase(), new ArrayList<>(Arrays.asList(keys))));
        return transformer.transform(text);
    }

    @RequestMapping(value = "/except/{text}", method = RequestMethod.GET, produces = "application/json")
    public String getExcept(@PathVariable String text, @RequestParam(value = "keys", defaultValue = "") String[] keys) {
        logger.debug(text);
        logger.debug(Arrays.toString(keys));
        var transformer = new JsonTransformerDecoratorExcept(new JsonTransformerBase(), new ArrayList<>(Arrays.asList(keys)));
        return transformer.transform(text);
    }

    @RequestMapping(value = "/beautify/except/{text}", method = RequestMethod.GET, produces = "application/json")
    public String getBeautifyExcept(@PathVariable String text, @RequestParam(value = "keys", defaultValue = "") String[] keys) {
        logger.debug(text);
        logger.debug(Arrays.toString(keys));
        var transformer = new JsonTransformerDecoratorBeautify(new JsonTransformerDecoratorExcept(new JsonTransformerBase(), new ArrayList<>(Arrays.asList(keys))));
        return transformer.transform(text);
    }

    @RequestMapping(value = "/beautify/{text}", method = RequestMethod.GET, produces = "application/json")
    public String getBeautify(@PathVariable String text) {
        logger.debug(text);
        var transformer = new JsonTransformerDecoratorBeautify(new JsonTransformerBase());
        return transformer.transform(text);
    }

    @RequestMapping(value = "/minify/{text}", method = RequestMethod.GET, produces = "application/json")
    public String getMinify(@PathVariable String text) {
        logger.debug(text);
        var transformer = new JsonTransformerDecoratorMinify(new JsonTransformerBase());
        return transformer.transform(text);
    }

}


