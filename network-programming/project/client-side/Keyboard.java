import java.io.*;
public class Keyboard
{
private Keyboard()
{
}
public static String readString()
{
try
{
String str;
BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
return bufferedReader.readLine();
}catch(IOException ioException)
{
return "";
}
}
public static String readString(String prompt)
{
System.out.print(prompt);
return readString();
}
public static long readLong()
{
return Long.parseLong(readString());
}
public static long readLong(String prompt)
{
System.out.print(prompt);
return readLong();
}
public static int readInt()
{
return Integer.parseInt(readString());
}
public static int readInt(String prompt)
{
System.out.print(prompt);
return readInt();
}
public static short readShort()
{
return Short.parseShort(readString());
}
public static short readShort(String prompt)
{
System.out.print(prompt);
return readShort();
}
public static byte readByte()
{
return Byte.parseByte(readString());
}
public static byte readByte(String prompt)
{
System.out.print(prompt);
return readByte();
}
public static double readDouble()
{
return Double.parseDouble(readString());
}
public static double readDouble(String prompt)
{
System.out.print(prompt);
return readDouble();
}
public static float readFloat()
{
return Float.parseFloat(readString());
}
public static float readFloat(String prompt)
{
System.out.print(prompt);
return readFloat();
}
public static char readChar()
{
return readString().charAt(0);
}
public static char readChar(String prompt)
{
System.out.print(prompt);
return readChar();
}
public static boolean readBoolean()
{
return Boolean.parseBoolean(readString());
}
public static boolean readBoolean(String prompt)
{
System.out.print(prompt);
return readBoolean();
}
}
