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
class mappsp
{
public static void main(String gg[])
{
Student s1=new Student(101,"Bhavesh");
Student s2=new Student(102,"Rajiv");
Student s3=new Student(103,"Rahul");
Student s4=new Student(104,"Kunal");
Student s5=new Student(105,"Karan");
Map<Integer,Student> m=new HashMap<>();
m.put(s1.getRollNumber(),s1);
m.put(s2.getRollNumber(),s2);
m.put(s3.getRollNumber(),s3);
m.put(s4.getRollNumber(),s4);
System.out.println("size of Map is : "+m.size());
Student s6=new Student(103,"Ravi");
System.out.println(m.containsKey(101));
m.remove(104);
System.out.println("size of Map is : "+m.size());
for(Integer i:m.keySet())
{
Student s = m.get(i);
System.out.println(s.getRollNumber()+","+s.getName());
}
}
}
