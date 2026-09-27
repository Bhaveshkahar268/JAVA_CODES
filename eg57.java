class eg57psp
{
public static void main(String gg[])
{
int [] arr={2,3,4,1,3,4,10,1,02,1,11,79,2,1,44,22,1,44,23};
int largest = arr[0];
for(int i :arr)
{
if(i>largest) largest=i;
}
System.out.println(largest);
}
}
