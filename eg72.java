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


class eg72psp
{
public static void main(String gg[])
{
Student[] students = {
    new Student(101, "Rahul", 85),
    new Student(102, "Aman", 95),
    new Student(103, "Rohit", 85),
    new Student(104, "Vikas", 95),
    new Student(105, "Ankit", 70)
};

Comparator<Student> bymarks = (s1,s2) ->{
int result = Integer.compare(s2.marks,s1.marks);
if(result == 0)
{
int sname = s1.name.compareTo(s2.name);
if(sname == 0) return Integer.compare(s1.id,s2.id);
return sname;
}
return result;
};

Arrays.sort(students,bymarks);
for(Student s : students)
{
Student.print(s);
}

}
}

