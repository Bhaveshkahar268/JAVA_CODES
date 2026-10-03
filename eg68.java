import java.util.*;
class Student {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        Student s = (Student) obj;

        return this.id == s.id &&
               this.name.equals(s.name);
    }
    @Override
    public int hashCode() {
      return Objects.hash(id, name);
    }
}
class eg68psp
{
public static void main(String gg[])
{
HashMap<Student,String> map= new HashMap<>();

Student s1 = new Student(101, "Rahul");
Student s2 = new Student(101, "Rahul");

map.put(s1,"Computer Science");
map.put(s2,"Electrical");

System.out.println(s1.equals(s2)==true);
System.out.println(s1.hashCode());
System.out.println(s2.hashCode());


System.out.println(s1 == s2);
System.out.println(s1.equals(s2));

System.out.println(map.get(s1));
System.out.println(map.get(s2));

}
}
