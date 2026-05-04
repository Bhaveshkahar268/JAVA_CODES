import java.util.*;
class Student implements Comparable<Student>
{
private int rollNumber;
private String name;
public Student()
{
this.rollNumber=0;
this.name="";
}
public Student(int rollNumber,String name)
{
this.rollNumber=rollNumber;
this.name=name;
}
public void setRollNumber(int rollNumber)
{
this.rollNumber=rollNumber;
}
public int getRollNumber()
{
return this.rollNumber;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public boolean equals(Object obj)
{
if(!(obj instanceof Student)) return false;
Student s=(Student)obj;
System.out.println("equals got called for : "+this.rollNumber +" received : "+s.rollNumber);
return this.rollNumber==s.rollNumber;
}
public int compareTo(Student s)
{
System.out.println("compareTo got called for : "+this.rollNumber +" received : "+s.rollNumber);
return this.rollNumber-s.rollNumber;
}

public int hashCode()
{
System.out.println("HashCode is : "+this.rollNumber);
return this.rollNumber;
}
}
class setpsp
{
public static void main(String gg[])
{
Student s1=new Student(101,"Bhavesh");
Student s2=new Student(102,"Rajiv");
Student s3=new Student(103,"Rahul");
Student s4=new Student(104,"Kunal");
Student s5=new Student(105,"Karan");
Set<Student> q=new HashSet<>();
q.add(s1);
q.add(s2);
q.add(s3);
q.add(s4);
System.out.println("size of Queue is : "+q.size());
Student s6=new Student(103,"Ravi");
if(q.contains(s6))
{
System.out.println("103 exist");
}
else 
{
System.out.println("103 does not exist");
}
q.remove(s4);
System.out.println("size of Queue is : "+q.size());
System.out.println("Top element of Queue is : "+q.());
while(q.isEmpty()==false)
{
System.out.println(q.poll());
}


}
}
