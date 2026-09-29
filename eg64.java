import java.util.*;
class eg64psp
{
public static void main(String gg[])
{
int[] nums = {0, 1, 1, 0, 1, 1, 1, 0};
HashMap<Integer,Integer> map = new HashMap<>();
map.put(0,-1);
int sum=0;
int length=0;
int maxLength=0;
for(int i=0;i<nums.length;++i)
{
if(nums[i]==0) sum+=-1;
else sum+=1;

if(map.containsKey(sum))
{
int index=map.get(sum);
length=i-index;
maxLength=Math.max(maxLength,length);
}
else map.put(sum,i);
}
System.out.println(maxLength);
}
}
