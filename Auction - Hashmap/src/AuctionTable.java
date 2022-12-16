/**
 * The database of open auctions will be stored
 * in a hash table to provide constant time insertion and deletion.
 * This is the class for that Database.
 * auctionID will be used as the key for the corresponding Auction object.
 *
 * @author Nicholas Stamatakis
 *
 */
import java.io.Serializable;
import java.util.HashMap;

import big.data.*;
//Hashmap = dictionary = associative array
//Set of key-value pairs

public class AuctionTable extends HashMap<String, Auction> implements Serializable{

    /**
     * Uses the BigData library to construct an AuctionTable from a remote data source.
     * @param URL
     * String representing the URL for the remote data source.
     * @custom.precondition
     * URL represents a data source which can be connected to using the BigData library.
     * The data source has proper syntax.
     * @return
     * The AuctionTable constructed from the remote data source.
     * @throws IllegalArgumentException
     * Thrown if the URL does not represent a valid datasource (can't connect or invalid syntax).
     */
    public static AuctionTable buildFromURL(String URL) throws IllegalArgumentException{
        if (false){//TODO: find out how to check precondition
            throw new IllegalArgumentException();
        }
        //Creates AuctionTable Object
        AuctionTable auctionTable = new AuctionTable();
        try{
            //Tries to Load data from file
            DataSource ds = DataSource.connect(URL).load();
            String[] sellerNames = ds.fetchStringArray("listing/seller_info/seller_name");
            for(int i = 0; i < sellerNames.length; i++){
                sellerNames[i] = sellerNames[i].replaceAll("\\s", "");
            }
            String[] currentBids = ds.fetchStringArray("listing/auction_info/current_bid");
            double[] currentBids2 = new double[currentBids.length];
            for(int i = 0; i < currentBids.length; i++){
                currentBids[i] = currentBids[i].substring(1);
                currentBids[i] = currentBids[i].replaceAll(",", "");
                currentBids2[i] = Double.parseDouble(currentBids[i]);
            }
            String[] timeLeftBad = ds.fetchStringArray("listing/auction_info/time_left");
            int[] timeLeftGood = new int[timeLeftBad.length];
            //Goes through for loop to change days to hours
            for(int i = 0; i < timeLeftBad.length; i++){
                String[] temp = timeLeftBad[i].split(" ");
                int totalHours = 0;
                if (temp.length <= 2){
                    totalHours = Integer.parseInt(temp[0]) * 24;
                }
                else if (temp.length > 2){
                    totalHours = Integer.parseInt(temp[0]) * 24 + Integer.parseInt(temp[2]);
                }
                timeLeftGood[i] = totalHours;
            }

            String[] idNums = ds.fetchStringArray("listing/auction_info/id_num");
            String[] bidderNames = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");

            String[] memory = ds.fetchStringArray("listing/item_info/memory");
            String[] hardDrive = ds.fetchStringArray("listing/item_info/hard_drive");
            String[] cpu = ds.fetchStringArray("listing/item_info/cpu");

            //Creates combined string array
            String[] combinedStringArray = new String[memory.length];
            for (int i = 0; i < memory.length; i++){
                if (cpu[i].equals(""))
                    cpu[i] = "N/A";
                if (memory[i].equals(""))
                    memory[i] = "N/A";
                if (hardDrive[i].equals(""))
                    hardDrive[i] = "N/A";
                combinedStringArray[i] = cpu[i] + " - " + memory[i] + " - " + hardDrive[i];
            }
            //Builds hashmap
            for(int i = 0; i < combinedStringArray.length; i++) {
                if (idNums[i].equals(""))
                    idNums[i] = "N/A";
                if (sellerNames[i].equals(""))
                    sellerNames[i] = "N/A";
                if (bidderNames[i].equals(""))
                    bidderNames[i] = "N/A";
                Auction auction = new Auction(timeLeftGood[i], currentBids2[i], idNums[i],
                        sellerNames[i], bidderNames[i], combinedStringArray[i]);
                auctionTable.put(auction.getAuctionID(), auction);
            }
        }
        catch (Exception ex){
            System.out.println("Please enter a valid URL.");
        }
        return auctionTable;
    }

    /**
     * Manually posts an auction, and add it into the table.
     * @param auctionID
     * the unique key for this object
     * @param auction
     * The auction to insert into the table with the corresponding auctionID
     * @custom.postcondition
     * The item will be added to the table if all given parameters are correct.
     * @throws IllegalArgumentException
     * If the given auctionID is already stored in the table.
     */
    public void putAuction(String auctionID, Auction auction) throws IllegalArgumentException{
        //Throws new Illegal Argument Exception if key already in table
        if (this.containsKey(auctionID)){
            throw new IllegalArgumentException();
        }
        //Puts Auction Object in table if key is not already in the table
        this.put(auctionID, auction);
    }

    /**
     * Get the information of an Auction that contains the given ID as key
     * @param auctionID
     * the unique key for this object
     * @return
     * An Auction object with the given key, null otherwise.
     */
    public Auction getAuction(String auctionID){
        if (this.containsKey(auctionID)){
            return this.get(auctionID);
        }
        return null;
    }

    /**
     * Simulates the passing of time. Decrease the timeRemaining of all Auction objects
     * by the amount specified.
     * The value cannot go below 0.
     * @param numHours
     * the number of hours to decrease the timeRemaining value by.
     * @custom.postcondition
     * All Auctions in the table have their timeRemaining timer decreased.
     * If the original value is less than the decreased value, set the value to 0.
     * @throws IllegalArgumentException
     * If the given numHours is non-positive
     */
    public void letTimePass(int numHours) throws IllegalArgumentException{
        //Let's time pass by specified amount
        String[] key = new String[this.values().size()];
        int i = 0;
        for (Auction value : this.values()){
            if (numHours > value.getTimeRemaining()) {
                key[i] = value.getAuctionID();
                i++;
            }
        }
        for (int j = 0; j <= i; j++){
            if (this.getAuction(key[j]) != null)
                this.getAuction(key[j]).decrementTimeRemaining(this.getAuction(key[j]).getTimeRemaining());
        }
        for (Auction value : this.values()){
            if (value.getTimeRemaining() - numHours >= 0) {
                value.decrementTimeRemaining(numHours);
            }
        }
    }

    /**
     * Iterates over all Auction objects in the table and
     * removes them if they are expired (timeRemaining == 0).
     */
    public void removeExpiredAuctions(){
        String[] key = new String[this.values().size()];
        int i = 0;
        for (Auction value : this.values()){
            if (value.getTimeRemaining() == 0) {
                key[i] = value.getAuctionID();
                i++;
            }
        }
//        for (int k = 0; k <= i; k++){
//            System.out.println(key[k]);
//        }
        for (int j = 0; j <= i; j++){
            this.remove(key[j]);
        }
    }

    /**
     * Prints the AuctionTable in tabular form.
     */
    public void printTable(){
        System.out.println(" Auction ID |      Bid   |        Seller         |          Buyer          |    Time   |  Item Info");
        System.out.println("===================================================================================================================================");
        for (Auction value : this.values()){
            System.out.println(value.toString());
        }
    }


}
