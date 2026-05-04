import java.util.*;
class aaa
{
public void process(Integer i)
{
System.out.println("Integer is : "+i);
}
}
class ArrayListpsp
{
public static void main(String gg[])
{
List<Integer> list= new ArrayList<Integer>();
list.add(130);
list.add(90);
list.add(530);
list.add(30);
list.add(70);
list.add(250);
list.add(70);
System.out.println("List size is : "+list.size());
for(int i=0;i<list.size();++i)
{
System.out.println("Integer : "+ list.get(i));
}
System.out.println("-------------------------------");
list.remove(2);
System.out.println("List size is : "+list.size());
for(Integer i:list)
{
System.out.println("Integer is : "+i);
}
System.out.println("-------------------------------");
list.remove(4);
System.out.println("List size is : "+list.size());
Iterator<Integer> iter = list.iterator();
while(iter.hasNext())
{
System.out.println("Integer is : "+iter.next());
}
System.out.println("-------------------------------");
Integer iii=70;
list.remove(iii);
System.out.println("List size is : "+list.size());
aaa a=new aaa();
list.stream().forEach(a::process);
}
}
