public class LinkedSet<E> {
    private int size;
    private Item main;

    public LinkedSet() {
        main = new Item<E>(null, null, null);
        main.prev = main;
        main.next = main;
    }

    private class Item<T> {
        public T item;
        public Item<T> prev;
        public Item<T> next;

        public Item(T item, Item<T> prev, Item<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }

    public void add(E itemToAdd) {
        /*if(!check(itemToAdd)){
            throw new IllegalArgumentException();
        }*/
        /*System.out.println(String.format(
                "itemToAdd = %s; \nmain = %s; \n",
                itemToAdd.toString(),
                main.toString()

        ));*/
        Item<E> newItem = new Item<>(itemToAdd, main, main);
        newItem.prev.next = newItem;
        newItem.next.prev = newItem;
        size++;
    }

    private boolean check(E itemForCheck) {
        Item<E> item = main;
        for (int i = 0; i < size; i++) {
            if (item.equals(itemForCheck)){
                return false;
            }
            item = item.next;
        }
        return true;
    }

    public E get(int index) {
        Item<E> item = main;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " isn't in range (0 - " + size + ")");
        }
        for (int i = 0; i < index; i++) {
            item = item.next;
        }
        return item.item;
    }

    public int size() {
        return size;
    }
}
