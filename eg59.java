class eg59psp
{
public static void swap(int[] arr,int left,int right)
{
int temp=arr[left];
arr[left]=arr[right];
arr[right]=temp;
}
public static void main(String gg[])
{
int [] arr={2,3,4,1,3,4,10,1,02,1,11,79,2,1,44,22,1,44,23};
int left=0;
int right=arr.length-1;
while(left<right)
{
swap(arr,left,right);
left++;
right--;
}
for(int i:arr) System.out.print(i+" ");
}
}
