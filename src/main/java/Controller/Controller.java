package Controller;

import Request.Request;

/**
 * Created by Joey on 5/6/2015.
 */
    public abstract class Controller {

    public abstract void HandleRequest(Request request) throws Exception;

}
