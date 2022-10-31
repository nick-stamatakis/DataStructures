/**
 * This class represents a course which extends the IllegalArgumentException class
 * @author Nicholas Stamatakis
 */

public class FullPlannerException extends IllegalArgumentException{
    FullPlannerException(){
        super();
    }
    FullPlannerException(String s){
        super(s);
    }

}
