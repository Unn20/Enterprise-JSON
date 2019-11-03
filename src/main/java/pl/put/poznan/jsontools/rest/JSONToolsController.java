package pl.put.poznan.jsontools.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.jsontools.logic.JsonTransformer;

import java.util.Arrays;


@RestController
@RequestMapping("/only/{text}")
public class JSONToolsController {

    private static final Logger logger = LoggerFactory.getLogger(JSONToolsController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text, @RequestParam(value="keys", defaultValue="") String[] keys) {
        logger.debug(text);
        logger.debug(Arrays.toString(keys));
        var transformer = new JsonTransformer();
        transformer.addFilterOnly(Arrays.asList(keys));
        return transformer.transform(text);
    }

}


