class aaa
{
int x;
aaa()
{
this.x=99;
}
aaa(int x)
{
this.x=x;
}

void print()
{
System.out.println(this.x);
}
}
class bbb extends aaa
{
bbb(int x)
{
this.x=x;
}
void print()
{ 
System.out.println(this.x);
}
}
class eg39psp
{
public static void main(String gg[])
{
aaa a =new aaa(10);
bbb b= new bbb(15);
aaa x= new bbb(190);
a.print();
b.print();
x.print();
}
}
