package Request;

import org.w3c.dom.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Joey on 5/6/2015.
 */
public class SpotifyRequest extends Request {
    String Artist;




    public SpotifyRequest(Document doc){
        type = Type.SPOTIFY;
        switch (((CharacterData)doc.getElementsByTagName("Command").item(0).getFirstChild()).getData()){
            case "MAKE_PLAYLIST":
                command = Command.MAKE_PLAYLIST;
                Artist = ((CharacterData)doc.getElementsByTagName("Arguments").item(0).getFirstChild()).getData();
                break;
        }


    }

    public String getArtist() {
        return Artist;
    }
}
