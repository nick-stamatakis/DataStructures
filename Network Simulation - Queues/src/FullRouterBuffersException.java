/**
 * This class creates a custom exception for when a Router is full.
 *
 * @author Nicholas Stamatakis
 */
public class FullRouterBuffersException extends Exception{
    FullRouterBuffersException(){
        super();
    }
    FullRouterBuffersException(String s){
        super(s);
    }
}
