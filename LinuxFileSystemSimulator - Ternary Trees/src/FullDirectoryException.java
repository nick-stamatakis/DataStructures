/**
 * This class creates a custom exception.
 * Thrown if the current node is a file,
 * as files cannot contain DirectoryNode references (i.e. all files are leaves).
 *
 * @author Nicholas Stamatakis
 *
 */
public class FullDirectoryException extends Exception {
    FullDirectoryException(){
        super();
    }
    FullDirectoryException(String s){
        super(s);
    }
}
