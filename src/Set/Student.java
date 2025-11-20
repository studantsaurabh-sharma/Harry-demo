package Set;

import java.util.Objects;

public class Student {
    String name;
    int rollNo;

    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return rollNo == student.rollNo && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rollNo);
    }

    public String toString() {
        return " Student{ " + " name " + name + '\'' + " ,rollNo= " + rollNo + '}';

    }
}