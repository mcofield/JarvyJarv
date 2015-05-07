package Controller.test;

import Controller.SpotifyController;
import Request.SpotifyRequest;
import junit.framework.TestCase;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

/**
 * Created by Joey on 5/6/2015.
 */
public class testSpotifyController extends TestCase {

    public void testMakePlaylist() throws Exception{
        try {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<Request>\n" +
                    "<Type>SPOTIFY</Type>\n" +
                    "<Command>MAKE_PLAYLIST</Command>\n" +
                    "<Arguments>Blink 182</Arguments>\n" +
                    "</Request>";
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
            SpotifyController spotifyController = new SpotifyController();
            spotifyController.HandleRequest(new SpotifyRequest(doc));
        }catch (Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
