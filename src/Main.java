public class Main {
    public static void main(String[] args) {
        LinkedSet<String> set = new LinkedSet<>();
        String s1 = "exception";
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");
        set.add("6", "7", "8");

        set.addFirst("11");
        set.add(3, "99", "100", "101");
        for (int i = 0; i < set.size(); i++){
            System.out.print(set.get(i) + " ");
        }

    }
}
