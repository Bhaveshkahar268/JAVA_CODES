import java.io.*;
import java.net.*;

class ClientOne
{
public static void main(String gg[])
{
   try
{
   Socket clientSocket;
clientSocket=new Socket("localhost",3090);

String requestString="Hello Server, is everything ok?#";
char characters[]=requestString.toCharArray();
OutputStream outputStream=clientSocket.getOutputStream();
OutputStreamWriter outputStreamWriter;
outputStreamWriter=new OutputStreamWriter(outputStream);
outputStreamWriter.write(characters,0,characters.length);
outputStreamWriter.flush();
System.out.println("Request sent");

//code receive response
InputStream inputStream=clientSocket.getInputStream();
InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
StringBuffer sb=new StringBuffer();
char m;
int x;
while(true)
{
x=inputStreamReader.read();
m=(char)x;
if(m=='#') break;
sb.append(m);
}
String response=sb.toString();
System.out.println("Respone received");
System.out.println("Response:"+response);
inputStream.close();
outputStream.close();
clientSocket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
}