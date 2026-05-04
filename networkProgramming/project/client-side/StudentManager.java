import java.io.*;
import java.util.*;
import java.net.*;

class StudentManager
{
private String sendRequest(String requestString) throws IOException
{
Socket socket=new Socket("192.168.29.127",3030);
OutputStream outputStream=socket.getOutputStream();
OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream);
char characters[]=requestString.toCharArray();
outputStreamWriter.write(characters,0,characters.length);
outputStreamWriter.flush();
InputStream inputStream=socket.getInputStream();
InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
StringBuffer stringBuffer=new StringBuffer();
char m;
int x;
while(true)
{
x=inputStreamReader.read();
m=(char)x;
if(m=='#') break;
stringBuffer.append(m);
}
String responseString=stringBuffer.toString();
outputStreamWriter.close();
inputStreamReader.close();
socket.close();
return responseString;
}
public Student getByRollNumber(int rollNumber) throws DataException
{
String requestString="student,getByRollNumber,"+rollNumber+"#";
String responseString="";
try
{
responseString=sendRequest(requestString);
}catch(IOException ioException)
{
throw new DataException("Unable to find Student");
}
String splits[]=responseString.split(",");
if(splits[0].equalsIgnoreCase("Failed"))
{
throw new DataException(splits[1]);
}
int frollNumber=Integer.parseInt(splits[1]);
String fname=splits[2];
char fgender=splits[3].charAt(0);
long fFamilyIncome=Long.parseLong(splits[4]);
Student student=new Student();
student.setRollNumber(frollNumber);
student.setName(fname);
student.setGender(fgender);
student.setFamilyIncome(fFamilyIncome);
return student;
}

public boolean rollNumberExists(int rollNumber) throws DataException
{
throw new DataException("Not yet implemented");
}

public void add(Student student) throws DataException
{
String requestString="student,add,"+student.getRollNumber()+",";
requestString=requestString+student.getName()+",";
requestString=requestString+student.getGender()+",";
requestString=requestString+student.getFamilyIncome()+"#";
String responseString="";
try
{
responseString=sendRequest(requestString);
System.out.println(responseString);
}catch(IOException ioException)
{
throw new DataException("Unable to add");
}
String splits[]=responseString.split(",");
if(splits[0].equalsIgnoreCase("Failed"))
{
throw new DataException(splits[1]);
}
}

public void update(Student student) throws DataException
{
String requestString="student,update,"+student.getRollNumber()+",";
requestString=requestString+student.getName()+",";
requestString=requestString+student.getGender()+",";
requestString=requestString+student.getFamilyIncome()+"#";
String responseString="";
try
{
responseString=sendRequest(requestString);
}catch(IOException ioException)
{
throw new DataException("Unable to update");
}
String splits[]=responseString.split(",");
if(splits[0].equalsIgnoreCase("Failed"))
{
throw new DataException(splits[1]);
}
}

public void delete(int rollNumber) throws DataException
{
String requestString="student,delete,"+rollNumber+"#";
String responseString="";
try
{
responseString=sendRequest(requestString);
}catch(IOException ioException)
{
throw new DataException("Student not deleted!!!  ");
}
String splits[]=responseString.split(",");
if(splits[0].equalsIgnoreCase("Failed"))
{
throw new DataException(splits[1]);
}
}

public List<Student> getAll() throws DataException
{
try
{
String requestString="student,getAll#";
String responseString=sendRequest(requestString);
String splits[]=responseString.split(",");
if(splits[0].equals("FAILED")) throw new DataException(splits[1]);
int i;
int rollNumber;
char gender;
String name;
long familyIncome;
List<Student> students=new ArrayList<Student>();
Student student;
for(i=1;i<splits.length;i=i+4)
{
rollNumber=Integer.parseInt(splits[i]);
name=splits[i+1];
gender=splits[i+2].charAt(0);
familyIncome=Long.parseLong(splits[i+3]);
student=new Student();
student.setRollNumber(rollNumber);
student.setName(name);
student.setGender(gender);
student.setFamilyIncome(familyIncome);
students.add(student);
}
return students;
}catch(IOException ioException)
{
throw new DataException("Cannot get list of students");
}
}


}
