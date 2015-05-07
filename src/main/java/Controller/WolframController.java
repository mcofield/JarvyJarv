package Controller;

import Request.WolframRequest;
import Request.Request;

import com.wolfram.alpha.*;

/**
 * Created by Joey on 5/7/2015.
 */
public class WolframController extends Controller{
    private static String appid = "GG5VHU-YWU484KVEY";
    private WAEngine engine;
    private WAQuery query;
    public WolframController() {
        engine = new WAEngine();
        engine.setAppID("GG5VHU-YWU484KVEY");
        engine.addFormat("plaintext");
        query = engine.createQuery();
    }

    @Override
    public void HandleRequest(Request request) throws Exception {
           getApproximateResult(executeQuery(((WolframRequest)request).getQuery()));
    }

    private WAPod[] executeQuery(String input) {
        try {
            query.setInput(input);
            WAQueryResult queryResult = engine.performQuery(query);

            if (queryResult.isError()) {
                System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());
                if(queryResult.getErrorMessage().equals("Invalid appid"))
                    return executeQuery(input);
            } else if (!queryResult.isSuccess()) {
                System.out.println("Query was not understood; no results available.");
            } else {

                return queryResult.getPods();


            }
        } catch (WAException e) {
            e.printStackTrace();
        }
    return new WAPod[0];
    }

    private String getApproximateResult(WAPod[] pods){

        return "";
    }
}
