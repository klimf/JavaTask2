import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LinkedSet<E> {
    private int size;
    private Item main;

    {
        main = new Item<E>(null, null, null);
        main.prev = main;
        main.next = main;
    }

    public LinkedSet(E... itemsToAdd) {
        add(itemsToAdd);
    }

    private static class Item<T> {
        public T value;
        public Item<T> prev;
        public Item<T> next;

        public Item(T value, Item<T> prev, Item<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

    }

    public void addFirst(E... itemToAdd) {
        for (int i = itemToAdd.length - 1; i >= 0; i--) {
            if (check(itemToAdd[i]) != -1) {
                throw new IllegalArgumentException(size + ":" + itemToAdd[i].toString() + " == " + (check(itemToAdd[i]) - 1) + ":" + get(check(itemToAdd[i]) - 1));
            }
            Item<E> newItem = new Item<>(itemToAdd[i], main, main.next);
            newItem.prev.next = newItem;
            newItem.next.prev = newItem;
            size++;
        }
    }

    public void add(E... itemToAdd) {
        for (int i = 0; i < itemToAdd.length; i++) {
            if (check(itemToAdd[i]) != -1) {
                throw new IllegalArgumentException(size + ":" + itemToAdd[i].toString() + " == " + (check(itemToAdd[i]) - 1) + ":" + get(check(itemToAdd[i]) - 1));
            }
            Item<E> newItem = new Item<>(itemToAdd[i], main.prev, main);
            newItem.prev.next = newItem;
            newItem.next.prev = newItem;
            size++;

//            System.out.println("m.p.n.v = " + main.prev.next.value + " m.n.p.v = " + main.next.prev.value);
        }
    }

    public void add(int index, E... itemToAdd) { //fixme Here it would be nice to optimise this function by combine check and insert methods
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

    public void add(LinkedSet<E> newSet) {//fixme Add check
        Item<E> item = main;
        for (int i = 0; i < newSet.size(); i++) {
            if (check(newSet.get(i)) != -1) {
                throw new IllegalArgumentException(size + i + ":" + newSet.get(i).toString() + " == " + (check(newSet.get(i)) - 1) + ":" + get(check(newSet.get(i)) - 1));
            }
            item = item.next;
        }
        main.prev.next = newSet.main.next;
        main.next.prev = newSet.main.prev;
        newSet.main.prev.next = main;
        newSet.main.next.prev = main.prev;
        size += newSet.size();
    }

    public void add(int index, LinkedSet<E> newSet) {
        Item<E> item = main;
        for (int i = 0; i < newSet.size(); i++) {
            if (check(newSet.get(i)) != -1) {
                throw new IllegalArgumentException(size + i + ":" + newSet.get(i).toString() + " == " + (check(newSet.get(i)) - 1) + ":" + get(check(newSet.get(i)) - 1));
            }
            item = item.next;
        }
        item = getItem(index);
        item.prev.next = newSet.main.next;
        item.next.prev = newSet.main.prev;
        newSet.main.prev.next = item;
        newSet.main.next.prev = item.prev;
        size += newSet.size();
    }

    public void replace(int index, E itemToChange) {
        getItem(index).value = itemToChange;
    }

    public void remove(int index) {
        Item<E> item = getItem(index);
        item.next.prev = item.prev;
        item.prev.next = item.next;
    }

    public void remove(E itemToRemove) {
        Item<E> item = getItem(itemToRemove);
        item.next.prev = item.prev;
        item.prev.next = item.next;
    }

    public void remove(int index1, int index2) {
        if (index1 < 0 || index1 >= size) {
            throw new IndexOutOfBoundsException("Index 1 " + index1 + " isn't in range (0 - " + (size - 1) + ")");
        }
        if (index2 < 0 || index2 >= size) {
            throw new IndexOutOfBoundsException("Index 2 " + index2 + " isn't in range (0 - " + (size - 1) + ")");
        }
        if (index1>index2){
            int index = index1;
            index1 = index2;
            index2 = index;
        }
        Item<E> item1 = getItem(index1);
        Item<E> item2 = getItem(index2);
        item1.prev.next = item2.next;
        item2.next.prev = item1.prev;
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
        for (int i = 0; i <= index; i++) { //fixme Need optimisation, add 1 more "for" from size to index if index < size/2
            item = item.next;
        }
        return item;
    }

    private Item<E> getItem(E value) {
        return getItem(get(value));
    }

    public E get(int index) {
        return getItem(index).value;
    }

    public int get(E itemForGetId) {
        return check(itemForGetId);
    }

    public int size() {
        return size;
    }
}
