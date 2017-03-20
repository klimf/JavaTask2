public class Main {
    public static void main(String[] args) {
        LinkedSet<String> set1 = new LinkedSet<>();
        LinkedSet<String> set2 = new LinkedSet<>("999", "1999", "2999");
        String s1 = "exception";
        set1.add("1");
        set1.add("2");
        set1.add("3");
        set1.add("4");
        set1.add("5");
        set1.add("6", "7", "8");

        set1.addFirst("0");
        set1.add(3, "99", "100", "101");
        set1.add(8, set2);


        for (int i = 0; i < set1.size(); i++){
            System.out.print(set1.get(i) + " ");
        }

        Class newClass = new Class(0, "zero");
        LinkedSet<Class> classSet = new LinkedSet<>(
                newClass,
                new Class(1, "one"),
                new Class(2, "two"),
                new Class(3, "three"),
                new Class(4, "four")
        );

        classSet.add(new Class(0, "zero"));
        //classSet.add(newClass); exception

        for (int i = 0; i < classSet.size(); i++){
            System.out.println(classSet.get(i).toString());
        }

    }
}
