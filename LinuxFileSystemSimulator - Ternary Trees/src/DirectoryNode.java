/**
 *
 * Represents a node in the file tree.
 *
 * @author Nicholas Stamatakis
 *
 */

public class DirectoryNode {
    //Constructors
    /**
     * No arg constructor
     */
    public DirectoryNode(){
        name = "";
        isFile = false;
        left = null;
        middle = null;
        right = null;
        depth = 0;
        //parent = null;
    }
    /**
     * Arg constructor
     */
    public DirectoryNode(String name1, boolean isFile1, DirectoryNode left1, DirectoryNode middle1,
                         DirectoryNode right1){
        name = name1;
        isFile = isFile1;
        left = left1;
        middle = middle1;
        right = right1;
//        parent = parent1;
    }

    /**
     * String that accumulates directory path to a particular
     */
    public static String directoryPath = "root";

    /**
     * Indicates the name of the node in the tree
     * The name member variable should be a full string with no spaces,
     * tabs, or any other whitespace.
     */
    private String name;

    /**
     * reference to the left node of a given ternary tree node
     */
    private DirectoryNode left;

    /**
     * reference to the middle node of a given ternary tree node
     */
    private DirectoryNode middle;

    /**
     * reference to the right node of a given ternary tree node
     */
    private DirectoryNode right;

    /**
     * Reference to parent
     */
    private int depth;

    /**
     * Getter for depth
     * @return
     * returns current depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Setter for depth
     * @param depth
     * sets depth to desired value
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Since DirectoryNodes can be either a file or a folder,
     * include a boolean member variable named isFile to differentiate between the two.
     * Note that if this value is set to true, then the node is not a directory,
     * and therefore should NOT contain any children.
     * That is, files are not allowed to have children.
     */
    private boolean isFile;

//Setters/Getters

    /**
     * Getter for name
     * @return
     * String value of name
     */
    public String getName(){
        return name;
    }

    /**
     * Setter for name
     * @param name1
     * Change name value to input in method signature
     */
    public void setName(String name1){
        name = name1;
    }

    /**
     * Getter for left
     * @return
     * DirectoryNode value of left
     */
    public DirectoryNode getLeft(){
        return left;
    }

    /**
     * Setter for left
     * @param left
     * takes in DirectoryNode in method signature
     */
    public void setLeft(DirectoryNode left) {
        this.left = left;
    }

    /**
     * Getter for middle
     * @return
     * DirectoryNode value of middle
     */
    public DirectoryNode getMiddle(){
        return middle;
    }

    /**
     * Setter for middle
     * @param middle
     * takes in DirectoryNode in method signature
     */
    public void setMiddle(DirectoryNode middle) {
        this.middle = middle;
    }

    /**
     * Getter for right
     * @return
     * DirectoryNode value of right
     */
    public DirectoryNode getRight(){
        return right;
    }

    /**
     * Setter for right
     * @param right
     * takes in DirectoryNode in method signature
     */
    public void setRight(DirectoryNode right) {
        this.right = right;
    }

    /**
     * Getter for isFile
     * @return
     * value of isFile currently
     */
    public boolean getIsFile() {
        return isFile;
    }

    /**
     * Setter for isFile
     * @param isFile1
     * Takes in value for isFile in method signature
     */
    public void setIsFile(boolean isFile1) {
        this.isFile = isFile1;
    }

    /**
     * Adds newChild to any of the open child positions of this node (left, middle, or right).
     * NOTE: Children should be added to this node in left-to-right order, i.e. left is filled first,
     * middle is filled second, and right is filled last
     *
     * @param newChild
     * DirectoryNode that is being added
     * @custom.precondition
     * This node is not a file.
     * There is at least one empty position in the children of this node (left, middle, or right).
     * @throws FullDirectoryException
     * Thrown if the current node is a file,
     * as files cannot contain DirectoryNode references (i.e. all files are leaves).
     * @throws NotADirectoryException
     * Thrown if all child references of this directory are occupied.
     * @custom.postcondition
     * newChild has been added as a child of this node.
     * If there is no room for a new node, throw a FullDirectoryException.
     */
    public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException{
        //throws exceptions
        if (this.isFile){
            throw new NotADirectoryException();
        }
        if (this.left != null && this.middle != null && this.right != null){
            throw new FullDirectoryException();
        }
        //adds child to node based on the preference (left, middle, right)
        if (this.left == null){
            this.left = newChild;
        }
        else if (this.middle == null){
            this.middle = newChild;
        }
        else{
            this.right = newChild;
        }
    }

    /**
     * Method to figure out depth of each node
     */
    public void markDepth(DirectoryNode node, int depthDefault){
        if (node == null) return;

        node.depth = depthDefault;

        markDepth(node.left, depthDefault+1);
        markDepth(node.middle, depthDefault+1);
        markDepth(node.right, depthDefault+1);
    }
    /**
     * PreOrder traversal method in node class
     */
    public void preOrder(){
        if (!this.isFile){
            for (int i = 0; i < this.depth; i++){
                System.out.print("\t");
            }
            System.out.println("|- " + this.getName());
        }
        else{
            for (int i = 0; i < this.depth; i++){
                System.out.print("\t");
            }
            System.out.println("- " + this.getName());
        }
        if (left != null){
            left.preOrder();
        }
        if (middle != null){
            middle.preOrder();
        }
        if (right != null){
            right.preOrder();
        }
    }
}
