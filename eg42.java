class eg42psp
{
public static void swap(int[] arr,int  a,int b)
{
int temp=a;
arr[a]=arr[b];
arr[b]=temp;
System.out.println("Done");
}
public static void main(String gg[])
{
int[] arr={1,2,3,4,5};
int e=0;
int f=arr.length-1;
while(e<f)
{
eg42psp.swap(arr,e,f);
e++;
f--;
}
for(int i :arr)
{
System.out.println(i+ " "); 
}

}
}
