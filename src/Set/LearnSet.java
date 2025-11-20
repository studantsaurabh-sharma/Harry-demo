package Set;
import java.util.Set;
import java.util.HashSet;
public class LearnSet {
    public static void main(String args[]) {
       Set<Student> S = new HashSet<>();
//        S.add(new Student("Anuj", 2));
//        S.add(new Student("Ramesh", 4));
        S.add(new Student("Harry", 54));
        S.add(new Student("Rohit", 98));
        S.add(new Student("Harry", 54));
        System.out.println(S);

    }

}
