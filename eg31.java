import java.util.*;
class eg31psp
{
public static void main(String gg[])
{
HashMap<Integer,String> hm = new HashMap<>();
HashMap<Integer,Integer> fq= new HashMap<>();
hm.put(1,"aaa");
hm.put(2,"nksnd");
hm.put(1,"bhavesh");
for(int i : hm.keySet())
{
System.out.println("Key : " + i +" Value : "+ hm.get(i));
}
for(String s : hm.values()) System.out.println(s);

int[] x={1,23,4,2,1,4,23,1,1,4,5,6,78,9,9,9,4};
for (int i:x)
{
if(fq.containsKey(i))
{
fq.put(i,fq.get(i)+1);
}
else
{
fq.put(i,1);
}
}
for(int i:fq.keySet())
{
System.out.println("Key : " + i +" Value : "+fq.get(i));
}
}
}

