class aaa
{
public void sam()
{
System.out.println("Great");
}
}
class bbb extends aaa
{
public void tom()
{
System.out.println("bbb");
}

}
class eg34psp
{
public static void main(String gg[])
{
aaa a;
a=new aaa();
a.sam();
a=new bbb();
a.sam();
a.tom();
}
}
