import java.util.*;
class eg65psp
{
public static void main(String gg[])
{
String s="abcabcbb";
HashMap<Character,Integer> map= new HashMap<>();
int left=0;
int maxlength=0;
int start=0;
String ans="";
for(int right=0;right<s.length();++right)
{
char ch = s.charAt(right);
if(map.containsKey(ch) && map.get(ch)>=left)
{
left=map.get(ch)+1;
}
map.put(ch,right);
int length=right-left+1;
if(length>maxlength)
{
maxlength=length;
start=left;
}
}
ans=s.substring(start,start+maxlength);
System.out.println("Length : " + maxlength);
System.out.println("String : " + ans);
}
}
