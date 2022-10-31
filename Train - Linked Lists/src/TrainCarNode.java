/**
 * This class acts as a node wrapper around a TrainCar Object.
 * It contains a TrainCarNode Objects prev, next, and car.
 *
 * @author Nicholas Stamatakis
 */

public class TrainCarNode {
    /**
     * Previous node in the chain
     */
    private TrainCarNode prev;

    /**
     * Next node in the chain
     */
    private TrainCarNode next;

    /**
     * TrainCar Reference containing the data for the TrainCar
     */
    private TrainCar car;

    /**
     * Getter method for prev
     *
     * @return
     * TrainCarNode Object which represents the previous value in the chain
     */
    public TrainCarNode getPrev() {
        return prev;
    }

    /**
     * Setter method for prev
     *
     * @param prev
     * Changes prev to TrainCarNode Object in the method signature
     */
    public void setPrev(TrainCarNode prev) {
        this.prev = prev;
    }

    /**
     * Getter method for next
     *
     * @return
     * Value of TrainCarNode Object next which represents the next node in the chain
     */
    public TrainCarNode getNext() {return next;}

    /**
     * Setter method for next
     *
     * @param next
     * Changes next Object to the value in the method signature
     */
    public void setNext(TrainCarNode next) {
        this.next = next;
    }

    /**
     * Getter method for car
     *
     * @return
     * Current value of car which is a TrainCar Object
     */
    public TrainCar getCar() {
        return car;
    }

    /**
     * Setter method for car
     *
     * @param car
     * Changes car to TrainCar object given in the method signature
     */
    public void setCar(TrainCar car) {
        this.car = car;
    }

    /**
     * No-arg constructor for TrainCarNode Class
     */
    public TrainCarNode(){
        prev = null;
        next = null;
        car = null;
    }

    /**
     * Arg Constructor for TrainCarNode Class
     */
    public TrainCarNode(TrainCar car1){
        car = car1;
    }

    /**
     * ToString method for TrainCarNode Class
     * @return
     * String to display node
     */
    @Override
    public String toString() {
        return "TrainCarNode{" +
                "prev=" + prev +
                ", next=" + next +
                ", car=" + car +
                '}';
    }
}
