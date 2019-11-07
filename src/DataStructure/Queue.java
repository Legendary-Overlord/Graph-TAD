package DataStructure;

import java.util.NoSuchElementException;

public class Queue<E> {

	private int n;       
    private Node first;    
    private Node last;     


    private class Node {
        private E item; 
        private Node next;  
    }

  
    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

 
    public boolean isEmpty() {
        return first == null;
    }


    public int size() {
        return n;
    }

   
    public int length() {
        return n;
    }

   
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

   
    public void enqueue(E e) {
        Node oldlast = last;
        last = new Node();
        last.item = e;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

 
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        E e = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;  
        return e;
    }
}

 