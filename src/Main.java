public class Main {
    public static void main(String[] args) {
        LinkedSet<String> set = new LinkedSet<>();
        String s1 = "exception";
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");
        set.add("6");

        set.add(3, "99");
        for (int i = 0; i < set.size(); i++){
            System.out.println(set.get(i));
        }
    }
}
