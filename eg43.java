Class eg43psp
{
public static void main(String gg[])
{
int x,y,z;
try
{
x=Integer.parseInt(gg[0]);
y=Integer.parseInt(gg[1]);
z=x/y;
System.out.println(z);
}
catch(NumberFormatException nfe)
{
System.out.println(nfe);
}
catch(ArrayIndexOutOfBoundsException aiobe)
{
System.out.println(aiobe);
}
catch(ArithmeticException ae)
{
System.out.println(ae);
}
System.out.println("Thank you ");

System.out.println("Thank you ");


}
}
