package sd.java.lab7_8;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class CustomList<T> implements List<T> {

    private Node<T> head;
    private int size;
    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public CustomList() {
        this.head = null;
        this.size = 0;
    }

    public CustomList(T singleElement) {
        this.head = new Node<>(singleElement);
        this.size = 1;
    }

    public CustomList(Collection<? extends T> collection) {
        this();

        if (collection != null) {
            addAll(collection);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();

                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public Object @NotNull [] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<T> current = head;

        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }

        return array;
    }

    @Override
    public <T1> T1 @NotNull [] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(toArray(), size, a.getClass());
        } else {
            int index = 0;
            Node<T> current = head;

            while (current != null) {
                a[index++] = (T1) current.data;
                current = current.next;
            }

            if (a.length > size) {
                a[size] = null;
            }
        }
        return a;
    }

    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        return List.super.toArray(generator);
    }

    @Override
    public boolean add(T t) {
        Node<T> newNode = new Node<>(t);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) {
            if (current.data.equals(o)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o: c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isAdded = false;
        for (T t: c) {
            add(t);
            isAdded = true;
        }
        return isAdded;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        boolean isAdded = false;

        for (T t: c) {
            add(index++, t);
            isAdded = true;
        }

        return isAdded;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;

        for (Object o: c) {
            if (remove(o)) {
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        boolean isRemoved = false;

        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) {
            if (filter.test(current.data)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                isRemoved = true;
            }
            previous = current;
            current = current.next;
        }
        if (size == 0) {
            head = null;
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        boolean isRemoved = false;

        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) {
            if (!c.contains(current.data)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                isRemoved = true;
            }
            previous = current;
            current = current.next;
        }

        return isRemoved;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        Node<T> current = head;

        while (current != null) {
            current.data = operator.apply(current.data);
            current = current.next;
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        List.super.sort(c);
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node<T> current = head;
        int currentIndex = 0;

        while (current != null) {
            if (currentIndex == index) {
                return current.data;
            }
            currentIndex++;
            current = current.next;
        }

        return null;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node<T> current = head;
        int currentIndex = 0;

        while (current != null) {
            if (currentIndex == index) {
                current.data = element;
                return element;
            }
            currentIndex++;
            current = current.next;
        }

        return null;
    }

    @Override
    public void add(int index, T element) {
        if (size == 0) {
            add(element);
            return;
        }

        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();


        Node<T> newNode = new Node<>(element);
        Node<T> current = head;
        Node<T> previous = null;
        int currentIndex = 0;

        while (current != null) {
            if (currentIndex == index) {
                if (previous == null) {
                    newNode.next = head;
                    head = newNode;
                } else {
                    previous.next = newNode;
                    newNode.next = current;
                }
                size++;
                return;
            }
            currentIndex++;
            previous = current;
            current = current.next;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node<T> current = head;
        Node<T> previous = null;
        int currentIndex = 0;

        while (current != null) {
            if (currentIndex == index) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.data;
            }
            currentIndex++;
            previous = current;
            current = current.next;
        }

        return null;
    }

    @Override
    public int indexOf(Object o) {
        Node<T> current = head;
        int currentIndex = 0;

        while (current != null) {
            if (current.data.equals(o)) {
                return currentIndex;
            }
            currentIndex++;
            current = current.next;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<T> current = head;
        int currentIndex = 0;
        int lastIndex = -1;

        while (current != null) {
            if (current.data.equals(o)) {
                lastIndex = currentIndex;
            }
            currentIndex++;
            current = current.next;
        }

        return lastIndex;
    }

    private class CustomListIterator implements ListIterator<T> {
        private Node<T> nextNode;
        private Node<T> lastReturnedNode;
        private int nextIndex;

        private CustomListIterator(int index) {
            if (index < 0 || index > size) throw new IndexOutOfBoundsException();

            nextNode = (index == size) ? null : getNode(index);
            nextIndex = index;
        }


        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            lastReturnedNode = nextNode;
            nextNode = nextNode.next;
            nextIndex++;
            return lastReturnedNode.data;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) throw new NoSuchElementException();

            if (nextNode == null) {
                nextNode = getNode(nextIndex - 1);
            } else {
                nextNode = nextNode.next;
            }

            lastReturnedNode = nextNode;
            nextNode = getNode(nextIndex - 1);
            nextIndex--;

            return lastReturnedNode.data;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex -1;
        }

        @Override
        public void remove() {
            if (lastReturnedNode == null) throw new IllegalStateException();

            if (lastReturnedNode == head) {
                head = head.next;
            } else {
                Node<T> previous = getNode(nextIndex - 2);
                previous.next = lastReturnedNode.next;
            }

            if (nextNode == lastReturnedNode) {
                nextNode = lastReturnedNode.next;
            }

            lastReturnedNode = null;
            size--;
        }

        @Override
        public void set(T t) {
            if (lastReturnedNode == null) throw new IllegalStateException();

            lastReturnedNode.data = t;
        }

        @Override
        public void add(T t) {
            Node<T> newNode = new Node<>(t);

            if (nextNode == head) {
                newNode.next = head;
                head = newNode;
            } else {
                Node<T> previous = getNode(nextIndex - 2);
                previous.next = newNode;
                newNode.next = nextNode;
            }

            lastReturnedNode = null;
            size++;
            nextIndex++;
        }
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    @Override
    public @NotNull ListIterator<T> listIterator() {
        return new CustomListIterator(0);
    }

    @Override
    public @NotNull ListIterator<T> listIterator(int index) {
        return new CustomListIterator(index);
    }

    @Override
    public @NotNull CustomList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) throw new IndexOutOfBoundsException();

        CustomList<T> subList = new CustomList<>();
        Node<T> current = getNode(fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.data);
            current = current.next;
        }

        return subList;
    }

    @Override
    public Spliterator<T> spliterator() {
        return List.super.spliterator();
    }

    @Override
    public Stream<T> stream() {
        return List.super.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return List.super.parallelStream();
    }

    @Override
    public void addFirst(T t) {
        List.super.addFirst(t);
    }

    @Override
    public void addLast(T t) {
        List.super.addLast(t);
    }

    @Override
    public T getFirst() {
        return List.super.getFirst();
    }

    @Override
    public T getLast() {
        return List.super.getLast();
    }

    @Override
    public T removeFirst() {
        return List.super.removeFirst();
    }

    @Override
    public T removeLast() {
        return List.super.removeLast();
    }

    @Override
    public List<T> reversed() {
        return List.super.reversed();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        List.super.forEach(action);
    }

}
