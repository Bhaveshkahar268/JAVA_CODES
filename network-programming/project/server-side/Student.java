class Student implements Comparable<Student>
{
private int rollNumber;
private String name;
private char gender;
private long familyIncome;
public Student()
{
this.rollNumber=0;
this.name="";
this.gender=(char)0;
this.familyIncome=0;
}
public void setRollNumber(int rollNumber)
{
this.rollNumber=rollNumber;
}
public int getrollNumber()
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
public void setGender(char gender)
{
this.gender=gender;
}
public char getGender()
{
return this.gender;
}
public void setFamilyIncome(long familyIncome)
{
this.familyIncome=familyIncome;
}
public long getFamilyIncome()
{
return this.familyIncome;
}
public boolean equals(Object object)
{
if(!(object instanceof Student)) return false;
Student student=(Student)object;
return this.rollNumber==student.rollNumber;
}
public int compareTo(Student student)
{
return this.rollNumber-student.rollNumber;
}
}
