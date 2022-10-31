import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Traces/reads python file to determine the Order of Complexity
 *
 * @author Nicholas Stamatakis
 */

public class PythonTracer {

    /**
     * Used to determine the indentation of each statement
     */
    public static int SPACE_COUNT = 4; // number of spaces for an indent for these java files
    /**
     *Uses various methods within PythonTracer to determine time complexity of the Python file
     * @param args
     * Taking in Sting array argument.
     */
    public static void main(String[] args) {
        String fileNameFromUser = getFileName();//initializes string
        System.out.println();
        if (!fileNameFromUser.equals("quit")){//traces first file unless input is "quit"
            traceFile(fileNameFromUser);
        }
        while(!fileNameFromUser.equals("quit")){//Loops over files unless input is "quit"
            fileNameFromUser = getFileName();
            if (!fileNameFromUser.equals("quit")) {
                traceFile(fileNameFromUser);
            }
        }
        System.out.println();
        System.out.println("Program terminating successfully...");
    }

    /**
     * Prompt user to enter file name
     * @return
     * User input is returned
     */
    public static String getFileName(){
        Scanner input = new Scanner(System.in); //Initialize the Scanner
        System.out.print("Please enter a file name (or 'quit' to quit): "); //Print statement to user
        return input.next();
    }
    /**
     * Opens the indicated file, trace through the code of the Python function contained within the
     * file, and output the details of the trace and the overall complexity to the console.
     * @param filename
     * String representing the file name.
     * @return
     * The complexity object that details the Order of Complexity in the file
     */
   public static Complexity traceFile(String filename){
       BlockStack<CodeBlock> stack = new BlockStack();//Initializes stack to empty stack of codeblocks
       CodeBlock oldTop = new CodeBlock();
       Complexity oldTopComplexity = new Complexity();
       Complexity totalComplexity;
       try {
           Scanner scanner = new Scanner(new File("./" + filename));//Reads contents of file
           CodeBlock.blockCount.removeAll(CodeBlock.blockCount);
           //removes all elements in arraylist when new file is entered
           while (scanner.hasNextLine()) {
               String line = scanner.nextLine();//gives access to the contents of each line
               if (!line.isBlank() && !line.trim().startsWith("#")) {//checks whether to skip lines
//                   System.out.println(line);
                   CodeBlock codeBlock = new CodeBlock();
                   Complexity codeBlockComplexity = new Complexity();
                   int space_count = line.indexOf(line.trim());
                   int indents = space_count / SPACE_COUNT;//counts number of indents
                   //Goes in while when indentation goes back
                   while (indents < stack.size()) {
                       if (indents == 0){//case: if function is empty
                            //TODO: Close file
                           System.out.println("\tLeaving block 1.");
                           System.out.println("Overall complexity of " + filename.substring(0,filename.length()-3)
                                   + ": " + stack.peek().getHighestSubComplexity().toString());
                           return stack.peek().getHighestSubComplexity();
                       }
                       else{
                           poppingStack(oldTop, oldTopComplexity, stack);
                       }
                   }
                   String[] splitString = line.trim().split(" ");//splits string into an array of continuous characters

                   //Formats codeblocks if they contain a keyword
                   if (PythonTracer.hasKeyWord(splitString)) {
                       addToBlockCount(indents);
                       String keyword = splitString[0];
                       codeBlock.setName(getBlockNumber(CodeBlock.blockCount));
                       //Case: For Keyword
                       if (splitString[0].equals("for")) {
                           if (splitString[3].equals("N:")) {
                               codeBlockComplexity.setN_power(codeBlockComplexity.getN_power() + 1);
                               codeBlock.setBlockComplexity(codeBlockComplexity);
                           }
                           else if (splitString[3].equals("log_N:")) {
                               codeBlockComplexity.setLog_power(codeBlockComplexity.getLog_power() + 1);
                               codeBlock.setBlockComplexity(codeBlockComplexity);
                           }
                           stack.push(codeBlock);
                           PythonTracer.enteringBlock(PythonTracer.getBlockNumber(CodeBlock.blockCount),
                                   keyword, codeBlock);
                       }
                       //Case: While keyword
                       else if(splitString[0].equals("while")){
                           codeBlock.setLoopVariable(splitString[1]);
                           stack.push(codeBlock);
                           PythonTracer.enteringBlock(PythonTracer.getBlockNumber(CodeBlock.blockCount),
                                   keyword, codeBlock);
                       }
                       else{
                           PythonTracer.enteringBlock(PythonTracer.getBlockNumber(CodeBlock.blockCount),
                                   keyword, codeBlock);
                           stack.push(codeBlock);
                       }
                   }
                   else if(stack.peek()!= null &&
                           !stack.peek().getLoopVariable().isEmpty()){
                       if (line.contains(stack.peek().getLoopVariable())){
                           checkWhileConditional(line, "while", getBlockNumber(CodeBlock.blockCount),
                                   codeBlock, CodeBlock.blockCount, stack);
                       }
                   }
               }
               else{
                   //TODO: Ignore line
               }
           }
           //Case: if function is just a bunch of nested for loops
           while(stack.size() > 1){
               poppingStack(oldTop, oldTopComplexity, stack);
           }
           System.out.println("\tLeaving block 1.\n");
           System.out.println();
           System.out.println("Overall complexity of " + filename.substring(0,filename.length()-3)
                   + ": " + stack.peek().getHighestSubComplexity().toString());
       }
       catch (FileNotFoundException ex) {
           ex.printStackTrace();
       }
       return stack.peek().getHighestSubComplexity();
   }

   public static void poppingStack(CodeBlock oldTop, Complexity oldTopComplexity, BlockStack<CodeBlock> stack){
       oldTop = stack.pop();
       oldTopComplexity = oldTop.getBlockComplexity();
       oldTopComplexity.collapseComplexities(stack.peek().getBlockComplexity());
       Complexity blockComplexity = oldTop.getHighestSubComplexity();
       if (oldTopComplexity.isHigherOrder(stack.peek().getHighestSubComplexity())){
           stack.peek().setHighestSubComplexity(oldTopComplexity);
           blockComplexity.collapseComplexities2(blockComplexity, stack.peek().getHighestSubComplexity());
           System.out.println("\tLeaving block " + oldTop.getName() + " updating block: ");
           System.out.print(String.format("%-16s%-30s%s", " \t\tBLOCK " + stack.peek().getName() + ":", "block complexity = " +
                   oldTop.getHighestSubComplexity(), "highest sub-complexity = " + stack.peek().getHighestSubComplexity() + "\n"));
           System.out.println();
       }
       else{
           blockComplexity.collapseComplexities2(blockComplexity, stack.peek().getHighestSubComplexity());
           System.out.println("\tLeaving block " + oldTop.getName()  +", nothing to update.\n");
           System.out.printf("%-16s%-30s%s", " \t\tBLOCK " + stack.peek().getName() + ":", "block complexity = " +
                   oldTop.getHighestSubComplexity() , "highest sub-complexity = " +
                   stack.peek().getHighestSubComplexity() + "\n");
           System.out.println();
       }
   }
    /**
     * Increments blockCount ArrayList as needed
     * @param indents
     * Number of indents (n) determines which index is incremented
     */
    public static void addToBlockCount(int indents){
        //Cases: Initially empty, Normal nesting, Backtracking indents
        if(indents == 0){
            CodeBlock.blockCount.add(indents,1);
        }
        else if (indents == CodeBlock.blockCount.size()) {
            CodeBlock.blockCount.add(indents,1);
        }
        else{
            //Case:indents > size (impossible bc as indents increase, the size must increse too),
            //Case: size > indents
            //--implementation below
            if (CodeBlock.blockCount.size() > indents){
                for(int i = CodeBlock.blockCount.size()-1; i > indents; i--){
                    CodeBlock.blockCount.remove(i);
                }
                CodeBlock.blockCount.add(indents, CodeBlock.blockCount.get(indents) + 1);
                CodeBlock.blockCount.remove(indents+1);
            }
        }
    }

    /**
     * Prints Block Count Arraylist used to track the name of the block
     * @param blockCount
     * Takes in current ArrayList in method signature
     */
    public static void printBlockCount(ArrayList<Integer> blockCount){
        for (int i = 0; i < blockCount.size(); i++){
            System.out.print(blockCount.get(i) + " ");
        }
        System.out.println("\n");
    }

    /**
     * Checks what complexity to make while loop based on while condition
     * @param line
     * Line parsed by Scanner
     * @param keyword
     * Keyword of the particular line
     * @param blockNumber
     * Name of the Block
     * @param codeBlock
     * CodeBlock object to be pushed onto the stack
     * @param blockCount
     * ArrayList that becomes the blockNumber String
     * @param stack
     * Stack of CodeBlocks
     */
    public static void checkWhileConditional(String line, String keyword, String blockNumber, CodeBlock codeBlock,
                                             ArrayList<Integer> blockCount, BlockStack<CodeBlock> stack){
        String blockComplexity;
        String highestSubComplexity;
        line = line.trim();
        int indexOfSpace = line.indexOf(" ");
        line = line.substring(indexOfSpace).trim().substring(0,1);
        if (line.equals("*") || line.equals("/") || line.equals("+") || line.equals("-")){
            if (line.equals("*") || line.equals("/")){
                stack.peek().getBlockComplexity().setLog_power(stack.peek().getBlockComplexity().getLog_power() +1);
                System.out.print("\tFound update statement, updating block " +
                        getBlockNumber(blockCount) + ":");
                System.out.println();
            }
            else if(line.equals("+") || line.equals("-")){
                stack.peek().getBlockComplexity().setN_power(stack.peek().getBlockComplexity().getN_power() +1);
                System.out.println("\tFound update statement, updating block " +
                        getBlockNumber(blockCount) + ":");
                System.out.println();
            }
            System.out.println(stack.peek().toString());
//            blockNumber = "BLOCK " + codeBlock.getName();
//            blockComplexity = "block complexity = " + stack.peek().getBlockComplexity();
//            highestSubComplexity = "highest sub-complexity = " + stack.peek().getHighestSubComplexity();
//            System.out.printf("%18s%28s%36s", blockNumber, blockComplexity, highestSubComplexity );
//            System.out.println("\n");
        }
    }

    /**
     * Prints out statements for entering CodeBlocks
     * @param blockNumber
     * @param keyword
     * @param codeBlock
     */
    public static void enteringBlock(String blockNumber, String keyword, CodeBlock codeBlock){
        String blockComplexity;
        String highestSubComplexity;
        System.out.print("    Entering block " + codeBlock.getName() + " '" + keyword + "':\n");
        blockNumber = "BLOCK " + codeBlock.getName() + ":";
        blockComplexity = "block complexity = " + codeBlock.getBlockComplexity();
        highestSubComplexity = "highest sub-complexity = " + codeBlock.getHighestSubComplexity();
        System.out.printf("%18s%28s%36s", blockNumber, blockComplexity, highestSubComplexity );
        System.out.println("\n");
    }

    /**
     * Prints out the Complexities of a given block
     * @param blockCount
     * Determines number of block
     */
    public static String getBlockNumber(ArrayList<Integer> blockCount){
        String blockNumber = "";

        for(Integer i: blockCount){
            if (i != 0){
                blockNumber += i + ".";
            }
        }
        blockNumber = blockNumber.substring(0,blockNumber.length()-1);

        return blockNumber;
    }

    /**
     * Checks to see if line has a keyword
     * @param splitString
     * @return
     * True if line has keyword, false otherwise.
     */
    public static boolean hasKeyWord(String[] splitString){
        return splitString[0].equalsIgnoreCase("def") || splitString[0].equalsIgnoreCase("for") ||
                splitString[0].equalsIgnoreCase("while") || splitString[0].equalsIgnoreCase("if") ||
                splitString[0].equalsIgnoreCase("elif") || splitString[0].equalsIgnoreCase("else");
    }
}
