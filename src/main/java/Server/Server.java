package Server;


import Controller.SpotifyController;
import Controller.WolframController;
import Request.RequestFactory;
import Request.Request;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Joey on 5/7/2015.
 */

@Path("/")
public class Server {

    WolframController wolframController = new WolframController();
    SpotifyController spotifyController = new SpotifyController();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

    @POST
    @Path("/post")
    //@Consumes(MediaType.TEXT_XML)
    public Response postStrMsg( String msg){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(msg)));
            Request request = RequestFactory.createRequest(document);
            executeRequest(request);
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.status(200).entity("hi").build();
    }

    private void executeRequest(Request request) throws Exception{
        switch (request.getType()){
            case SPOTIFY:
                spotifyController.HandleRequest(request);
                break;
        }
    }
}
