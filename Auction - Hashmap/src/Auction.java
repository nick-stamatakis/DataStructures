/**
 * Represents an active auction currently in the database.
 *
 * @author Nicholas Stamatakis
 *
 */
import java.io.Serializable;
public class Auction implements Serializable{
    //Member Variables
    /**
     * Time left in the auction.
     */
    private int timeRemaining;

    /**
     * Current bid
     */
    private double currentBid;

    /**
     * Auction ID
     */
    private String auctionID;

    /**
     * Name of the Seller
     */
    private String sellerName;

    /**
     * Name of the buyer
     */
    private String buyerName;

    /**
     * Information about the Item
     */
    private String itemInfo;

    //Constructors
    /**
     * No-arg constructor
     */
    public Auction(){
        timeRemaining = 0;
        currentBid = 0;
        auctionID = "";
        sellerName = "";
        buyerName = "";
        itemInfo = "";
    }

    /**
     * Arg constructor
     */
    public Auction(int timeRemaining1, double currentBid1, String auctionID1,
                   String sellerName1, String buyerName1, String itemInfo1){
        timeRemaining = timeRemaining1;
        currentBid = currentBid1;
        auctionID = auctionID1;
        sellerName = sellerName1;
        buyerName = buyerName1;
        itemInfo = itemInfo1;
    }


    /**
     * Getter for time remaining
     * @return
     * Current value of timeRemaining
     */
    public int getTimeRemaining(){
        return timeRemaining;
    }

    /**
     * Getter for timeRemaining
     * @return
     * Current value of timeRemaining
     */
    public double getCurrentBid() {
        return currentBid;
    }

    /**
     * Getter for auctionID
     * @return
     * Current value of auctionID
     */
    public String getAuctionID() {
        return auctionID;
    }

    /**
     * Getter for sellerName
     * @return
     * Current value of sellerName
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * Getter for buyerName
     * @return
     * Current value of buyerName
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * Getter for itemInfo
     * @return
     * Current value of itemInfo
     */
    public String getItemInfo() {
        return itemInfo;
    }

    /**
     * Decreases the time remaining for this auction by the specified amount.
     * If time is greater than the current remaining time for the auction,
     * then the time remaining is set to 0 (i.e. no negative times).
     * @param time
     * Specified amount to decrement the time by
     */
    public void decrementTimeRemaining(int time){
        if (time <= timeRemaining)
            timeRemaining -= time;
        else
            timeRemaining = 0;
    }

    /**
     * Makes a new bid on this auction.
     * If bidAmt is larger than currentBid,
     * then the value of currentBid is replaced by bidAmt and
     * buyerName is replaced by bidderName.
     * @param bidderName
     * Name of the person bidding
     * @custom.precondition
     * The auction is not closed (i.e. timeRemaining > 0).
     * @param bidAmt
     * Amount that the person is bidding
     * @throws ClosedAuctionException
     * Thrown if the auction is closed and no more bids can be placed (i.e. timeRemaining == 0).
     * @custom.postcondition
     * currentBid Reflects the largest bid placed on this object.
     * If the auction is closed, throw a ClosedAuctionException.
     */
    public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException{
        if (timeRemaining == 0){
            throw new ClosedAuctionException();
        }
        else if (bidAmt > currentBid){
            currentBid = bidAmt;
            buyerName = bidderName;
        }
        else{
            System.out.println("Unacceptable bid. Must be higher than current bid");
        }
    }

    /**
     * ToString method
     * @return
     * String of data members in tabular form
     */
    @Override
    public String toString(){
        //TODO: Work on condition when bid and buyer are null
        if (currentBid == 0 && buyerName.equals(""))
            return String.format("%11s |            | %-22s|                         |%4s hours | %-42s",
                auctionID, sellerName, timeRemaining, itemInfo);
        else
            return String.format("%11s | $%9.2f | %-22s|  %-23s|%4s hours | %-42s", auctionID, currentBid, sellerName,
                buyerName, timeRemaining, (itemInfo.length() > 42) ? itemInfo.substring(0,42): itemInfo);
    }
}
