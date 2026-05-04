import java.util.*;

class eg48psp
{
public static int search(Integer []arr,int num)
{
int i=0;
for(i=0;i<arr.length;++i)
{
if(arr[i]==num) return i;
}
return -1;
}


public static void sort(Integer[] temp,HashMap<Integer,Integer> hm)
{

Arrays.sort(temp,(a,b) -> {
int freqA = hm.get(a);
int freqB = hm.get(b);

if(freqA != freqB)
{
return freqB - freqA;
}
else
{
return b - a;
}
});

}

public static void main(String gg[])
{
Scanner sc= new Scanner(System.in);
HashMap<Integer,Integer> hm= new HashMap<>();
int n = sc.nextInt();
int []arr= new int[n];

for(int i=0;i<n;++i)
{
arr[i]= sc.nextInt();
hm.put(arr[i],hm.getOrDefault(arr[i],0)+1);
}
Integer[] temp = new Integer[n];
for (int i = 0; i < n; i++) temp[i] = arr[i];
for(int i:arr) System.out.print(i+" ");
System.out.println();
for(int i:hm.keySet()) System.out.println(i+" :  "+hm.get(i));
sort(temp,hm);
System.out.println();
for(int i:temp) System.out.print(i+" ");
System.out.println();
int num= sc.nextInt();
int ans=search(temp,num);
System.out.println(ans);
}
}










