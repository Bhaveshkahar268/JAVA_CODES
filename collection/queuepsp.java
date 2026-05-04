import java.util.*;
class queuepsp
{
public static void main(String gg[])
{

Queue<Integer> q=new LinkedList<>();
q.add(12);
q.add(211);
q.add(400);
q.add(321);
System.out.println("size of Queue is : "+q.size());
System.out.println("Top element of Queue is : "+q.element());
while(q.isEmpty()==false)
{
System.out.println(q.poll());
}


}
}
