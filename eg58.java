import java.util.*;
class eg58psp
{
public static void main(String gg[])
{
int [] arr={2,3,2,79,4,80,80,90,91,100,1,3,4,100,1,02,91,11,79,2,1,44,22,1,44,23};
int largest = Integer.MIN_VALUE;
int second_largest=Integer.MIN_VALUE;
for(int i :arr)
{
if(i>largest)
{
second_largest=largest;
largest=i;
}
else if(i>second_largest && i<largest) second_largest=i;
}
System.out.println(largest + "   "+ second_largest);
}
}
