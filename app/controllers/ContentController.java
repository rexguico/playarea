package controllers;

import play.mvc.*;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import org.w3c.dom.*;

import play.libs.XPath;

public class ContentController extends Controller {

    public Result index() {
        JsonNode json = request().body().asJson();
        return ok("Got name: " + json.get("name").asText());
    }

    @BodyParser.Of(BodyParser.Text.class)
    public Result explicitContent() {
        // configure play.http.parser.maxMemoryBuffer to adjust buffer limit
        // custom body parsers can be created by implementing BodyParser
        String text = request().body().asText();
        return ok("Got text: " + text);
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result sayHelloJson() {
        JsonNode json = request().body().asJson();
        String name = json.findPath("name").textValue();
        return ok("Hello " + name);
    }

    @BodyParser.Of(BodyParser.Xml.class)
    public Result sayHelloXml() {
        Document dom = request().body().asXml();
        String name = XPath.selectText("//name", dom);

        return ok("<message \"status\"=\"OK\">Hello " + name + "</message>").as("application/xml");
    }

}
