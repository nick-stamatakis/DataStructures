/**
 * This class represents my train car.
 * It contains a length, weight, value, and load object
 * from the ProductLoad Class.
 *
 * @author Nicholas Stamatakis
 */

public class TrainCar {
    /**
     * Represents the length of the TrainCar in meters
     */
    private double carLength;

    /**
     * Represents the weight of the TrainCar in tons
     */
    //TODO: should i add product load weight to TrainCar.weight??
    private double carWeight;

    /**
     * A load reference - an instance of the ProductLoad Class
     */
    private ProductLoad load;

    /**
     * Getter method for length
     *
     * @return
     * Length of TrainCar in meters
     */
    public double getCarLength() {
        return carLength;
    }
    /**
     * Getter method for weight
     *
     * @return
     * Weight of TrainCar in tons
     */
    public double getCarWeight() {
        return carWeight;
    }

    /**
     * Getter method for load1 - instance of ProductLoad Class
     *
     * @return
     * ProductLoad Object Reference
     */
    public ProductLoad getProductLoad() {
        return load;
    }

    /**
     * Setter method for load1 - instance of ProductLoad Class
     *
     * @param load
     * Changes instance of ProductLoad Class to the method signature instance
     */
    public void setProductLoad(ProductLoad load) {
        this.load = load;
    }

    /**
     * No-arg constructor for TrainCar Class
     */
    public TrainCar(){
        carLength = 0;
        carWeight = 0;
        load = null;
        //TODO: Should the ProductLoad be added to the TrainCar weight??
    }

    /**
     * Arg constructor for TrainCar Class
     *
     * @param carLength1
     * Represents length of TrainCar Object in meters
     * @param carWeight1
     * Represents weight of TrainCar Object in tons
     */
    public TrainCar(double carLength1, double carWeight1){
        carLength = carLength1;
        carWeight = carWeight1;
    }

    /**
     * Determines whether the TrainCar is empty or not
     *
     * @return
     * Boolean value. True for if the TrainCar is empty.
     * False for if the TrainCar is not empty.
     */
    public boolean isEmpty(){
        return load == null;
    }

    public String toString(TrainCar car1) {
        return "        Name      Weight (t)     Value ($)   Dangerous\n ===================================================";
    }
}
