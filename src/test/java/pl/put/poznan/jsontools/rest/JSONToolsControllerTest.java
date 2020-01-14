package pl.put.poznan.jsontools.rest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.put.poznan.jsontools.logic.JsonExcept;
import pl.put.poznan.jsontools.logic.JsonOnly;
import pl.put.poznan.jsontools.logic.JsonTransform;

import java.util.Arrays;

class JSONToolsControllerTest {
    @Test
    void getOnlyNoKeys() {
        JSONToolsController jt = mock(JSONToolsController.class);
        String[] keys = {""};
        String value = "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}";

        var res = new JsonOnly(new JsonTransform(), Arrays.asList(keys)).transform(value);
        when(jt.getOnly(value, keys)).thenReturn(ResponseEntity.ok(res));

        ResponseEntity<String> OKResponse = new ResponseEntity<>("{}", HttpStatus.OK);
        Assert.assertEquals(OKResponse, jt.getOnly(value, keys));
    }
    @Test
    void getOnlyOneKey() {
        JSONToolsController jt = mock(JSONToolsController.class);
        String[] keys = {"title"};
        String value = "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}";

        var res = new JsonOnly(new JsonTransform(), Arrays.asList(keys)).transform(value);
        when(jt.getOnly(value, keys)).thenReturn(ResponseEntity.ok(res));

        ResponseEntity<String> OKResponse = new ResponseEntity<>("{\"title\":\"Nowy\"}", HttpStatus.OK);
        Assert.assertEquals(OKResponse, jt.getOnly(value, keys));
    }
    @Test
    void getOnlyTwoKey() {
        JSONToolsController jt = mock(JSONToolsController.class);
        String[] keys = {"title", "price"};
        String value = "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}";

        var res = new JsonOnly(new JsonTransform(), Arrays.asList(keys)).transform(value);
        when(jt.getOnly(value, keys)).thenReturn(ResponseEntity.ok(res));

        ResponseEntity<String> OKResponse = new ResponseEntity<>("{\"title\":\"Nowy\",\"price\":\"100\"}", HttpStatus.OK);
        Assert.assertEquals(OKResponse, jt.getOnly(value, keys));
    }
    @Test
    void getOnlyThreeKeys() {
        JSONToolsController jt = mock(JSONToolsController.class);
        String[] keys = {"title", "price", "author"};
        String value = "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}";

        var res = new JsonOnly(new JsonTransform(), Arrays.asList(keys)).transform(value);
        when(jt.getOnly(value, keys)).thenReturn(ResponseEntity.ok(res));

        ResponseEntity<String> OKResponse = new ResponseEntity<>("{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}", HttpStatus.OK);
        Assert.assertEquals(OKResponse, jt.getOnly(value, keys));
    }
    @Test
    void getOnlySyntaxError() {
        JSONToolsController jt = mock(JSONToolsController.class);
        String[] keys = {"author"};
        String value = "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"";

        when(jt.getOnly(value, keys)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("syntax error"));

        ResponseEntity<String> noOKResponse = new ResponseEntity<>("syntax error", HttpStatus.BAD_REQUEST);
        Assert.assertEquals(noOKResponse, jt.getOnly(value, keys));
    }


    @Test
    void getExceptNoKeys() {
        JSONToolsController jt = mock(JSONToolsController.class);
        String[] keys = {""};
        String value = "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}";

        var res = new JsonExcept(new JsonTransform(), Arrays.asList(keys)).transform(value);
        when(jt.getExcept(value, keys)).thenReturn(ResponseEntity.ok(res));

        ResponseEntity<String> OKResponse = new ResponseEntity<>("{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}", HttpStatus.OK);
        Assert.assertEquals(OKResponse, jt.getExcept(value, keys));
    }
    @Test
    void getExceptOneKey() {
        JSONToolsController jt = mock(JSONToolsController.class);
        String[] keys = {"title"};
        String value = "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}";

        var res = new JsonExcept(new JsonTransform(), Arrays.asList(keys)).transform(value);
        when(jt.getExcept(value, keys)).thenReturn(ResponseEntity.ok(res));

        ResponseEntity<String> OKResponse = new ResponseEntity<>("{\"price\":\"100\",\"author\":\"Malinowski\"}", HttpStatus.OK);
        Assert.assertEquals(OKResponse, jt.getExcept(value, keys));
    }
    @Test
    void getExceptTwoKey() {
        JSONToolsController jt = mock(JSONToolsController.class);
        String[] keys = {"title", "price"};
        String value = "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}";

        var res = new JsonExcept(new JsonTransform(), Arrays.asList(keys)).transform(value);
        when(jt.getExcept(value, keys)).thenReturn(ResponseEntity.ok(res));

        ResponseEntity<String> OKResponse = new ResponseEntity<>("{\"author\":\"Malinowski\"}", HttpStatus.OK);
        Assert.assertEquals(OKResponse, jt.getExcept(value, keys));
    }
    @Test
    void getExceptThreeKeys() {
        JSONToolsController jt = mock(JSONToolsController.class);
        String[] keys = {"title", "price", "author"};
        String value = "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"}";

        var res = new JsonExcept(new JsonTransform(), Arrays.asList(keys)).transform(value);
        when(jt.getExcept(value, keys)).thenReturn(ResponseEntity.ok(res));

        ResponseEntity<String> OKResponse = new ResponseEntity<>("{}", HttpStatus.OK);
        Assert.assertEquals(OKResponse, jt.getExcept(value, keys));
    }
    @Test
    void getExceptSyntaxError() {
        JSONToolsController jt = mock(JSONToolsController.class);
        String[] keys = {"author"};
        String value = "{\"title\":\"Nowy\",\"price\":\"100\",\"author\":\"Malinowski\"";

        when(jt.getExcept(value, keys)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("syntax error"));

        ResponseEntity<String> noOKResponse = new ResponseEntity<>("syntax error", HttpStatus.BAD_REQUEST);
        Assert.assertEquals(noOKResponse, jt.getExcept(value, keys));
    }
}