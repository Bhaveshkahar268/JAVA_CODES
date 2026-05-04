import java.io.*;
import java.net.*;

class ServerOne
{
      private ServerSocket serverSocket;
     private int portNumber;
   public  ServerOne(int portNumber)
{
  this.portNumber=portNumber;
}

public void start()
{
  try
{
    serverSocket =new ServerSocket(this.portNumber);
System.out.println("Server is ready to accept request on port :"+ this.portNumber);

Socket clientSocket;

char m;
int x;
InputStreamReader inputStreamReader;
InputStream inputStream;
OutputStream outputStream;
OutputStreamWriter outputStreamWriter;
StringBuffer sb;
String response;
String request;
char characters[];
while(true)
{
  clientSocket=serverSocket.accept();
System.out.println("Request arrived");
sb=new StringBuffer();
inputStream=clientSocket.getInputStream();
inputStreamReader=new InputStreamReader(inputStream);
while(true)
{
x=inputStreamReader.read();
m=(char)x;
if(m=='#') break;
sb.append(m);
}
request=sb.toString();
System.out.println("Request :"+request);
System.out.println("Response sent");
response="All is well#";
characters=response.toCharArray();
outputStream=clientSocket.getOutputStream();
outputStreamWriter=new OutputStreamWriter(outputStream);
outputStreamWriter.write(characters,0,characters.length);
outputStreamWriter.flush();
inputStream.close();
outputStream.close();
clientSocket.close();
}
}catch(Exception e)
{
   System.out.println(e);
}
}

public static void main(String ss[])
{
ServerOne serverOne=new ServerOne(3090);
serverOne.start();
}
}
