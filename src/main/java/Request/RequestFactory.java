package Request;

import org.w3c.dom.*;

/**
 * Created by Joey on 5/7/2015.
 */
public class RequestFactory {

    public static Request createRequest(Document doc){

        switch(((CharacterData)doc.getElementsByTagName("Type").item(0).getFirstChild()).getData()){
            case("SPOTIFY"):
                return new SpotifyRequest(doc);
            case("WOLFRAM"):
                return new WolframRequest(doc);
        }

        return null;
    }
}
