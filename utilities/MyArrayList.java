package utilities;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements ListADT<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData; 
    private int size = 0; 

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException("Cannot add null element.");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        ensureCapacity(size + 1);  
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = toAdd;
        size++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot add null element.");
        ensureCapacity(size + 1);
        elementData[size++] = toAdd;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        Object[] a = toAdd.toArray();
        int numNew = a.length;
        ensureCapacity(size + numNew); 
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return (E) elementData[index];
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        E oldValue = (E) elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null; 

        return oldValue;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException("Cannot remove null element.");

        for (int index = 0; index < size; index++) {
            if (toRemove.equals(elementData[index])) {
                return remove(index);
            }
        }
        return null; 
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Cannot set null element.");
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        E oldValue = (E) elementData[index];
        elementData[index] = toChange;
        return oldValue;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Cannot search for null element.");
        return indexOf(toFind) >= 0;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("Input array cannot be null.");
        if (toHold.length < size) {
            return (E[]) Arrays.copyOf(elementData, size, toHold.getClass());
        }
        System.arraycopy(elementData, 0, toHold, 0, size);
        if (toHold.length > size) {
            toHold[size] = null;
        }
        return toHold;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (E) elementData[index++];
            }
        };
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }
}
