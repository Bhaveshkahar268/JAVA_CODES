import java.util.*;
class eg60psp
{
public static int kthLargestElement(int[] arr,int k)
{
PriorityQueue<Integer> pq = new PriorityQueue<>();
for(int i:arr)
{
pq.add(i);
if(pq.size()>k) pq.poll();
}
return pq.peek();
}
public static void main(String gg[])
{
int [] arr={2,3,4,1,3,4,10,1,02,1,11,79,2,1,44,22,1,44,23};
int ans= kthLargestElement(arr,4);
System.out.println(ans);
}
}
