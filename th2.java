class CommonMedium
{
private int num;
private boolean flag=false;
synchronized public void setNum(int num)
{
if(flag==true)
{
try 
{
wait();
}catch(InterruptedException ie)
{
}
}
this.num=num;
System.out.println("Produced : "+this.num);
flag=true;
notify();
}
synchronized public int getNum()
{
if(flag==false)
{
try
{
wait();
}catch(InterruptedException ie)
{
}
}
System.out.println("Consumed : "+this.num);
flag=false;
notify();
return this.num;
}
}
class Producer extends Thread
{
private CommonMedium cmn;
public Producer(CommonMedium cmn)
{
this.cmn=cmn;
this.start();
}
public void run()
{
int x;
for(x=501;x<=550;++x)
{
this.cmn.setNum(x);
}
}
}
class Consumer extends Thread
{
private CommonMedium cmn;
public Consumer(CommonMedium cmn)
{
this.cmn=cmn;
this.start();
}
public void run()
{
int e,f;
for(e=1;e<=50;++e)
{
f=this.cmn.getNum();
}
}
}
class th2psp
{
public static void main(String gg[])
{
CommonMedium cm= new CommonMedium();
Producer p=new Producer(cm);
Consumer c=new Consumer(cm);
}
}




