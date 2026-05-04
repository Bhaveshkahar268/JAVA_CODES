class aaa implements Runnable
{
private Thread th;
public aaa()
{
th=new Thread(this);
th.start();
}
public void run()
{
System.out.println("Default priority level is : "+th.getPriority());
th.setPriority(0);
System.out.println("Thread priority level is : "+th.getPriority());
for(int x=1;x<=99;++x)
{
System.out.print(x+" ");
}
}
}
class th1psp
{
public static void main(String gg[])
{
aaa th1=new aaa();
for(int x=200;x<=300;++x)
{
System.out.print(x+" ");
}
}
}
