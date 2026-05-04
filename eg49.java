import java.util.*;
class eg49psp
{
public static void main(String gg[])
{
List<Integer> ls= new ArrayList<Integer>();
ls.add(5);
ls.add(5);
ls.add(7);
ls.add(3);
ls.add(2);
ls.add(3);
ls.add(4);
ls.add(1);
ls.add(3);
ls.add(4);
System.out.println(ls);
Collections.sort(ls);//,(a,b)->
/*{
if(a<b) return a;
else retun b;
});*/
System.out.println(ls);
}
}
