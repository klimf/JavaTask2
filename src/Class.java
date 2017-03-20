public class Class {
    int intager;
    String string;
    public Class(int intager, String string) {
        this.intager = intager;
        this.string = string;
    }

    @Override
    public String toString() {
        return "Class{" +
                "intager=" + intager +
                ", string='" + string + '\'' +
                '}';
    }
}
