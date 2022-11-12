/**
 *
 * This class creates a custom exception.
 * Thrown if all child references of this directory are occupied.
 *
 * @author Nicholas Stamatakis
 *
 */
public class NotADirectoryException extends Exception {
    NotADirectoryException(){
        super();
    }
    NotADirectoryException(String s){
        super(s);
    }
}
