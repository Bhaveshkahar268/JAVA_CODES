import java.util.*;

class aaa
{
private int x;
public aaa(int x)
{
this.x=x;
}

public boolean equals(Object o)
{
if(this == o) return true;
if(!(o instanceof aaa)) return false;
aaa a=(aaa) o;
return x==a.x;
}
public int hashCode()
{
return Objects.hash(x);
}
}
class eg38psp
{
public static void main(String gg[])
{
Set<aaa> set= new HashSet<>();
aaa a= new aaa(10);
set.add(a);
set.add(new aaa(10));
set.add(new aaa(10));

System.out.println(set);
}
}
