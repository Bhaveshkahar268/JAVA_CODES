class aaa
{
private void arpit()
{
}
public void sam()
{
System.out.println("sam is called");
}
private void tom()
{
System.out.println("tom is called");
}
protected void bobby()
{
System.out.println("bobby is called");
}

}
class bbb extends aaa
{
protected void amit()
{
System.out.println("amit is called");
}
}
class ccc extends bbb
{
protected void arpit()
{
System.out.println("arpit is called");
}
}

class eg37psp
{
public static void main(String gg[])
{

aaa a= new aaa();
bbb b= new bbb();
ccc c= new ccc();
aaa x= new ccc();
a.sam();
//a.tom();
a.bobby();

b.sam();
//b.tom();
b.bobby();
b.amit();

c.sam();
//c.tom();
c.bobby();
c.amit();

x.arpit();
}
}

