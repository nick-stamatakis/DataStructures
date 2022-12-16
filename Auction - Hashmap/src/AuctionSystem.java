/**
 *
 * This class will allow the user to interact with the database by listing open auctions,
 * make bids on open auctions, and create new auctions for different items. In addition,
 * the class should provide the functionality to load a saved (serialized) AuctionTable or
 * create a new one if a saved table does not exist.
 *
 * @author Nicholas Stamatakis
 *
 */
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.text.DecimalFormat;
public class AuctionSystem implements Serializable {

    /**
     * AuctionTable member variable
     */
    private AuctionTable auctionTable;

    /**
     * String member variable
     */
    private String username;

    /**
     * Main method. Will prompt the user for a username.
     * This should be stored in username The rest of the program will
     * be executed on behalf of this user.
     */
    public static void main(String[] args) {
        //Create Auction System object
        AuctionSystem system = new AuctionSystem();
        //Create Scanner object
        Scanner in = new Scanner(System.in);
        System.out.println("Starting...");
        //TODO: Check if there is a previous Auction
        //Serializable.class.isInstance(file) == false
        try{
            FileInputStream file = new FileInputStream("auction.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            if (Files.exists(Paths.get("auction.obj"))){
                System.out.println("Loading previous Auction Table...\n");
                system.auctionTable = (AuctionTable) inStream.readObject();
            }
            else{
                System.out.println("No previous auction table detected.");
                System.out.println("Creating new table...\n");
            }
        }
        catch (Exception ex){
            System.out.println("No previous auction table detected.");
            System.out.println("Creating new table...\n");
        }
        //Prompts user for username
        System.out.print("Please select a username: ");
        System.out.println();
        system.username = in.next();
        String input;
        do{
            //prints out menu
            input = AuctionSystem.printMenu(in);
            if (input.equals("D")){
                system.D(system, in);
            }
            else if (input.equals("A")){
                system.A(system, in);
            }
            else if (input.equals("B")){
                system.B(system, in);
            }
            else if (input.equals("I")){
                system.I(system, in);
            }
            else if (input.equals("P")){
                if (system.auctionTable != null)
                    system.auctionTable.printTable();
            }
            else if (input.equals("R")){
                system.R(system);
            }
            else if (input.equals("T")){
                system.T(system, in);
            }
            else if (input.equals("Q")){
                system.Q(system);
            }
            else{
                System.out.println("Please try again. A valid input from the menu is needed.");
            }
        }
        while (!input.equals("Q"));
    }

    /**
     * Prints out menu
     * @param in
     * Scanner object
     * @return
     * String with corresponding user answer
     */
    public static String printMenu(Scanner in){
        System.out.println("\nMenu:");
        System.out.println("    (D) - Import Data from URL\n" +
                "    (A) - Create a New Auction\n" +
                "    (B) - Bid on an Item\n" +
                "    (I) - Get Info on Auction\n" +
                "    (P) - Print All Auctions\n" +
                "    (R) - Remove Expired Auctions\n" +
                "    (T) - Let Time Pass\n" +
                "    (Q) - Quit");
        System.out.println();
        System.out.print("Please select an option: ");
        String answer = in.next();
        return answer.toUpperCase();
    }

    /**
     * Import Data from URL
     * @param system
     * Auction System object
     * @param in
     * Scanner Object
     */
    public void D(AuctionSystem system, Scanner in){
        //takes in URL
        System.out.print("Please enter a URL: ");
        String URL = in.next();
        //Calls AuctionTable method
        system.auctionTable = AuctionTable.buildFromURL(URL);
        System.out.println();
        System.out.println("Loading...");
        System.out.println("Auction data loaded successfully!");
    }

    /**
     * Create a New Auction
     * @param system
     * AuctionSystem Object
     * @param in
     * Scanner object
     */
    public void A(AuctionSystem system, Scanner in){
        System.out.println("Creating new Auction as " + system.username);
        //Enters auctionID
        System.out.println("Please enter an Auction ID: ");
        String auctionID1 = in.nextLine();
        String auctionID2 = in.nextLine();
        //Enters Auction time
        System.out.println("Please enter an Auction time (hours): ");
        String timeRemaining = in.nextLine();
        //Enters itemInfo
        System.out.println("Please enter some Item Info: ");
        String itemInfo = in.nextLine();
        //Creates New Auction
        Auction newAuction = new Auction(Integer.parseInt(timeRemaining), 0, auctionID2, system.username,
                "", itemInfo);
        //Puts new Auction in Hashtable
        system.auctionTable.putAuction(auctionID2, newAuction);
        System.out.println("\nAuction " + auctionID2 + " inserted into table.");
    }

    /**
     * Bid on an Item
     * @param system
     * AuctionSystem Object
     * @param in
     * Scanner object
     */
    public void B(AuctionSystem system, Scanner in){
        System.out.print("Please enter an Auction ID: ");
        String auctionID = in.next();
        System.out.println();
        try{
            if (system.auctionTable.containsKey(auctionID) &&
                    system.auctionTable.getAuction(auctionID).getTimeRemaining() != 0){
                System.out.println("Auction " + auctionID + " is OPEN");
                if (system.auctionTable.getAuction(auctionID).getCurrentBid() == 0){
                    System.out.printf("    Current Bid: None");
                }
                else{
                    System.out.printf("    Current Bid: $ %-6.2f", system.auctionTable.getAuction(auctionID).getCurrentBid());
                }
                System.out.println("\n");
                System.out.print("What would you like to bid?: ");
                double bid = in.nextDouble();
                system.auctionTable.getAuction(auctionID).newBid(system.username, bid);
                System.out.println("Bid accepted.");
            }
            else{
                system.ClosedAuctionWithCommas(auctionID, system);
            }
        }
        catch (ClosedAuctionException ex){
            system.ClosedAuctionWithCommas(auctionID, system);
        }
    }

    /**
     * Prints Closed Auction with Commas
     * @param auctionID
     * AuctionID String
     * @param system
     * AuctionSystem Object
     */
    public void ClosedAuctionWithCommas(String auctionID, AuctionSystem system){
        System.out.println("Auction " + auctionID + " is CLOSED");
        DecimalFormat df = new DecimalFormat("#,###.00");
        double d = system.auctionTable.getAuction(auctionID).getCurrentBid();
        String formattedNumberWithComma = df.format(d);
        System.out.println("    Current Bid: $ " + formattedNumberWithComma);
        System.out.println();
        System.out.println("You can no longer bid on this item.");
    }

    public void I(AuctionSystem system, Scanner in){
        System.out.println("Please enter an Auction ID:");
        String auctionID = in.nextLine();
        String auctionID1 = in.nextLine();
        if (system.auctionTable.containsKey(auctionID1)){
            System.out.println();
            System.out.println("Auction " + auctionID1);
            System.out.println("    Seller: " +
                    system.auctionTable.getAuction(auctionID1).getSellerName());
            System.out.println("    Buyer: " +
                    system.auctionTable.getAuction(auctionID1).getBuyerName());
            System.out.println("    Time: " +
                    system.auctionTable.getAuction(auctionID1).getTimeRemaining() + " hours");
            System.out.println("    Info: " +
                    system.auctionTable.getAuction(auctionID1).getItemInfo());
        }
        else{
            System.out.println("Please enter a valid AuctionID.");
        }
    }

    /**
     * Remove Expired Auctions
     * @param system
     * Auction System Object
     */
    public void R(AuctionSystem system){
        System.out.println("Removing expired auctions...");
        system.auctionTable.removeExpiredAuctions();
        System.out.println("All expired auctions removed.");
    }

    /**
     * Let Time Pass
     * @param system
     * AuctionSystem Object
     * @param in
     * Scanner Object
     */
    public void T(AuctionSystem system, Scanner in){
        System.out.println("How many hours should pass: ");
        int hours = in.nextInt();
        system.auctionTable.letTimePass(hours);
        System.out.println("Time passing...");
        System.out.println("Auction times updated.\n");
    }

    /**
     * Quit
     */
    public void Q(AuctionSystem system){
        try {
            System.out.println("\nWriting Auction Table to file...");
            //Writes to file
            FileOutputStream file = new FileOutputStream("auction.obj");
            ObjectOutputStream outStream = new ObjectOutputStream(file);
            outStream.writeObject(system.auctionTable);
            System.out.println("Done!\n");
            System.out.println("Goodbye.");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}


