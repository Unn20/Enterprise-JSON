package pl.put.poznan.jsontools.rest;

import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.jsontools.logic.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class JSONToolsController {
    private static final Logger logger = LoggerFactory.getLogger(JSONToolsController.class);
    private static final String SYNTAX_ERROR = "syntax error";

    @RequestMapping(value = "/only", method = RequestMethod.POST, produces = "application/json", headers = {"content-type=text/plain"})
    public ResponseEntity getOnly(@RequestBody String text, @RequestParam(value = "keys", defaultValue = "") String[] keys) {
        logger.debug(text);
        logger.debug(Arrays.toString(keys));
        try {
            var res = new JsonOnly(new JsonTransform(), Arrays.asList(keys)).transform(text);
            return ResponseEntity.ok(res);
        } catch (JsonSyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SYNTAX_ERROR);
        }
    }

    @RequestMapping(value = "/except", method = RequestMethod.POST, produces = "application/json", headers = {"content-type=text/plain"})
    public ResponseEntity getExcept(@RequestBody String text, @RequestParam(value = "keys", defaultValue = "") String[] keys) {
        logger.debug(text);
        logger.debug(Arrays.toString(keys));
        try {
            var res = new JsonExcept(new JsonTransform(), Arrays.asList(keys)).transform(text);
            return ResponseEntity.ok(res);
        } catch (JsonSyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SYNTAX_ERROR);
        }
    }
    @RequestMapping(value = "/beautify", method = RequestMethod.POST, produces = "application/json", headers = {"content-type=text/plain"})
    public ResponseEntity getBeautify(@RequestBody String text) {
        logger.debug(text);
        try {
            var res = new JsonBeautify(new JsonTransform()).transform(text);
            return ResponseEntity.ok(res);
        } catch (JsonSyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SYNTAX_ERROR);
        }
    }

    @RequestMapping(value = "/minify", method = RequestMethod.POST, produces = "application/json", headers = {"content-type=text/plain"})
    public ResponseEntity getMinify(@RequestBody String text) {
        logger.debug(text);
        try {
            var res = new JsonMinify(new JsonTransform()).transform(text);
            return ResponseEntity.ok(res);
        } catch (JsonSyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SYNTAX_ERROR);
        }
    }

    @RequestMapping(value = "/compare", method = RequestMethod.POST, produces = "application/json", headers = {"content-type=text/plain"})
    public ResponseEntity getCompare(@RequestBody String text) {
        logger.debug(text);
        var texts = text.split("\0\0\0");
        var res = TextComparator.compare(texts[0], texts[1]);
        return ResponseEntity.ok(res);
    }
}

