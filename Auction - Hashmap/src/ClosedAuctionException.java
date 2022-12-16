/**
 *
 * This class creates a custom exception.
 * Thrown if the auction is closed and no more bids can be placed (i.e. timeRemaining == 0).
 *
 * @author Nicholas Stamatakis
 *
 */
import java.io.Serializable;
public class ClosedAuctionException extends Exception implements Serializable {
    ClosedAuctionException(){
        super();
    }
    ClosedAuctionException(String s){
        super(s);
    }
}

