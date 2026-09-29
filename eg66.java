class eg66psp
{
public static void main(String gg[])
{
int[] nums = {2,3,1,2,4,3};
int k=7;
int left=0;
int right=0;
int sum=0;
int start=0;
int minlength=Integer.MAX_VALUE;
while(right<nums.length)
{
sum+=nums[right];
while(sum>=k)
{
int length=right-left+1;
if(length<minlength)
{
minlength=length;
start=left;
}
sum-=nums[left];
left++;
}
right++;
}
System.out.println(minlength);
for(int i=start;i<start+minlength;++i)
{
System.out.print(nums[i]+" ");
}
}
}

