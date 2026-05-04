import java.io.*;
import java.net.*;
class RequestProcessor extends Thread
{
private Socket socket;
public RequestProcessor(Socket socket)
{
this.socket=socket;
this.start();
}
public void run()
{
try
{
//extract request
StringBuffer stringBuffer;
InputStream inputStream;
InputStreamReader inputStreamReader;
OutputStream outputStream;
OutputStreamWriter outputStreamWriter;
String splits[];
int x;
char m;
String responseString="";
String requestString;
char characters[];
String entityName;
String operation;
stringBuffer=new StringBuffer();
inputStream=this.socket.getInputStream();
inputStreamReader=new InputStreamReader(inputStream);
while(true)
{
x=inputStream.read();
m=(char)x;
if(m=='#') break;
stringBuffer.append(m);
}
requestString=stringBuffer.toString();
splits=requestString.split(",");
entityName=splits[0];
operation=splits[1];
if(entityName.equalsIgnoreCase("student"))
{
if(operation.equalsIgnoreCase("add"))
{
int rollNumber=Integer.parseInt(splits[2]);
String name=splits[3];
char gender=splits[4].charAt(0);
long familyIncome=Long.parseLong(splits[5]);
Student student=new Student();
student.setRollNumber(rollNumber);
student.setName(name);
student.setGender(gender);
student.setFamilyIncome(familyIncome);
StudentManager studentManager=new StudentManager();
try
{
studentManager.add(student);
responseString="OK#";
}catch(DataException dataException)
{
responseString="FAILED,"+dataException.getMessage()+"#";
}
}//operation add part ends here
//more if blocks for other operations
if(operation.equalsIgnoreCase("getByRollNumber"))
{
int rollNumber=Integer.parseInt(splits[2]);
StudentManager studentManager=new StudentManager();
Student student;
try
{
student=studentManager.getByRollNumber(rollNumber);
responseString="OK#";
responseString=responseString+student.getRollNumber()+",";
responseString=responseString+student.getName()+",";
responseString=responseString+student.getGender()+",";
responseString=responseString+student.getFamilyIncome()+"#";
}catch(DataException dataException)
{
responseString="FAILED,"+dataException.getMessage()+"#";
}
}//getByRollNumber ends here
if(operation.equalsIgnoreCase("Update"))
{
}
if(operation.equalsIgnoreCase("getAll"))
{
try
{
responseString="OK#";
List<Student> students=new StudentManager().getAll();
for(Student student:students)
{
responseString=responseString+student.getRollNumber()+",";
responseString=responseString+student.getName()+",";
responseString=responseString+student.getGender()+",";
responseString=responseString+student.getFamilyIncome()+",";
}
responseString=responseString.subString(0,responseString.length()-1);
responseString=responseString+"#";
}catch(DataException dataException)
{
responseString="FAILED,"+dataException.getMessage()+"#";
}
}//getAll ends here
}
//send response
characters=responseString.toCharArray();
outputStream=socket.getOutputStream();
outputStreamWriter=new OutputStreamWriter(outputStream);
outputStreamWriter.write(characters,0,characters.length);
outputStreamWriter.flush();

//close connection
socket.close();
}catch(Exception exception)
{
System.out.println(exception);
}
}
}
