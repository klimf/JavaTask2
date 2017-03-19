public class Main {
    public static void main(String[] args) {
        LinkedSet<String> set = new LinkedSet<>();
        set.add("1");
        set.add("2");
        set.add("3");

        for (int i = 0; i < set.size(); i++){
            System.out.println(set.get(i));
        }
    }
}
