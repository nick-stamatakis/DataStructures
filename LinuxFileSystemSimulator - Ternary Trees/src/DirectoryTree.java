/**
 * Implements a ternary (3-child) tree of DirectoryNodes.
 *
 * The class should contain methods for moving the cursor through the file system,
 * printing the filepath of the present working directory (cursor location),
 * listing the directories and files in the present working directory,
 * printing the entire file system, and finding a file in the file system.
 *
 * @author Nicholas Stamatakis
 *
 */
public class DirectoryTree {
    //
    private DirectoryTree directoryTree;

    //Member Variables
    /**
     * Root of the tree
     */
    private DirectoryNode root;
    /**
     * Reference to Tree as we go through it
     */
    private DirectoryNode cursor;

    /**
     * No-arg constructor: Initializes a DirectoryTree object with a single DirectoryNode named "root".
     * @custom.postcondition
     * The tree contains a single DirectoryNode named "root", and both cursor and root reference this node.
     */
    public DirectoryTree(){
        root = new DirectoryNode();
        root.setName("root");
        root.setIsFile(false);
        cursor = root;
    }

    /**
     * Arg constructor: Initializes a DirectoryTree object with a single DirectoryNode named "root".
     * @custom.postcondition
     * The tree contains a single DirectoryNode named "root", and both cursor and root reference this node.
     */
    public DirectoryTree(DirectoryNode root1){
        root = root1;
        cursor = (root);
    }

    /**
     * Moves the cursor to the root node of the tree.
     * @custom.postcondition
     * The cursor now references the root node of the tree.
     */
    public void resetCursor(){
        DirectoryNode.directoryPath = "root";
        cursor = root;
    }

    /**
     * Checks whether the name is legal
     * @param name
     * name of the directory
     * @return
     * boolean value that represents whether that name was found within the children of the cursor
     * @throws IllegalArgumentException
     * Throw if name is not found, meaning the user input of the name was invalid.
     */
    public boolean checkNames(String name){
        DirectoryNode[] nodes = {cursor.getLeft(), cursor.getMiddle(), cursor.getRight()};
        //Goes through a for loop to check if they have the desired name
        // and if it is a file or not
        for(DirectoryNode node : nodes){
            if (node != null && node.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    /**
     * Moves the cursor to the directory with the name indicated by name.
     * @param name
     * name of the directory
     * @custom.precondition
     * 'name' references a valid directory ('name' cannot reference a file).
     * @throws NotADirectoryException
     * Makes sure that it is not a file
     * Thrown if the node with the indicated name is a file,
     * as files cannot be selected by the cursor, or cannot be found.
     * @custom.postcondition
     * The cursor now references the directory with the name indicated by name.
     * If a child could not be found with that name, then the user is prompted to enter
     * a different directory name.
     */
    public void changeDirectory(String name) throws NotADirectoryException{
        //throw exception if not a directory initially
        if (cursor.getName().equals(name) && cursor.getIsFile()){
            throw new NotADirectoryException();
        }
        //Creates array of the children of the cursor node
        DirectoryNode[] nodes = new DirectoryNode[3];
        nodes[0] = cursor.getLeft();
        nodes[1] = cursor.getMiddle();
        nodes[2] = cursor.getRight();
        //Goes through a for loop to check if they have the desired name
        // and if it is a file or not
        for(DirectoryNode node : nodes){
            if (node != null && node.getName().equals(name)){
                if (!node.getIsFile()){
                    cursor = node;
                    DirectoryNode.directoryPath += "/" + node.getName();
                }
                else{
                    throw new NotADirectoryException();
                }
            }
        }
    }

    /**
     * Returns a String containing the path of directory names
     * from the root node of the tree to the cursor,
     * with each name separated by a forward slash "/".
     * e.g. root/home/user/Documents if the cursor is at Documents in the example above.
     * @return
     * String containing path of directory names from the root to cursor
     * @custom.postcondition
     * The cursor remains at the same DirectoryNode.
     */
    public String presentWorkingDirectory(){
        return DirectoryNode.directoryPath;
    }

    /**
     * Returns a String containing a space-separated list of names of all
     * the child directories or files of the cursor.
     * e.g. dev home bin if the cursor is at root in the example above.
     * @return
     * A formatted String of DirectoryNode names.
     */
    public String listDirectory(){
        //creates string builder to be added to be added to
        String res = "";
        //creates array to store children of cursor
        DirectoryNode[] nodes = new DirectoryNode[3];
        nodes[0] = cursor.getLeft();
        nodes[1] = cursor.getMiddle();
        nodes[2] = cursor.getRight();
        for(DirectoryNode node : nodes){
            if (node != null){
                res += node.getName() + " ";
            }
        }
        if (res.length() > 2){
            res = res.substring(0, res.length() - 1);
        }
        //TODO figure out how to properly trim down res

        return res.toString();
    }

    /**
     * Prints a formatted nested list of names of all the nodes in the directory tree,
     * starting from the cursor.
     * See sample I/O for an example.
     * @custom.postcondition
     * The cursor remains at the same DirectoryNode
     */
    public void printDirectoryTree(){
        DirectoryNode newNode = cursor;
        newNode.markDepth(newNode, 0);
        newNode = cursor;
        System.out.println();
        newNode.preOrder();
        System.out.println();
    }


    /**
     * Creates a directory with the indicated name and adds it to the children of the cursor node.
     * Remember that children of a node are added in left-to-right order.
     * @param name
     * name of the directory
     * @custom.precondition
     * 'name' is a legal argument (does not contain spaces " " or forward slashes "/").
     * @throws IllegalArgumentException
     * Thrown if the 'name' argument is invalid.
     * @throws FullDirectoryException
     * Thrown if all child references of this directory are occupied.
     */
    public boolean makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException{
        //Checks precondition
        int num1 = name.indexOf('/');
        int num2 = name.indexOf(' ');
        if (num1 + num2 != -2){
            throw new IllegalArgumentException();
        }

        //Tries to add to left child
        if (cursor.getLeft() == null){
            DirectoryNode newChild = new DirectoryNode(name, false, null, null, null);
            cursor.setLeft(newChild);
            return true;
        }
        //Tries to add to right child
        else if (cursor.getMiddle() == null){
            DirectoryNode newChild = new DirectoryNode(name, false, null, null, null);
            cursor.setMiddle(newChild);
            return true;
        }
        //Tries to add to middle child
        else if (cursor.getRight() == null){
            DirectoryNode newChild = new DirectoryNode(name, false, null, null, null);
            cursor.setRight(newChild);
            return true;
        }
        return false;
    }

    /**
     * Creates a file with the indicated name and adds it to the children of the cursor node.
     * Remember that children of a node are added in left-to-right order.
     * @param name
     * name The name of the file to add.
     * @custom.precondition
     * 'name' is a legal argument (does not contain spaces " " or forward slashes "/").
     * @throws IllegalArgumentException
     * Thrown if the 'name' argument is invalid.
     * @throws FullDirectoryException
     * Thrown if all child references of this directory are occupied.
     * @custom.postcondition
     * A new DirectoryNode has been added to the children of the cursor,
     * or an exception has been thrown.
     */
    public boolean makeFile(String name) throws IllegalArgumentException, FullDirectoryException{
        //Checks precondition
        int num1 = name.indexOf('/');
        int num2 = name.indexOf(' ');
        if (num1 + num2 != -2){
            throw new IllegalArgumentException();
        }

        //Tries to add to left child
        if (cursor.getLeft() == null){
            DirectoryNode newChild = new DirectoryNode(name, true, null, null, null);
            cursor.setLeft(newChild);
            return true;
        }
        //Tries to add to right child
        else if (cursor.getMiddle() == null){
            DirectoryNode newChild = new DirectoryNode(name, true, null, null, null);
            cursor.setMiddle(newChild);
            return true;
        }
        //Tries to add to middle child
        else if (cursor.getRight() == null){
            DirectoryNode newChild = new DirectoryNode(name, true, null, null, null);
            cursor.setRight(newChild);
            return true;
        }
        return false;
    }
}
