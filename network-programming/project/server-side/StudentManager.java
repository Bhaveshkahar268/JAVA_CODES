import java.io.*;
import java.net.*;
import java.util.*;
class StudentManager
{
private String sendRequest(String requestString) throws IOException
{
Socket socket=new Socket("localhost",3030);
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
}
public void add(Student student) throws DataException
{
}
}
