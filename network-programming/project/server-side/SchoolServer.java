import java.io.*;
import java.net.*;
class SchoolServer
{
private ServerSocket serverSocket;
private int portNumber;
public SchoolServer(int portNumber) throws IOException
{
this.portNumber=portNumber;
this.serverSocket=new ServerSocket(this.portNumber);
}
public void start()
{
RequestProcessor requestProcessor;
Socket socket;
while(true)
{
try
{
socket=this.serverSocket.accept();
requestProcessor=new RequestProcessor(socket);
}catch(Exception exception)
{
System.out.println(exception);
}
}
}
public static void main(String gg[])
{
if(gg.length!=1)
{
System.out.println("Pass port number as command line argument\n");
return;
}
try
{
int portNumber=Integer.parseInt(gg[0]);
SchoolServer schoolServer=new SchoolServer(portNumber);
System.out.println("Server running on port : "+portNumber);
schoolServer.start();
}catch(Exception exception)
{
System.out.println(exception);
}
}
}
