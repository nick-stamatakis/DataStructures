import java.util.Scanner;

/**
 * The main method runs a menu-driven application which first creates an
 * empty TrainLinkedList object. The program prompts the user for a command to
 * execute an operation. Once a command has been chosen, the program may ask the
 * user for additional information if necessary, and performs the operation.
 *
 * @author Nicholas Stamatakis
 */

public class TrainManager {
    //Declare Linked
    TrainLinkedList list1 = new TrainLinkedList();
    TrainCarNode node1;
    TrainCar car1;
    ProductLoad load1;


    /**
     * Runs the menu setting which helps user activate methods for the Doubly Linked List
     * @param args
     */
    public static void main(String[] args) {
        TrainManager trainManager1 = new TrainManager();

        Scanner in = new Scanner(System.in);

        System.out.println("Here is your TrainManager for Your TrainLinkedList:");
        String userInput = "";
        trainManager1.printMenu();

        while (!userInput.equals("Q") && in.hasNext()) {

            userInput = in.nextLine();
            System.out.println();
            userInput = userInput.toUpperCase();

            //try catch block to catch IllegalArgumentException
            try {
                //Switch statement to determine which method is called
                switch (userInput) {
                    case "F" -> {
                        trainManager1.F();
                        trainManager1.printMenu();
                    }
                    case "B" -> {
                        trainManager1.B();
                        trainManager1.printMenu();
                    }
                    case "I" -> {
                        trainManager1.I();
                        trainManager1.printMenu();
                    }
                    case "R" -> {
                        trainManager1.R();
                        trainManager1.printMenu();
                    }
                    case "L" -> {
                        trainManager1.L();
                        trainManager1.printMenu();
                    }
                    case "S" -> {
                        trainManager1.S();
                        trainManager1.printMenu();
                    }
                    case "T" -> {
                        trainManager1.T();
                        trainManager1.printMenu();
                    }
                    case "M" -> {
                        trainManager1.M();
                        trainManager1.printMenu();
                    }
                    case "D" -> {
                        trainManager1.D();
                        trainManager1.printMenu();
                    }
                    case "Q" -> {
                        trainManager1.Q();
                        in.close();
                    }
                    default -> {
                        throw new IllegalArgumentException("Please enter a valid input.");
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Please enter a valid input from the menu.");
            }
            System.out.println();
        }
        in.close();
    }

    /**
     *Moves the cursor forward one car (if a next car exists).
     */
    public void F(){
        list1.cursorForward();
    }

    /**
     * Moves the cursor backward one car (if a previous car exists).
     */
    public void B(){
        list1.cursorBackward();
    }

    /**
     * Inserts a new empty car after the cursor. If the cursor is null
     * (i.e. the train is empty), the car is set as the head of the train.
     * After insertion, the cursor is set to the newly inserted car.
     */
    public void I(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter car length in meters: ");
        double length1 = in.nextDouble();
        System.out.println("Enter car weight in tons: ");
        double weight1 = in.nextDouble();
        car1 = new TrainCar(length1, weight1);
        list1.insertAfterCursor(car1);

        System.out.println("New train car " + length1 + " meters " + weight1 + " tons inserted into train.");
    }

    /**
     * Removes the car at current position of the cursor. After deletion,
     * the cursor is set to the next car in the list if one exists,
     * otherwise the previous car. If there is no previous car,
     * the list is empty and the cursor is set to null.
     */
    public void R(){
        list1.removeCursor();
        System.out.println("Car successully unlinked. The following load has been removed from the train:");
    }

    /**
     * Sets the product load at the current position in the list.
     */
    public void L(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter produce name: ");
        String name1 = in.nextLine();

        System.out.println("Enter product weight in tons: ");
        double weight1 = in.nextDouble();

        System.out.println("Enter product value in dollars: ");
        double value1 = in.nextDouble();

        System.out.println("Enter is product dangerous? (y/n): ");
        boolean isDangerous1 = (in.next().equals("y"));

        load1 = new ProductLoad(name1, weight1, value1, isDangerous1);
        car1.setProductLoad(load1);

        list1.incrementGlobalVariables(load1);

        System.out.println(weight1 + " tons of " + load1.getProductName() + " added to the current car.");
    }

    /**
     *Searches the train for all the loads with the indicated name and prints out
     * the total weight and value, and whether the load is dangerous or not.
     * If the product could not be found, indicate to the user that the train does not
     * contain the indicated product.
     */
    public void S(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter produce name: ");
        String name1 = in.nextLine();

        list1.findProduct(name1);
    }

    /**
     * Prints the String value of the train to the console.
     */
    public void T(){
        System.out.println(list1.toString());
    }

    /**
     *Prints the train manifest - the loads carried by each car.
     */
    public void M(){
        list1.printManifest();
    }

    /**
     * Removes all the dangerous cars from the train.
     */
    public void D(){
        list1.removeDangerousCars();
        System.out.println("Dangerous cars successfully removed from the train.");
    }

    /**
     * Terminates the program.
     */
    public void Q(){
        System.out.println("Program terminating successfully...");
    }

    /**
     * Creates menu each time it is needed in the switch case above
     */
    public void printMenu(){
        System.out.println();
        System.out.println("""
                    (F) Cursor Forward
                    (B) Cursor Backward
                    (I) Insert Car After Cursor
                    (R) Remove Car At Cursor
                    (L) Set Product Load
                    (S) Search For Product
                    (T) Display Train
                    (M) Display Manifest
                    (D) Remove Dangerous Cars
                    (Q) Quit
                    """);
        System.out.println("Enter a selection:");
    }
}


