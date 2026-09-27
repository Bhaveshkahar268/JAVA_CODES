class eg61psp
{
public static void main(String gg[])
{
int[] nums = {0,1,0,3,12,0,5};
int index=0;
for(int i=0;i<nums.length;++i)
{
if(nums[i]!=0) nums[index++]=nums[i];
}
for(int i=index;i<nums.length;++i)
{
nums[i]=0;
}
for(int i:nums)
{
System.out.print(i+" ");
}
}
}
