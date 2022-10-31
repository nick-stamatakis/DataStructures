import java.util.ArrayList;

/**
 * Describes the Nested Block of Code
 *
 * @author Nicholas Stamatakis
 */

public class CodeBlock {
    /**
     * Static constants
     */
    public static String[] BLOCK_TYPES = {"def", "for", "while", "if", "elif", "else"};
    public static int DEF = 0;
    public static int FOR = 1;
    public static int WHILE = 2;
    public static int IF = 3;
    public static int ELIF = 4;
    public static int ELSE = 5;

    /**
     *Keeps track of nested structure of the blocks
     * The first block in the stack will always be named "1".
     * All blocks included directly under a block will be numbered increasingly using a dot "."
     * separator after the block's own name (e.g. blocks nested under block 1 will start with
     * "1.1" and proceed to "1.2", "1.3", etc). Similarly, all blocks included directly under
     * the block named "1.2" will be numbered "1.2.1", "1.2.2", "1.2.3", etc.
     * For more detail, see the sample I/O below.
     */
    private String name;

    /**
     * Keeps track of the Big-Oh complexity of the particular block
     */
    private Complexity blockComplexity;

    /**
     * Keeps track of the Big-Oh complexity of the highest-order block nested within this block
     *
     * The difference between these Complexity objects is that the blockComplexity represents
     * the order of the block ignoring the statements inside (e.g. O(n) for a while block looping
     * from n to 1), whereas the highestSubComplexity represents the highest complexity of all the
     * blocks nested inside this block.
     */
    private Complexity highestSubComplexity;

    /**
     * This variable will only be used for while blocks in this assignment, as for blocks will not
     * alter their variable during execution in the input code.
     */
    private String loopVariable;
    public static ArrayList<Integer> blockCount = new ArrayList<Integer>();

    /**
     * No arg constructor for CodeBlock Class
     */
    public CodeBlock(){
        name = "";
        blockComplexity = new Complexity();
        highestSubComplexity = new Complexity();
        loopVariable = "";
    }

    /**
     * Arg constructor for CodeBlock Class
     */
    public CodeBlock(String name1, Complexity blockComplexity1, Complexity highestSubComplexity1,
                     String loopVariable1){
        this.name = name1;
        this.blockComplexity = blockComplexity1;
        this.highestSubComplexity = highestSubComplexity1;
        this.loopVariable = loopVariable1;
    }

    /**
     * Getter method for name member variable
     * @return
     * Current string referenced by name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name member variable
     * @param name
     * Changes name to string inputted to method signature
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for member variable blockComplexity
     * @return
     * Current Complexity object referenced by the variable.
     */
    public Complexity getBlockComplexity() {
        return blockComplexity;
    }

    /**
     * Setter method for member variable blockComplexity
     * @param blockComplexity
     * Changes name to Complexity object inputted to method signature
     */
    public void setBlockComplexity(Complexity blockComplexity) {
        this.blockComplexity = blockComplexity;
    }

    /**
     * Getter method for member variable highestSubComplexity
     * @return
     * Current Complexity object referenced by the variable.
     */
    public Complexity getHighestSubComplexity() {
        return highestSubComplexity;
    }

    /**
     * Setter method for member variable highestSubComplexity
     * @param highestSubComplexity
     * Changes name to Complexity object inputted to method signature
     */
    public void setHighestSubComplexity(Complexity highestSubComplexity) {
        this.highestSubComplexity = highestSubComplexity;
    }

    /**
     * Getter method for loopVariable member variable
     * @return
     * Current string referenced by loopVariable
     */
    public String getLoopVariable() {
        return loopVariable;
    }

    /**
     * Setter method for loopVariable member variable
     * @param loopVariable
     * Changes name to string inputted to method signature
     */
    public void setLoopVariable(String loopVariable) {
        this.loopVariable = loopVariable;
    }

    public String toString(){
        return String.format("%-16s%-30s%s", " \n\t\tBLOCK " + name + ":", "block complexity = " +
                blockComplexity , "highest sub-complexity = " + highestSubComplexity + "\n");
    }
}
