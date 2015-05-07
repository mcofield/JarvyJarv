package Request;

import org.w3c.dom.*;

/**
 * Created by Joey on 5/7/2015.
 */
public class WolframRequest extends Request {

    String Query;

    public WolframRequest(Document doc){
        type = Type.WOLFRAM;
        Query =  (((CharacterData)doc.getElementsByTagName("Arguments").item(0).getFirstChild()).getData());
    }

    public String getQuery() {
        return Query;
    }

}
