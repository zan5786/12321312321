package utilities;

import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyDLL() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException();
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index == size) {
            linkLast(toAdd);
        } else {
            Node<E> succ = (index == size) ? null : node(index);
            Node<E> pred = (index == 0) ? null : succ.prev;
            Node<E> newNode = new Node<>(toAdd, pred, succ);
            if (pred == null) {
                head = newNode;
            } else {
                pred.next = newNode;
            }
            if (succ == null) {
                tail = newNode;
            } else {
                succ.prev = newNode;
            }
            size++;
        }
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException();
        linkLast(toAdd);
        return true;
    }

    private void linkLast(E e) {
        final Node<E> l = tail;
        final Node<E> newNode = new Node<>(e, l, null);
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;
    }

    private Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return node(index).element;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return unlink(node(index));
    }

    private E unlink(Node<E> x) {
        final E element = x.element;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.element = null;
        size--;
        return element;
    }

    // Placeholder for methods like addAll, remove(E element), set, isEmpty, contains, toArray, iterator
    
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) throw new NoSuchElementException();
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }
    
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Collection to add cannot be null");

        Iterator<? extends E> iter = toAdd.iterator();
        while (iter.hasNext()) {
            add(iter.next());
        }
        return true;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Cannot set null element");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds: " + index);

        Node<E> x = node(index);
        E oldVal = x.element;
        x.element = toChange;
        return oldVal;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Search element cannot be null");

        for (Node<E> x = head; x != null; x = x.next) {
            if (toFind.equals(x.element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("Destination array cannot be null");
        if (toHold.length < size) {
            toHold = (E[])java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
        }
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            toHold[i++] = x.element;
        }
        if (toHold.length > size) {
            toHold[size] = null;
        }
        return toHold;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            result[i++] = x.element;
        }
        return result;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException("Cannot remove null elements");

        for (Node<E> x = head; x != null; x = x.next) {
            if (toRemove.equals(x.element)) {
                E removedItem = x.element;
                unlink(x);
                return removedItem;
            }
        }
        return null; 
    }


    
}
