class eg62psp
{
public static void main(String gg[])
{
int[] nums={10,5,2,7,1,9};
int k=15;
int count=0;
int sum=0;
int maxcount=0;
int j=0;
for(int i:nums)
{
if(i<k)
{
sum+=i;
count++;
}
if(sum==k)
{
maxcount=Math.max(count,maxcount);
count--;
sum=sum-nums[j];
j++;
}
}
System.out.println(maxcount);
}
}
