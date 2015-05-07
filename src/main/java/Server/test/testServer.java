package Server.test;

import junit.framework.TestCase;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by Joey on 5/7/2015.
 */
public class testServer extends TestCase{
    String URL_PATH = "http://localhost:9998/post";

    public void testSpotifyPost() throws Exception{
        URL url = new URL(URL_PATH);
        String xml ="<?xml version=\"1.0\"?> <Request> <Type>SPOTIFY</Type> <Command>MAKE_PLAYLIST</Command> <Arguments>Blink 182</Arguments> </Request>";
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        //con.setRequestProperty("Content-Type", "application/xml");
        con.setRequestProperty("Content-Length",""+ xml.getBytes().length);
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.write(xml.getBytes());
        wr.flush();
        wr.close();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println( con.getResponseCode());
    }
}
