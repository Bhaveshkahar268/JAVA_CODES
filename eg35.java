class eg35psp
{
public static void main(String gg[])
{
int x=10;
int y=0;
try
{
int z=x/y;
System.out.println(z);
}
catch(ArithmeticException ae)
{
ae.printStackTrace();
}

System.out.println("Thank you");
}
}
