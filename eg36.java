class aaa
{
public aaa(int a)
{
System.out.println(a);
}
public aaa()
{
}
public void something()
{
}
public void dosomething()
{
System.out.println("dosomething of aaa : abra ka dabra");
}
}
class bbb extends aaa
{
public bbb(int a)
{
System.out.println(a);
}
public bbb()
{
}
public void dosomething()
{
System.out.println("dosomething of bbb : abra ka dabra");
}
public void something()
{
System.out.println("bbb is the best");
}
}
class eg36psp
{
public static void main(String gg[])
{
aaa x= new bbb();
x.dosomething();
x.something();
}
}

