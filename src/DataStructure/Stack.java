package DataStructure;

import java.util.ArrayList;

public class Stack<E> {

    private ArrayList<E> stack;

    public Stack() {
    	
        stack = new ArrayList<E>();
        
    }

    public E peek() {

        if (isEmpty()) throw new AssertionError("Peek Failed: Stack is empty");

        return stack.get(size() - 1);
    }

    public E pop() {
    	
        if (isEmpty()) throw new AssertionError("Pop Failed: Stack is empty");

        return stack.remove(size() - 1);
    }

    public void push(E value) {
        stack.add(value);
    }

    public int size() { 
    	
    	return stack.size(); 
    	
    }

    public boolean isEmpty() { 
    
    	return (size() == 0); 
    	
    }

    
}