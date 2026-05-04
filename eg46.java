class AAA
{
protected void sam()
{
System.out.println("AAA sam");

}

}
class BBB extends AAA
{
public void tom()
{
System.out.println("BBB tom");
}
}
class eg46psp
{
public static void main(String gg[])
{
AAA a= new BBB();
a.sam();
}
}
