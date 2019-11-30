package pl.put.poznan.jsontools.rest;

import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.jsontools.logic.*;

import java.util.*;

@RestController
public class JSONToolsController {
    private static final Logger logger = LoggerFactory.getLogger(JSONToolsController.class);
    private static final String SYNTAX_ERROR = "syntax error";

    @RequestMapping(value = "/only/{text}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getOnly(@PathVariable String text, @RequestParam(value = "keys", defaultValue = "") String[] keys) {
        logger.debug(text);
        logger.debug(Arrays.toString(keys));
        try {
            var res = new JsonOnly(new JsonTransform(), Arrays.asList(keys)).transform(text);
            return ResponseEntity.ok(res);
        } catch (JsonSyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SYNTAX_ERROR);
        }
    }

    @RequestMapping(value = "/beautify/{text}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBeautify(@PathVariable String text) {
        logger.debug(text);
        try {
            var res = new JsonBeautify(new JsonTransform()).transform(text);
            return ResponseEntity.ok(res);
        } catch (JsonSyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SYNTAX_ERROR);
        }
    }

    @RequestMapping(value = "/minify/{text}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getMinify(@PathVariable String text) {
        logger.debug(text);
        try {
            var res = new JsonMinify(new JsonTransform()).transform(text);
            return ResponseEntity.ok(res);
        } catch (JsonSyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SYNTAX_ERROR);
        }
    }
}

