import java.util.Scanner;
/**
 *
 * The class should contain a single main method which allows a user to interact
 * with a file system implemented by an instance of DirectoryTree using the following commands
 * (note that commands are case-sensitive and will always be lower-case):
 *
 * @author Nicholas Stamatakis
 *
 */

public class BashTerminal {

    /**
     * Creates tree object
     */
    private DirectoryTree tree = new DirectoryTree();

    /**
     * creates BashTerminal Object
     */
    public static BashTerminal terminal = new BashTerminal();

    /**
     * Allows a user to interact with a file system implemented by an instance of DirectoryTree
     * using commands.
     * @param args
     * Takes in an Array of Strings named args
     */
    public static void main(String[] args) {
        //Creates instance of Scanner
        Scanner in = new Scanner(System.in);

        //Starts bash Terminal
        System.out.println("Starting bash terminal.");

        String userInput = "";
        do {
            try {
                System.out.print("[user@host]: $ ");
                userInput = in.nextLine();
                terminal.commands(userInput);
            }
            catch (IllegalArgumentException ex){
                System.out.println("\nThis is an invalid input. Please try again.\n");
            }
        }
        while (!userInput.equals("exit"));

        System.out.println("Program terminating normally");
    }

    /**
     * Addresses user input with various methods
     * @param userInput
     * String inputed to Scanner
     */
    public void commands(String userInput) {
        try{
            //Print the "present working directory" of the cursor node (e.g root/home/user/Documents).
            if (userInput.equals("pwd")) {
                System.out.println(tree.presentWorkingDirectory());
            }
            //List the names of all the child directories or files of the cursor.
            else if (userInput.equals("ls")) {
                System.out.println(tree.listDirectory());
            }
            //Recursive traversal of the directory tree. Prints the entire tree starting from the cursor in
            //pre-order traversal.
            else if (userInput.equals("ls -R")) {
                tree.printDirectoryTree();
            }
            else if (userInput.startsWith("cd ")) {
                //Moves the cursor to the root of the tree.
                if (userInput.substring(3).trim().equals("/")) {
                    tree.resetCursor();
                }
                // Moves the cursor to the child directory with the indicated name
                // (Only consider the direct children of the cursor).
                else {
                    String nameOfDirectory = userInput.substring(3).trim();
                    if(tree.checkNames(nameOfDirectory)){
                        tree.changeDirectory(nameOfDirectory);
                    }
                    else{
                        throw new IllegalArgumentException();
                    }
                }
            }
            else if (userInput.startsWith("mkdir ")){
                String nameOfDirectory = userInput.substring(6).trim();
                if(!(tree.makeDirectory(nameOfDirectory))){
                    throw new FullDirectoryException();
                }
            }
            else if (userInput.startsWith("touch ")){
                String nameOfFile = userInput.substring(6).trim();
                if(!(tree.makeFile(nameOfFile))){
                    throw new FullDirectoryException();
                }
            }
            else if (userInput.equals("exit")){

            }
            else{
                System.out.println("Please try again. This is not a valid input.");
            }
        }
        catch(NotADirectoryException ex){
            System.out.println("ERROR: Cannot change directory into a file.");
        }
        catch(FullDirectoryException ex){
            System.out.println("ERROR: Present directory is full.");
        }
        catch(IllegalArgumentException ex){
            System.out.println("ERROR: No such directory named '" +
                    userInput.split(" ")[1] + "'");
        }
    }
}
