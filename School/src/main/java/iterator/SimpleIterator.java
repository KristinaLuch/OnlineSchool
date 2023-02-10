package iterator;

import exceptions.EntityNotFoundException;

public class SimpleIterator<E> {

    Rep<E> rep;
    int cursor = 0;
    int getElement = -1;

    public SimpleIterator(Rep<E> rep) {
        this.rep = rep;
    }

    public boolean hasNext() {
        return cursor != rep.size();
    }

    public E next() throws EntityNotFoundException {
        if (cursor >= rep.size()){
            throw new EntityNotFoundException();
        }
        E element = rep.get(cursor);
        getElement = cursor;
        cursor++;
        return element;
    }

    public void remove() throws EntityNotFoundException {
        if (getElement < 0){
            throw new EntityNotFoundException();
        }
        rep.remove(getElement);
    }

}

