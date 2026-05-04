import java.io.*;
import java.util.*;

class StudentManager
{     private static   String dataFileName="Student.data"; 
      public  Student getByRollNumber(int rollNumber) throws DataException
{
if(rollNumber<=0) throw new DataException(rollNumber+ "does not exist.");

try
{
  Student student;
 File file=new File(dataFileName);
if(file.exists()==false) throw new DataException(rollNumber+ "does not exists.");

RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
   randomAccessFile.close();
throw new DataException(rollNumber+"does not exists");
}
int fRollNumber;
String fName;
char fGender;
long fFamilyIncome;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fRollNumber=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fGender=randomAccessFile.readLine().charAt(0);
fFamilyIncome=Long.parseLong(randomAccessFile.readLine());
if(fRollNumber==rollNumber)
{
   student=new Student();
   student.setRollNumber(fRollNumber);
  student.setName(fName);
  student.setGender(fGender);
  student.setFamilyIncome(fFamilyIncome);
 randomAccessFile.close();
return student;
}
}
randomAccessFile.close();
throw new DataException(rollNumber+"does not exists");
}catch(IOException ioException)
{
throw new DataException(ioException.getMessage());
}
}

public  boolean rollNumberExists(int rollNumber)
{
  if(rollNumber<=0)  return false;
try
{
 File file=new File(dataFileName);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");

if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
int fRollNumber;
String fName;
char fGender;
Long fFamilyIncome;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fRollNumber=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fGender=randomAccessFile.readLine().charAt(0);
fFamilyIncome=Long.parseLong(randomAccessFile.readLine());
if(fRollNumber==rollNumber)
{
  randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;

}catch(IOException ioExcepton)
{
  return false;
}
}

public void add(Student student)  throws DataException
{
  int rollNumber=student.getRollNumber();
if(rollNumber<0) throw new DataException(rollNumber+"does not exists");

String name=student.getName();
char gender=student.getGender();
Long familyIncome=student.getFamilyIncome();
if(this.rollNumberExists(rollNumber) )throw new DataException(rollNumber+"exists");

try
{
  File file=new File(dataFileName);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
randomAccessFile.seek(randomAccessFile.length());

randomAccessFile.writeBytes(rollNumber+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(familyIncome+"\n");
randomAccessFile.close();
}catch(IOException ioException)
{
   throw new DataException(ioException.getMessage());
}
}


public List<Student> getAll() throws DataException
{
      List<Student> students;
students=new  ArrayList<Student>();

try
{
   Student student;
File file=new File(dataFileName);
if(file.exists()==false)  return students;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");

if(randomAccessFile.length()==0)
{
 randomAccessFile.close();
return students;
}
int fRollNumber;
String fName;
char fGender;
long fFamilyIncome;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
   fRollNumber=Integer.parseInt(randomAccessFile.readLine());
  fName=randomAccessFile.readLine();
fGender=randomAccessFile.readLine().charAt(0);
fFamilyIncome=Long.parseLong(randomAccessFile.readLine());
student=new Student();
student.setRollNumber(fRollNumber);
student.setName(fName);
student.setGender(fGender);
student.setFamilyIncome(fFamilyIncome);
students.add(student);
}
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DataException(ioException.getMessage());
}
return students;
}

public void update(Student student) throws DataException
{
int rollNumber=student.getRollNumber();
if(rollNumber<=0) throw new DataException("Invalid roll number: "+rollNumber);
String name=student.getName();
char gender=student.getGender();
long familyIncome=student.getFamilyIncome();
if(this.rollNumberExists(rollNumber)==false) throw new DataException(rollNumber+"does not exists");
try
{
File fhaltuFile=new File("fhaltu.tmp");
if(fhaltuFile.exists())  fhaltuFile.delete();
File file=new File(dataFileName);
RandomAccessFile fhaltuRandomAccessFile=new RandomAccessFile(fhaltuFile,"rw");
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");

int fRollNumber;
String fName;
char fGender;
Long fFamilyIncome;

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fRollNumber=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fGender=randomAccessFile.readLine().charAt(0);
fFamilyIncome=Long.parseLong(randomAccessFile.readLine());
if(rollNumber!=fRollNumber)
{
fhaltuRandomAccessFile.writeBytes(fRollNumber+"\n");
fhaltuRandomAccessFile.writeBytes(fName+"\n");
fhaltuRandomAccessFile.writeBytes(fGender+"\n");
fhaltuRandomAccessFile.writeBytes(fFamilyIncome+"\n");
}
else
{
fhaltuRandomAccessFile.writeBytes(rollNumber+"\n");
fhaltuRandomAccessFile.writeBytes(name+"\n");
fhaltuRandomAccessFile.writeBytes(gender+"\n");
fhaltuRandomAccessFile.writeBytes(familyIncome+"\n");
}
}
randomAccessFile.seek(0);
fhaltuRandomAccessFile.seek(0);
while(fhaltuRandomAccessFile.getFilePointer()<fhaltuRandomAccessFile.length())
{
randomAccessFile.writeBytes(fhaltuRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(fhaltuRandomAccessFile.length());
fhaltuRandomAccessFile.setLength(0);
fhaltuRandomAccessFile.close();
randomAccessFile.close();
}catch(IOException ioException)
{
    throw new DataException(ioException.getMessage());
}
}

public void delete(int rollNumber) throws DataException
{
   if(rollNumber<=0) throw new DataException("Invalid roll number: "+rollNumber);
if(this.rollNumberExists(rollNumber)==false) throw new DataException(rollNumber+"does not exists");
try
{
    File fhaltuFile=new File("fhaltu.tmp");
if(fhaltuFile.exists())  fhaltuFile.delete();
File file=new File(dataFileName);
RandomAccessFile fhaltuRandomAccessFile=new RandomAccessFile(fhaltuFile,"rw");
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");

int fRollNumber;
String fName;
char fGender;
Long fFamilyIncome;

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
   fRollNumber=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fGender=randomAccessFile.readLine().charAt(0);
fFamilyIncome=Long.parseLong(randomAccessFile.readLine());
if(rollNumber!=fRollNumber)
{
fhaltuRandomAccessFile.writeBytes(fRollNumber+"\n");
fhaltuRandomAccessFile.writeBytes(fName+"\n");
fhaltuRandomAccessFile.writeBytes(fGender+"\n");
fhaltuRandomAccessFile.writeBytes(fFamilyIncome+"\n");
}
}
randomAccessFile.seek(0);
fhaltuRandomAccessFile.seek(0);
while(fhaltuRandomAccessFile.getFilePointer()<fhaltuRandomAccessFile.length())
{
randomAccessFile.writeBytes(fhaltuRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(fhaltuRandomAccessFile.length());
fhaltuRandomAccessFile.setLength(0);
fhaltuRandomAccessFile.close();
randomAccessFile.close();
}catch(IOException ioException)
{
    throw new DataException(ioException.getMessage());
}
}

}

