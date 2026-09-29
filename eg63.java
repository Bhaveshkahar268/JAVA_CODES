import java.util.*;
class eg63psp
{
public static void main(String gg[])
{
int[] nums={5, -2, 3, 1, 2, -1, 4};
int k=5;
HashMap<Integer,Integer> map= new HashMap<>();
int sum=0;
int maxLength=0;
map.put(0,-1);
for(int i=0;i<nums.length;++i)
{
sum+=nums[i];
int reqSum=sum-k;

if(map.containsKey(reqSum))
{
int length = i - map.get(reqSum);
maxLength = Math.max(length,maxLength);
}


if(!map.containsKey(sum)) map.put(sum,i);

}
System.out.println(maxLength);
}
}
