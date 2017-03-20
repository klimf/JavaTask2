import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LinkedSet<E> {
    private int size;
    private Item main;

    public LinkedSet() {
        main = new Item<E>(null, null, null);
        main.prev = main;
        main.next = main;
    }

    private class Item<T> {
        public T value;
        public Item<T> prev;
        public Item<T> next;

        public Item(T value, Item<T> prev, Item<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

    }


    public void addFirst(E itemToAdd) {
        if (check(itemToAdd) != -1) {
            throw new IllegalArgumentException(size + ":" + itemToAdd.toString() + " == " + (check(itemToAdd) - 1) + ":" + get(check(itemToAdd) - 1));
        }
        Item<E> newItem = new Item<>(itemToAdd, main, main.next);
        newItem.prev.next = newItem;
        newItem.next.prev = newItem;
        size++;
    }

    public void add(E... itemToAdd) {
        for (int i = 0; i < itemToAdd.length; i++) {
            if (check(itemToAdd[i]) != -1) {
                throw new IllegalArgumentException(size + ":" + itemToAdd.toString() + " == " + (check(itemToAdd[i]) - 1) + ":" + get(check(itemToAdd[i]) - 1));
            }
            Item<E> newItem = new Item<>(itemToAdd[i], main.prev, main);
            newItem.prev.next = newItem;
            newItem.next.prev = newItem;
            size++;
        }
    }

    public void add(int index, E... itemToAdd) { //here it would be nice to optimise this function by combine check and insert methods (fixme)
        for (int i = itemToAdd.length - 1; i >= 0; i--) {
            if (check(itemToAdd[i]) != -1) {
                throw new IllegalArgumentException(size + ":" + itemToAdd[i].toString() + " == " + (check(itemToAdd[i]) - 1) + ":" + get(check(itemToAdd[i]) - 1));
            }
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index " + index + " isn't in range (0 - " + (size - 1) + ")");
            }
            Item<E> item = getItem(index);
            Item<E> newItem = new Item<>(itemToAdd[i], item.prev, item);
            newItem.prev.next = newItem;
            newItem.next.prev = newItem;
            size++;
        }
    }

    public void add(LinkedSet<E> set) {
        throw new NotImplementedException();
    }

    public void add(int index, LinkedSet<E> set) {
        throw new NotImplementedException();
    }

    public void change(int index, E itemToChange) {
        throw new NotImplementedException();
    }

    public void remove(int index) {
        throw new NotImplementedException();
    }

    public void remove(E itemToRemove) {
        throw new NotImplementedException();
    }

    private int check(E itemForCheck) {
        Item<E> item = main;
        for (int i = 0; i <= size; i++) {
            if (itemForCheck.equals(item.value)) {
                return i;
            }
            item = item.next;
        }
        return -1;//is it correct?
    }

    private Item<E> getItem(int index) {
        Item<E> item = main;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " isn't in range (0 - " + (size - 1) + ")");
        }
        for (int i = 0; i <= index; i++) { //Need optimisation, add 1 more "for" from size to index if index < size/2 (fixme)
            item = item.next;
        }
        return item;
    }

    public E get(int index) {
        Item<E> item = main;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " isn't in range (0 - " + (size - 1) + ")");
        }
        for (int i = 0; i <= index; i++) {
            item = item.next;
        }
        return item.value;
    }

    public int get(E itemForGetId) {
        Item<E> item = main;
        for (int i = 0; i <= size; i++) {
            item = item.next;
        }
        return 5;
    }

    public int size() {
        return size;
    }
}
