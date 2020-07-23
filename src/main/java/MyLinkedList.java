import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private int size = 0;
    private MyNode<T> header;

    public MyLinkedList() {
        header = new MyNode(null, null, null);
    }

    private class MyNode<T> {
        T element;
        MyNode<T> prev;
        MyNode<T> next;

        public MyNode(T element, MyNode<T> prev, MyNode<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyNode<T> getNext(MyNode<T> currentNode) {
        if (currentNode == null) {
            return null;
        } else {
            return currentNode.next;
        }
    }

    public MyNode<T> getPrev(MyNode<T> currentNode) {
        if (currentNode == null) {
            return null;
        } else {
            return currentNode.prev;
        }
    }

    @Override
    public boolean add(T value) {
        MyNode<T> currentNode = header;

        for (int i = 0; i < size; i++) {
            currentNode = getNext(currentNode);
        }

        MyNode<T> newNode = new MyNode<>(value, currentNode, null);
        currentNode.next = newNode;

        if (newNode == null) {
            return false;
        } else {
            size++;
            return true;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        MyNode<T> currentNode = header;

        for (int i = 0; i < index; i++) {
            currentNode = getNext(currentNode);
        }

        MyNode<T> newNode = new MyNode<>(value, currentNode, currentNode.next);
        if (currentNode.next != null) {
            currentNode.next.prev = newNode;
        }
        currentNode.next = newNode;
        size++;
    }

    @Override
    public boolean addAll(List<T> list) {
        boolean isAdded = false;

        for (T value: list) {
            isAdded = add(value);
        }

        return isAdded;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        MyNode<T> currentNode = header;

        for (int i = 0; i <= index; i++) {
            currentNode = getNext(currentNode);
        }

        return currentNode.element;
    }

    @Override
    public T set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        MyNode<T> currentNode = header;

        for (int i = 0; i <= index; i++) {
            currentNode = getNext(currentNode);
        }

        T oldValue = currentNode.element;
        currentNode.element = value;

        return oldValue;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        MyNode<T> currentNode = header;

        for (int i = 0; i <= index; i++) {
            currentNode = getNext(currentNode);
        }

        if (currentNode.prev != null) {
            currentNode.prev.next = currentNode.next;
        }

        if (currentNode.next != null) {
            currentNode.next.prev = currentNode.prev;
        }

        size--;
        return currentNode.element;
    }

    @Override
    public boolean remove(T t) {
        boolean isDeleted = false;
        MyNode<T> currentNode = header;

        for (int i = 0; i < size; i++) {
            currentNode = getNext(currentNode);
            if (currentNode.element == t || currentNode.element.equals(t)) {
                isDeleted = true;
                size--;
                break;
            }
        }

        if (currentNode.prev != null && isDeleted == true) {
            currentNode.prev.next = currentNode.next;
        }

        if (currentNode.next != null && isDeleted == true) {
            currentNode.next.prev = currentNode.prev;
        }

        return isDeleted;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0 ? true : false;
    }
}
