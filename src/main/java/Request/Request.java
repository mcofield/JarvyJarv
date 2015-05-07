package Request;

/**
 * Created by Joey on 5/6/2015.
 */
public abstract class Request {

    Type type;
    Command command;



    public Command getCommand() {
        return command;
    }
    public Type getType() {
        return type;
    }


}
