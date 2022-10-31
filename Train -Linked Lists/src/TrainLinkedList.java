
/**
 * This class implements Double-Linked List ADT.
 *
 * @author Nicholas Stamatakis
 */

public class TrainLinkedList {

    /**
     * TrainCarNode Object for the head
     */
    private TrainCarNode head;

    /**
     * TrainCarNode Object for the tail
     */
    private TrainCarNode tail;

    /**
     * TrainCarNode Object for the cursor
     */
    private TrainCarNode cursor;

    /**
     * Counts the number of nodes added
     */
    private int nodeCount;

    /**
     * Keeps a global count of the length
     */
    private double totalLength;

    /**
     * Keeps a global count of the weight
     */
    private double totalWeight;

    /**
     * Keeps a global count of the value
     */
    private double totalValue;

    /**
     * Counts the number of Dangerous cars
     */
    private int numOfDangerousCars;
    /**
     * Checks if one of cars in the linked list is dangerous or not
     */
    private boolean ifListDangerous = false;

    /**
     * Getter for cursor
     *
     * @custom.precondition
     * The list is not empty (cursor is not null).
     *
     * @return
     * Current reference to TrainCarNode cursor Object
     */
    public TrainCar getCursorData() {
        if (cursor == null){
            return null;
        }
        return this.cursor.getCar();
    }

    /**
     * Setter for cursor
     *
     * @param car
     * Changes TrainCarNode cursor Object to value in the method signature
     *
     * @custom.precondition
     * The list is not empty (cursor is not null).
     *
     * @custom.postcondition
     * The cursor node now contains a reference to car as its data.
     * //TODO: Implement this
     */
    public void setCursorData(TrainCar car) {
        if (cursor != null) {
            cursor.setCar(car);
        }
    }

    /**
     *Constructs an instance of the TrainLinkedList with
     * no TrainCar objects in it.
     *
     * @custom.postcondition
     * This TrainLinkedList has been initialized to an empty linked list.
     * head, tail, and cursor are all set to null.
     */
    public TrainLinkedList(){
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     *Moves the cursor to point at the next TrainCarNode.
     *
     * @custom.precondition
     * The list is not empty (cursor is not null)
     * and cursor does not currently reference the tail of the list.
     *
     * @custom.postcondition
     * The cursor has been advanced to the next TrainCarNode,
     * or has remained at the tail of the list.
     */
    public void cursorForward(){
        if (cursor != null && cursor != tail){
            cursor = cursor.getNext();
            System.out.println("cursor moved forward.");
        }
        else{
            System.out.println("No next car, cannot move cursor forward.");
        }
    }

    /**
     * Moves the cursor to point at the previous TrainCarNode.
     *
     * @custom.precondition
     * The list is not empty (cursor is not null)
     * and the cursor does not currently reference the head of the list.
     *
     * @custom.postcondition
     * The cursor has been moved back to the previous TrainCarNode,
     * or has remained at the head of the list.
     */
    public void cursorBackward(){
        if (cursor != null && cursor != head){
            cursor = cursor.getPrev();
            System.out.println("Cursor moved backward");
        }
        else{
            System.out.println("No previous car, cannot move cursor backward.");
        }
    }

    /**
     *Inserts a car into the train after the cursor position.
     *
     * @param newCar
     * the new TrainCar to be inserted into the train.
     *
     * @custom.precondition
     * This TrainCar object has been instantiated
     *
     * @custom.postcondition
     * The new TrainCar has been inserted into the train after
     * the position of the cursor.
     *
     * All TrainCar objects previously on the train are still on the train,
     * and their order has been preserved.
     *
     * The cursor now points to the inserted car.
     *
     * @throws IllegalArgumentException
     * Indicates that newCar is null.
     */
    public void insertAfterCursor(TrainCar newCar){
        TrainCarNode node = new TrainCarNode(newCar);
        if (newCar == null){
            throw new IllegalArgumentException("newCar is null. It must be instantiated.");
        }
        if (cursor == null){
            head = node;
            tail = node;
            cursor = node;
        }
        //TODO: how to make cursor not null
        else if (cursor.getNext() == null){
            node.setPrev(cursor);
            cursor.setNext(node);
            tail = node;
            cursorForward();
        }
        else if (cursor != null && cursor.getNext() != null) {
            node.setNext(cursor.getNext());
            cursor.getNext().setPrev(node);
            cursor.setNext(node);
            node.setPrev(node);
            cursor = node;
        }
        totalLength += node.getCar().getCarLength();//increments length of the car
        totalWeight += node.getCar().getCarWeight();
        nodeCount++;
    }

    /**
     * helps increment global variables each time the ProductLoad is instantiated
     * @param load1
     * represents the ProductLoad inputted by the user
     */
    public void incrementGlobalVariables(ProductLoad load1){
        totalWeight += load1.getWeight();//increments weight of car
        totalValue += load1.getValue();
        ifListDangerous = ifListDangerous | load1.getIsDangerous();
        if(load1.getIsDangerous()){
            numOfDangerousCars++;
        }
    }

    /**
     * Removes the TrainCarNode referenced by the cursor and returns
     * the TrainCar contained within the node.
     *
     * @custom.precondition
     * The cursor is not null.
     *
     * @return
     * TrainCar Object
     *
     * @custom.postcondition
     * The TrainCarNode referenced by the cursor has been removed from the train.
     * The cursor now references the next node,
     * or the previous node if no next node exists.
     */
    public TrainCar removeCursor(){
        if (cursor != null){
            printCurrentCursor();
            decrementGlobalVariables();

            TrainCarNode temp = cursor;
            if (cursor.getPrev() == null){//checks if it's the head
                System.out.println("1");
                head = head.getNext();
                cursor = head;
                nodeCount--;
                return temp.getCar();
            }
            else if (cursor.getNext() == null){//checks to see if at tail
                System.out.println("2");
                tail = tail.getPrev();
                cursor = tail;
                cursor.setNext(null);
                nodeCount--;
                return temp.getCar();
            }
            else{//not head or tail
                System.out.println("3");
                cursor.getPrev().setNext(cursor.getNext());
                cursor.getNext().setPrev(cursor.getPrev());
                cursor = cursor.getNext();
                nodeCount--;
                return temp.getCar();
            }
        }
        return null;
    }

    /**
     * Decrements length, weight, and value.
     * Changes ifListDangerous depending on how many dangerous cars how been found.
     */
    public void decrementGlobalVariables(){
        totalLength -= cursor.getCar().getCarLength();//increments length of the car
        totalWeight -= cursor.getCar().getCarWeight();
        if (cursor.getCar().getProductLoad() != null){
            totalWeight -= cursor.getCar().getProductLoad().getWeight();
        }
        if (cursor.getCar().getProductLoad() != null){
            totalValue -= cursor.getCar().getProductLoad().getValue();//increments value of the car
        }
        if (numOfDangerousCars == 1 && cursor.getCar().getProductLoad().getIsDangerous()){
            numOfDangerousCars--;
            ifListDangerous = false;
        }
        else if (numOfDangerousCars > 1 && cursor.getCar().getProductLoad().getIsDangerous()){
            numOfDangerousCars--;
            ifListDangerous = true;
        }
        else{
            ifListDangerous = false;
        }
    }

    public void printCurrentCursor(){
        if (cursor.getCar().getProductLoad() != null){
            System.out.println("        Name      Weight (t)     Value ($)   Dangerous");
            System.out.println("    ===================================================");
            System.out.printf("%14s%14.1f%14.2f%12s", cursor.getCar().getProductLoad().getProductName(),
                    cursor.getCar().getProductLoad().getWeight(), cursor.getCar().getProductLoad().getValue(),
                    (cursor.getCar().getProductLoad().getIsDangerous()) ? "YES" : "NO" );
        }
        System.out.println();
    }

    /**
     * Determines the number of TrainCar objects currently on the train.
     *
     *The number of TrainCar objects on this train.
     * @return
     *
     * Time Complexity: This function should complete in O(1) time.
     */
    public int size(){
        return nodeCount;
    }

    /**
     *Returns the total length of the train in meters.
     *
     * @return
     * The sum of the lengths of each TrainCar in the train.
     *
     * Time Complexity: This function should complete in O(1) time.
     */
    public double getLength(){
        return totalLength;
    }

    /**
     * Returns the total weight in tons of the train. Note that the
     * weight of the train is the sum of the weights of each empty TrainCar,
     * plus the weight of the ProductLoad carried by that car.
     *
     * @return
     * The sum of the weight of each TrainCar plus the sum of the ProductLoad
     * carried by that car.
     *
     * Time Complexity: This function should complete in O(1) time.
     */
    public double getWeight(){
        return totalWeight;
    }

    /**
     *Returns the total value of product carried by the train.
     *
     * @return
     * The sum of the values of each TrainCar in the train.
     *
     * Time Complexity: This function should complete in O(1) time.
     */
    public double getValue(){
        return totalValue;
    }

    /**
     * Whether there is a dangerous product on one of the TrainCar
     * objects on the train.
     *
     * @return
     * Returns true if the train contains at least one TrainCar carrying a
     * dangerous ProductLoad, false otherwise.
     *
     * Time Complexity: This function should complete in O(1) time.
     */
    public boolean isDangerous(){
        return ifListDangerous;
    }

    /**
     * Searches the list for all ProductLoad objects with the indicated name
     * \and sums together their weight and value (Also keeps track of whether
     * the product is dangerous or not), then prints a single ProductLoad record
     * to the console.
     *
     * @param name
     * the name of the ProductLoad to find on the train.
     *
     */
    public void findProduct(String name){
        ProductLoad searchLoad = new ProductLoad();
        TrainCarNode cursor3 = head;
        int numCars = 0;
        while(cursor3 != null && cursor3.getCar().getProductLoad() != null){
            if (cursor3.getCar().getProductLoad().getProductName().equals(name)){
                searchLoad.setProductName(cursor3.getCar().getProductLoad().getProductName());
                searchLoad.setWeight(searchLoad.getWeight() + cursor3.getCar().getProductLoad().getWeight());
                searchLoad.setValue(searchLoad.getValue() + cursor3.getCar().getProductLoad().getValue());
                searchLoad.setIsDangerous(cursor3.getCar().getProductLoad().getIsDangerous());
                numCars++;
            }
            cursor3 = cursor3.getNext();
        }
        if (searchLoad != null && numCars > 0){
            System.out.println("The following products were found on " +
                    ((numCars == 1) ? "1 car: ": numCars + " cars:"));
            System.out.println("        Name      Weight (t)     Value ($)   Dangerous");
            System.out.println("    ===================================================");
            System.out.printf("%14s%14.1f%14.2f%12s", searchLoad.getProductName(),
                    searchLoad.getWeight(), searchLoad.getValue(),(searchLoad.getIsDangerous()) ? "YES" : "NO" );
        }
        else{
            System.out.println("No record of " + name + " on board train.");
        }

    }

    /**
     * Prints a neatly formatted table of the car number, car length, car weight,
     * load name, load weight, load value,and load dangerousness for all the
     * car on the train.
     */
    public void printManifest() {
        System.out.println("    CAR:                               LOAD:");
        System.out.println("      Num   Length (m)    Weight (t)  |    Name      Weight (t)     Value ($)   Dangerous");
        System.out.println("    ==================================+===================================================");

        TrainCarNode cursor2 = head;
        int i = 1;

        while (cursor2 != null){
            if (cursor2 != cursor){
                System.out.print("       ");
                System.out.printf("%-11d%-14.1f%-6.1f", i, cursor2.getCar().getCarLength(), cursor2.getCar().getCarWeight());
                System.out.print("|");
                if (cursor2.getCar().getProductLoad() != null) {
                    System.out.printf("%10s%14.1f%14.2f%12s", cursor2.getCar().getProductLoad().getProductName(),
                            cursor2.getCar().getProductLoad().getWeight(), cursor2.getCar().getProductLoad().getValue(),
                            (cursor2.getCar().getProductLoad().getIsDangerous()) ? "YES" : "NO");
                } else {
                    System.out.printf("%10s%14.1f%14.2f%12s", "Empty", 0.0, 0.00, "NO");
                }
            }
            else{
                System.out.print(" ->    ");
                System.out.printf("%-11d%-14.1f%-6.1f", i, cursor.getCar().getCarLength(), cursor.getCar().getCarWeight());
                System.out.print("|");
                if (cursor.getCar().getProductLoad() != null) {
                    System.out.printf("%10s%14.1f%14.2f%12s", cursor.getCar().getProductLoad().getProductName(),
                            cursor.getCar().getProductLoad().getWeight(), cursor.getCar().getProductLoad().getValue(),
                            (cursor.getCar().getProductLoad().getIsDangerous()) ? "YES" : "NO");
                } else {
                    System.out.printf("%10s%14.1f%14.2f%12s", "Empty", 0.0, 0.00, "NO");
                }
            }
            System.out.println();
            cursor2 = cursor2.getNext();
            i++;
        }
    }


    /**
     * Removes all dangerous cars from the train, maintaining the order of the
     * cars in the train.
     *
     * @custom.postcondition
     * All dangerous cars have been removed from this train.
     * The order of all non-dangerous cars must be maintained upon the completion
     * of this method.
     *
     */
    public void removeDangerousCars(){
        TrainCarNode cursor2 = head;
//        while(cursor2 != null){
//            if (cursor2.getCar().getProductLoad().getIsDangerous()){
//                this.removeCursor();//TODO: check if this works
//            }
//            cursor = cursor2;
//            if (cursor2 != null){
//                cursor2 = cursor2.getNext();
//            }
//        }
        while (cursor != null && cursor.getCar().getProductLoad().getIsDangerous()){
            cursor2 = cursor;
            this.removeCursor();
        }
        cursor = cursor2;
    }

    /**
     *Returns a neatly formatted String representation of the train.
     *
     * @return
     * A neatly formatted string containing information about the train,
     * including its size (number of cars), length in meters, weight in tons,
     * value in dollars, and whether it is dangerous or not.
     */
    public String toString(){
        return "Train: " + nodeCount +" cars, " + totalLength + " meters, " + totalWeight +
                " tons, " + totalValue + " value, " + (ifListDangerous ? "DANGEROUS" : "not dangerous.");
    }
}
