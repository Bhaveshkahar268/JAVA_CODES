import java.util.*;
class Student implements Comparable<Student>
{
int id;
String name;
int marks;

public Student(int id,String name,int marks)
{
this.id = id;
this.name = name;
this.marks = marks;
}

public static void print(Student s)
{
System.out.println("id : " +s.id+" name : "+s.name+" marks : "+s.marks);
}

 
@Override
public int compareTo(Student s)
{
return Integer.compare(this.id,s.id);
}



}


class eg69psp
{
public static void main(String gg[])
{
Student s1 = new Student(101, "Rahul", 85);
Student s2 = new Student(102, "Aman", 92);
Student s3 = new Student(103, "Rohit", 75);

ArrayList<Student> list = new ArrayList<>();
list.add(s1);
list.add(s2);
list.add(s3);
Collections.sort(list);

for(Student s : list)
{
Student.print(s);
}
}
}
