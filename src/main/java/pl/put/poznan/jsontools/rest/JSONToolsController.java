package pl.put.poznan.jsontools.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.jsontools.logic.JsonTransformer;

import java.util.Arrays;

@RestController
public class JSONToolsController {

    private static final Logger logger = LoggerFactory.getLogger(JSONToolsController.class);

    @RequestMapping(value="/only/{text}", method = RequestMethod.GET, produces = "application/json")
    public String getOnly(@PathVariable String text, @RequestParam(value="keys", defaultValue="") String[] keys) {
        logger.debug(text);
        logger.debug(Arrays.toString(keys));
        var transformer = new JsonTransformer();
        transformer.addFilterOnly(Arrays.asList(keys));
        return transformer.transform(text);
    }

    @RequestMapping(value="/except/{text}", method = RequestMethod.GET, produces = "application/json")
    public String getExcept(@PathVariable String text, @RequestParam(value="keys", defaultValue="") String[] keys) {
        logger.debug(text);
        logger.debug(Arrays.toString(keys));
        var transformer = new JsonTransformer();
        transformer.addFilterExcept(Arrays.asList(keys));
        return transformer.transform(text);
    }

    @RequestMapping(value="/beautify/{text}", method = RequestMethod.GET, produces = "application/json")
    public String getBeautify(@PathVariable String text) {
        logger.debug(text);
        var transformer = new JsonTransformer();
        transformer.addBeautify();
        return transformer.transform(text);
    }

    @RequestMapping(value="/minify/{text}", method = RequestMethod.GET, produces = "application/json")
    public String getMinify(@PathVariable String text) {
        logger.debug(text);
        var transformer = new JsonTransformer();
        transformer.addMinify();
        return transformer.transform(text);
    }

}


