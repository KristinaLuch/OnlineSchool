package repository;

import exceptions.EntityNotFoundException;
import iterator.SimpleIterator;
import java.util.Arrays;

public class Rep<E>{

    private Object[] elements;

    private int size;

    private int capacity;

    public Rep(E[] elements) {
        this.elements = elements;
        this.size = elements.length;
    }

    public Rep() {
        this.size = 0;
        this.capacity = 10;
        this.elements = new Object[capacity];;
    }

    public int size(){
        return size;
    }

    boolean isEmpty(){
        return size == 0;
    }

    public E get (int index){
        return (E) elements[index];
    }


    public void add (E element){
        if(element == null){
            return;
        }
        add(element, size);

    }

    private void add (E element, int s){
        if(s == elements.length) {
            grow();
        }
        elements [s] = element;
        size = s + 1;
    }

    private void grow(){
        int newCapacity = (elements.length * 3) / 2 + 1;
        capacity = newCapacity;
        Arrays.copyOf(elements, newCapacity);
    }

    public void add (int index, E element) {
        checkIndex(index);
        elements[index] = element;
    }

    private void checkIndex(int index){
        if (index >= size){
            throw new IndexOutOfBoundsException("Index: "+index+" Size: "+ size);
        }
    }

    public void remove (int index){
        checkIndex(index);
        int newCapacity = size - 1;
        int different = capacity - newCapacity;
        if (different > 7){
            capacity = newCapacity;
        }
        delete(index);
        size = size-1;
    }

    private void delete(int deletedIndex){
        Object[] newObjects = new Object[capacity];
        for (int indexOldArray = 0, indexNewArray = 0, count = 0; indexOldArray < elements.length; count++, indexOldArray++, indexNewArray++){
            if(indexOldArray == deletedIndex){
                indexOldArray++;
            }
            newObjects[indexNewArray] = elements[indexOldArray];
        }
        elements = newObjects;
    }


    public E[] toArray(){
        if(size == capacity){
            return (E[]) elements;
        }
        E[] objToArray = (E[]) Arrays.copyOf(elements, size);
        return objToArray;
    }

    @Override
    public String toString() {
        if(size == capacity){
            return Arrays.toString(elements);
        }
        Object[] objToStr = Arrays.copyOf(elements, size);
        return Arrays.toString(objToStr);
    }

    public void findAll() throws EntityNotFoundException {
        SimpleIterator<E> simpleIterator = new SimpleIterator<>(this);
        while (simpleIterator.hasNext()){
            System.out.println(simpleIterator.next());
        }
    }

    public SimpleIterator<E> simpleIterator(){
        return new SimpleIterator<>(this);
    }

}
