import java.awt.*;
class awt4 extends Frame
{
private Label heading, l1,l2,l3,l4,l5,l6;
private TextField t1, t2;
private Choice c1;
private List lst1;
private Button b1,b2;
private Checkbox cb1;
private CheckboxGroup cg1;
private Checkbox r1,r2;
awt4()
{ 
heading=new Label("Student (Add Module)");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3)); 
p1.add(new Label("        "));
p1.add(heading);
p1.add(new Label("        "));

l1=new Label("Roll number");
t1=new TextField(10);

l2=new Label("Roll Number");
t2=new TextField(50);

l3=new Label("City");
c1=new Choice();
c1.add("Ujjain");
c1.add("Dewas");
c1.add("Mumbai");

l4=new Label("Hobbies");
lst1=new List(4,true);
lst1.add("Swimming");
lst1.add("Cricket");
lst1.add("Balling");
lst1.add("Cooking");
lst1.add("Dancing");

l5=new Label("Indian");
cb1=new Checkbox();

cg1=new CheckboxGroup();
l6=new Label("Gender");
r1=new Checkbox("Male",cg1,true);
r2=new Checkbox("Female",cg1,false);
Panel p2=new Panel();
p2.setLayout(new GridLayout(1,2));
p2.add(r1);
p2.add(r2);

b1=new Button("Save");
b2=new Button("Cancel");

Panel p3=new Panel();
p3.setLayout(new GridLayout(1,5));
p3.add(new Label("        "));
p3.add(b1);
p3.add(new Label("        "));
p3.add(b2);
p3.add(new Label("        "));
Panel p4=new Panel();
p4.setLayout(new GridLayout(6,2));
p4.add(l1);
p4.add(t1);
p4.add(l2);
p4.add(t2);
p4.add(l3);
p4.add(c1);
p4.add(l1);
p4.add(lst1);
p4.add(l5);
p4.add(cb1);
p4.add(l6);
p4.add(p2);
BorderLayout bl=new BorderLayout();
setLayout(bl);
add(p1,BorderLayout.NORTH);
add(p1,BorderLayout.CENTER);
add(p1,BorderLayout.SOUTH);
add(new Label("      "),BorderLayout.EAST);
add(new Label("      "),BorderLayout.WEST);
setLocation(300,100);
setSize(500,350);
setVisible(true);
}
}
class awt4psp
{
public static void main(String gg[])
{
awt4 aa=new awt4();
}
}
