/**
 * Uses Java API for Stacks
 *
 * @author Nicholas Stamatakis
 * ID: 114140995
 * Email: nicholas.stamatakis@stonybrook.edu
 * Homework #2
 * CSE 214 Recitation 03 with Alexander Leong and Sean Xia
 * Grading TA: Rohan Bansal & Ashish Kumar
 */
import java.util.Stack;

public class BlockStack<CodeBlock>{
    /**
     * Creates Stack object using Java API for stacks
     */
    Stack<CodeBlock> stack = new Stack<CodeBlock>();
    /**
     * Used a counter to determine the size of the stack at any given time
     */
    private int sizeOfStack;

    /**
     * Pushes element on top of the stack
     * @param block
     * block is the element pushed onto the stack
     */
    public void push(CodeBlock block){
        if (block != null){
            sizeOfStack++;
            stack.push(block);
        }
    }

    /**
     * Removes the object at the top of this stack and returns
     * that object as the value of this function.
     * @return
     * CodeBlock object that was removed from the top of the stack.
     */
    public CodeBlock pop(){
        if (!stack.isEmpty()){
            sizeOfStack--;
            return stack.pop();
        }
        return null;
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     * @return
     * CodeBlock object that was looked at in the peek() method.
     */
    public CodeBlock peek(){
        if (!stack.isEmpty()){
            return stack.peek();
        }
        return null;
    }

    /**
     * Gives the size of stack.
     * @return
     * int that represents the current size of the stack
     */
    public int size(){
        return sizeOfStack;
    }

    /**
     * Tests if this stack is empty.
     * @return
     * If the stack is empty, return true. Otherwise, it returns false.
     */
    public boolean isEmpty(){
        return stack.empty();
    }


}
