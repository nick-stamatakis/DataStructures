/**
 * This class represents the Product Load on my train car.
 * It contains a product name, weight, value, and a boolean
 * value which represents the danger of the product.
 *
 * @author Nicholas Stamatakis
 */

public class ProductLoad {
    /**
     * Data field for name of the product
     */
    private String name;

    /**
     * Data field representing the product's weight in tons
     */
    private double weight;

    /**
     * Data field representing the product's value in dollars
     */
    private double value;

    /**
     * Data field representing whether the product is dangerous
     */
    private boolean isDangerous;

    /**
     * Getter method for Product name
     *
     * @return
     * String referencing the name of the Product
     */
    public String getProductName() {
        return name;
    }

    /**
     * Setter method for Product Name
     *
     * @param productName
     * Changes Product name to string value given in the method signature
     */
    public void setProductName(String productName) {
        this.name = productName;
    }

    /**
     * Getter method for Weight
     *
     * @return
     * Double value that represents the weight of the product  in tons.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Setter method for weight
     *
     * @param weight
     * Changes weight to double value given in the method signature
     */
    public void setWeight(double weight) {
        if (weight < 0){
            throw new IllegalArgumentException("Please enter a weight >= 0.");
        }
        this.weight = weight;
    }

    /**
     * Getter method for value
     *
     * @return
     * Double value representing the product's value in dollars
     */
    public double getValue() {return value;}

    /**
     * Setter method for value
     *
     * @param value
     * Changes value to double given in the method signature
     */
    public void setValue(double value) {
        if (value < 0){
            throw new IllegalArgumentException("Please enter a weight >= 0.");
        }
        this.value = value;
    }

    /**
     * Getter method for danger
     *
     * @return
     * Boolean value for whether the product is dangerous
     */
    public boolean getIsDangerous() {return isDangerous;}

    /**
     * Setter method for danger
     *
     * @param isDangerous
     * Changes danger to given boolean in the method signature
     */
    public void setIsDangerous(boolean isDangerous) {this.isDangerous = isDangerous;}

    /**
     * No-arg constructor for the ProductLoad Class
     * Sets all data fields to common instantiation
     */
    public ProductLoad(){
        name = "";
        weight = 0;
        value = 0;
        isDangerous = false;
    }

    /**
     * Arg constructor for the ProductLoad Class
     * Sets data field to the values given in the method signature
     *
     * @param name1
     * String representing the name of the product
     * @param weight1
     * Double representing the weight in tons of the Product
     * @param value1
     * Double representing the value of the product
     * @param danger1
     * Boolean value that represents whether the product is dangerous
     */
    public ProductLoad(String name1, double weight1, double value1, boolean danger1){
        name = name1;
        weight = weight1;
        value = value1;
        isDangerous = danger1;
    }
}
