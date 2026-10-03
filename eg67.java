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
}
class eg67psp
{
public static void main(String gg[])
{
Student s1 = new Student(101, "Rahul");
Student s2 = new Student(101, "Rahul");

System.out.println(s1 == s2);
System.out.println(s1.equals(s2));
}
}
