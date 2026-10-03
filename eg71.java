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


class eg71psp
{
public static void main(String gg[])
{
Student s1 = new Student(101, "Rahul", 85);
Student s2 = new Student(102, "Aman", 95);
Student s3 = new Student(103, "Rohit", 85);
Student s4 = new Student(104, "Vikas", 95);

ArrayList<Student> list = new ArrayList<>();
list.add(s1);
list.add(s2);
list.add(s3);
list.add(s4);

System.out.println("Default sort by id using comparable");
Collections.sort(list);
for(Student s : list)
{
Student.print(s);
}

Comparator<Student> byid =(student1,student2) -> Integer.compare(student1.id,student2.id);
Comparator<Student> byname = (student1,student2) -> student1.name.compareTo(student2.name);
Comparator<Student> bymarks = (student1,student2) -> { 
int result = Integer.compare(student2.marks,student1.marks);
if(result==0) return student1.name.compareTo(student2.name);
return result;
};
System.out.println("Now Sorting using Different Comparator");
System.out.println("Sort by Id");
Collections.sort(list,byid);
for(Student s : list)
{
Student.print(s);
}

System.out.println("Sort by Name");
Collections.sort(list,byname);
for(Student s : list)
{
Student.print(s);
}

System.out.println("Sort by Marks");
Collections.sort(list,bymarks);
for(Student s : list)
{
Student.print(s);
}

}
}
