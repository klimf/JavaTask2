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

    public void add(E itemToAdd) {
        if(check(itemToAdd)!=-1){
            throw new IllegalArgumentException(size + ":" + itemToAdd.toString() + " == " + (check(itemToAdd)-1) + ":" + get(check(itemToAdd)-1));
        }
        Item<E> newItem = new Item<>(itemToAdd, main.prev, main);
        newItem.prev.next = newItem;
        newItem.next.prev = newItem;
        size++;
    }

    public void add(int index, E itemToAdd) { //here it would be nice to optimise this function by combine check and insert methods (fixme)
        if(check(itemToAdd)!=-1){
            throw new IllegalArgumentException(size + ":" + itemToAdd.toString() + " == " + (check(itemToAdd)-1) + ":" + get(check(itemToAdd)-1));
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " isn't in range (0 - " + (size - 1) + ")");
        }
        Item<E> item = getItem(index);
        Item<E> newItem = new Item<>(itemToAdd, item.prev, item);
        newItem.prev.next = newItem;
        newItem.next.prev = newItem;
        size++;
    }

    private int check(E itemForCheck) {
        Item<E> item = main;
        for (int i = 0; i <= size; i++) {
            if (itemForCheck.equals(item.value)){
                return i;
            }
            item = item.next;
        }
        return -1;//is it correct?
    }

    public Item<E> getItem(int index) {
        Item<E> item = main;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " isn't in range (0 - " + (size - 1) + ")");
        }
        for (int i = 0; i <= index; i++) {
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
