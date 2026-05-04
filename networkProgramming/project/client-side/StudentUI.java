import java.util.*;

class StudentUI
{   
 public void add()
{      StudentManager studentManager =new StudentManager();
       Student student;
       System.out.println("Student(Add Module)");
       int rollNumber;
      String name;
      char gender;
      long familyIncome;
      rollNumber=Keyboard.readInt("Enter roll number: ");
     if(rollNumber==0)
{   
System.out.println("Invalid roll number");
return;
}

try
{
     student=studentManager.getByRollNumber(rollNumber);
System.out.println("That roll number alloted to"+student.getName());
return;
}catch(DataException dataException)
{
}

name=Keyboard.readString("Enter name: ");
name=name.trim();
if(name.length()==0)
{
  System.out.println("Name required");
return;
}

gender=Keyboard.readChar("Enter gender(M/F): ");
if(gender!='m' &&  gender!='M'  &&   gender!='F'  &&  gender!='f')
{
System.out.println("Invalid gender");
return;
}

familyIncome=Keyboard.readLong("Enter family income: ");
if(familyIncome<0)
{
   System.out.println("Invalid family income");
return;
}

char yesNo;
yesNo=Keyboard.readChar("Add(Y/N): ");
if (yesNo=='y' || yesNo=='Y')
{
     student=new Student();
student.setRollNumber(rollNumber);
student.setName(name);
student.setGender(gender);
student.setFamilyIncome(familyIncome);

try
{
   studentManager.add(student);
}catch(DataException dataException)
{
  System.out.println(dataException.getMessage());
  System.out.println("Student not added");
}
}
else
{
System.out.println("Student not added");
}
}

public void edit()
{
System.out.println("Student(Edit Module)");
int rollNumber;
String name;
char gender;
long familyIncome;
Student student=null;
rollNumber=Keyboard.readInt("Enter roll number: ");
if (rollNumber<0)
{
System.out.println("Invalid input");
return;
}
StudentManager studentManager=new StudentManager();
try
{
student=studentManager.getByRollNumber(rollNumber);
}catch(DataException dataException)
{
System.out.println(dataException.getMessage());
}
System.out.println("Name: "+student.getName());
System.out.println("Gender: "+student.getGender());
System.out.println("Family income: "+student.getFamilyIncome());
char yesNo;
yesNo=Keyboard.readChar("Edit(Y/N) : ");
if(yesNo!='Y' && yesNo!='y')
{
System.out.println("Student not edited");
return;
}
name=Keyboard.readString("Enter name: ");
name=name.trim();
if(name.length()==0)
{
  System.out.println("Name required");
return;
}

gender=Keyboard.readChar("Enter gender(M/F): ");
if(gender!='m' &&  gender!='M'  &&   gender!='F'  &&  gender!='f')
{
System.out.println("Invalid gender");
return;
}

familyIncome=Keyboard.readLong("Enter family income: ");
if(familyIncome<0)
{
   System.out.println("Invalid family income");
return;
}

char YesNo;
YesNo=Keyboard.readChar("Edit(Y/N): ");
if (YesNo=='y' || YesNo=='Y')
{
     student=new Student();
student.setRollNumber(rollNumber);
student.setName(name);
student.setGender(gender);
student.setFamilyIncome(familyIncome);

try
{
   studentManager.update(student);
System.out.println("Student updated");
}catch(DataException dataException)
{
  System.out.println(dataException.getMessage());
  System.out.println("Student not updated");
}
}
else
{
System.out.println("Student not updated");
}


}

public void delete()
{
System.out.println("Student(Delete Module)");
int rollNumber;
String name;
char gender;
long familyIncome;
Student student=null;
rollNumber=Keyboard.readInt("Enter roll number: ");
if (rollNumber<0)
{
System.out.println("Invalid input");
return;
}
StudentManager studentManager=new StudentManager();
try
{
student=studentManager.getByRollNumber(rollNumber);
}catch(DataException dataException)
{
System.out.println(dataException.getMessage());
}
System.out.println("Name: "+student.getName());
System.out.println("Gender: "+student.getGender());
System.out.println("Family income: "+student.getFamilyIncome());
char yesNo;
yesNo=Keyboard.readChar("Delete(Y/N) : ");
if(yesNo!='Y' && yesNo!='y')
{
System.out.println("Student not deleted");
return;
}


try
{
   studentManager.delete(rollNumber);
System.out.println("Student deleted");
}catch(DataException dataException)
{
  System.out.println(dataException.getMessage());
  System.out.println("Student not deleted");
}



}

public void find()
{   
int rollNumber;
System.out.println("Student(View Module)");
rollNumber=Keyboard.readInt("Enter roll number : ");
StudentManager studentManager=new StudentManager();
try
{
 Student student=studentManager.getByRollNumber(rollNumber);
System.out.println("Name:"+student.getName());
System.out.println("Gender:"+student.getGender());
System.out.println("FamilyIncome:"+student.getFamilyIncome());
}catch(DataException  dataException)
{
System.out.println(dataException.getMessage());
}
}

public void printAll()
{
try
{
System.out.println("Student(View Module)");
StudentManager studentManager=new StudentManager();
List<Student> students=studentManager.getAll();
if(students.size()==0)
{
  System.out.println("No Students");
   return;
}
int i;
Student student;
for(i=0;i<students.size();i++)
{
student=students.get(i);
System.out.println("Roll number : "+student.getRollNumber());
System.out.println("Name : "+student.getName());
System.out.println("Gender : "+student.getGender());
System.out.println("Family Income : "+student.getFamilyIncome());
}
   }catch(DataException dataException)
{
System.out.println(dataException.getMessage());
}
}
}

class  StudentApplication
{
public static void main(String gg[])
{   StudentUI   studentUI =new StudentUI();
     int choice;
     while(true)
{    System.out.println("Options");
     System.out.println("-------------");
   System.out.println("1.Add student");
System.out.println("2.Edit student");
System.out.println("3.Delete student");
System.out.println("4.Find student");
System.out.println("5.Print Details");
System.out.println("6.Exit");
choice=Keyboard.readInt("Enter choice: ");
if(choice==1)   studentUI.add();
else if(choice==2) studentUI.edit();
else if(choice==3) studentUI.delete();
else if(choice==4) studentUI.find();
else if(choice==5) studentUI.printAll();
else if(choice==6) break;
else
{
 System.out.println("Invalid choice");
}
} 
}
}
