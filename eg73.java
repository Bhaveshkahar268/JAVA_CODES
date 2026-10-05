import java.util.*;
class eg73psp
{
public static void main(String gg[])
{
int[] nums = {100,4,200,1,3,2};
HashSet<Integer> set = new HashSet<>();
int maxlength=0;
for(int i : nums) set.add(i);
for(int i : nums)
{
if(!set.contains(i-1))
{
int current = i;
int count = 1; 

while(set.contains(current+1))
{
current++;
count++;
}
maxlength=Math.max(maxlength,count);
}
}
System.out.println(maxlength);
}
}
