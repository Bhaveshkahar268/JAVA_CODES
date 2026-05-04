import java.sql.*;

class eg44psp
{
public static void main(String gg[])
{
try 
{
Class.forName("com.mysql.cj.jdbc.Driver");
System.out.println("Driveer");
Connection con;
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/HR_DB","root","Bhavesh@2004");
System.out.println("connection ho gaya ");
Statement s= con.createStatement();
ResultSet rs;
int Scode;
String Sname;
System.out.println("adbashjdbahsbdjabsdjkbaskjdbaskdas");
rs=s.executeQuery("select * from city");

while(rs.next())
{
Scode=rs.getInt("code");
Sname=rs.getString("name").trim();

System.out.println(Scode + " : " + Sname);

}
rs.close();
s.close();
con.close(); 
}
catch(ClassNotFoundException cnfe)
{
System.out.println(cnfe.getMessage());
}
catch(SQLException sqle)
{
System.out.println(sqle.getMessage());
}
System.out.println("End ho gaya");
}
}
