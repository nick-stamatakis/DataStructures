/**
 * Represents the Big-Oh complexity of some block of code.
 *
 * @author Nicholas Stamatakis
 */

public class Complexity {
    /**
     * Represents the exponent that the base n term should be raised to
     */
    private int n_power;
    /**
     * Represents the exponent that the base log(n) term should be raised to
     */
    private int log_power;

    /**
     * No arg constructor for Complexity Class
     */
    public Complexity(){
        n_power = 0;
        log_power = 0;
    }

    /**
     * Arg constructor for Complexity Class
     * @param n_power1
     * Represents given power to set the exponent of the base n term
     * @param log_power1
     * Represents given power to set the exponent of the base log(n) term
     */
    public Complexity(int n_power1, int log_power1){
        n_power = n_power1;
        log_power = log_power1;
    }

    /**
     * Getter for n_power
     * @return
     * Value of n_power at the given time
     */
    public int getN_power() {
        return n_power;
    }

    /**
     * Setter for n_power
     * @param n_power
     * Method signature is a given input to the function which will change
     * the value of n_power
     */
    public void setN_power(int n_power) {
        this.n_power = n_power;
    }

    /**
     * Getter for log_power
     * @return
     * Value of log_power at the given time
     */
    public int getLog_power() {
        return log_power;
    }

    /**
     * Setter for log_power
     * @param log_power
     * Method signature is a given input to the function which will change
     * the value of log_power
     */
    public void setLog_power(int log_power) {
        this.log_power = log_power;
    }

    /**
     * Returns formatted String for Complexity Class
     * Changes format to account for edge cases like the exponent being zero or one.
     * Also prompts reader to reassign values to his variable
     * @return
     * A formatted string representing the Complexity Class
     */
    @Override
    public String toString() {
        if (n_power == 0 && log_power == 0) {
            return "O(1)";
        } else if (n_power == 1 && log_power == 0) {
            return "O(n)";
        } else if (n_power == 0 && log_power == 1) {
            return "O(log(n))";
        } else if (n_power == 1 && log_power == 1) {
            return "O(n * log(n))";
        } else if (n_power > 1 && log_power == 0) {
            return "O(n^" + n_power + ")";
        } else if (n_power > 1 && log_power == 1) {
            return "O(n^" + n_power + " * " + "log(n))";
        } else if (n_power == 0 && log_power > 1) {
            return "O(log(n)^" + log_power + ")";
        } else if (n_power == 1 && log_power > 1) {
            return "O(n" + " * " + "log(n)^" + log_power + ")";
        } else {
            return "O(n^" + n_power + " * " + "log(n)^" + log_power + ')';
        }
    }

    /**
     * Checks which of the two Complexity Objects are of a higher order (i.e. n^2 > n(log(n))
     * @param complexity2
     * Second Complexity object to be compared to the first one
     * @return
     * True if the first complexity object is of a higher order, false otherwise.
     */
    public boolean isHigherOrder(Complexity complexity2){
        if (this.getN_power() > complexity2.getN_power()) {
            return true;
        }
        else if (this.getN_power() == complexity2.getN_power() &&
                this.getLog_power() > complexity2.getLog_power()) {
            return true;
        }
        else if (this.getN_power() == complexity2.getN_power() &&
                this.getLog_power() == complexity2.getLog_power()) {
            return false;
        }
        else{
            return false;
        }
    }

    /**
     * Creates a single complexity object from one object that is popped from the stack and another that is pushed.
     * It combined the complexities of these two objects of the stack.
     * @param Complexity2
     * Second Complexity object to be compared.
     */
    public void collapseComplexities(Complexity Complexity2){
        Complexity2.setN_power(this.getN_power() +
                Complexity2.getN_power());
        Complexity2.setLog_power(this.getLog_power() +
                Complexity2.getLog_power());
    }

    public void collapseComplexities2(Complexity oldTop, Complexity peek){
        oldTop.setN_power(peek.getN_power() -
                oldTop.getN_power());
        oldTop.setLog_power(peek.getLog_power() -
                oldTop.getLog_power());
    }
}
