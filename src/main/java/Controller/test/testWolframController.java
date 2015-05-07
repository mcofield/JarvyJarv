package Controller.test;


import Controller.WolframController;
import Request.WolframRequest;
import junit.framework.TestCase;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

/**
 * Created by Joey on 5/7/2015.
 */
public class testWolframController extends TestCase {

        public void testQuery() throws Exception{

            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<Request>\n" +
                    "<Type>WOLFRAM</Type>\n" +
                    "<Command>MAKE_PLAYLIST</Command>\n" +
                    "<Arguments>integrate 1/x</Arguments>\n" +
                    "</Request>";
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));

            WolframController wc = new WolframController();
            WolframRequest wr = new WolframRequest(doc);
            wc.HandleRequest(wr);
        }
}
