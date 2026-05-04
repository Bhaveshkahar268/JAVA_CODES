import java.util.*;
class eg47psp
{
public static void main(String gg[])
{
Scanner sc= new Scanner(System.in);
int hr=sc.nextInt();
int result=0;
int rem=0;
result = (result+20);
if(hr>5)
{
rem= hr-5;
result= 350+(rem*20);
}
else if(hr>2 && hr<=5)
{
rem=hr-2;
result=200+(rem*50);
}
else if(hr>0 && hr<=2)
{
result=hr*100;
}
System.out.println(result);
}
}
