import java.awt.*;
class awt1 extends Frame
{
private Label l1,l2,l3,l4,l5;
private TextField t1, t2;
private Choice c1;
private List lst1;
private Button b1,b2;
private Checkbox cb1;
private CheckboxGroup cg1;
private Checkbox r1, r2;
awt1()
{
l1=new Label ("Roll number");
l2=new Label("Name");
l3=new Label("City");
l4=new Label("Hobbies");
l5=new Label ("Gender");
t1=new TextField(10);
t2=new TextField(50);
c1=new Choice();
c1.add("Ujjain");
c1.add("Dewas");
c1.add("Mumbai");
cb1=new Checkbox ("Indian");
cg1=new CheckboxGroup();
r1=new Checkbox("male", cg1, true);
r2=new Checkbox("Female", cg1, false);
lst1=new List (4, true);
lst1.add("swimming");
lst1.add("Reading");
lst1.add("Movies");
lst1.add("Dancing");
lst1.add("Programming");
lst1.add("origami");
lst1.add("cooking");
b1=new Button("Save");
b2=new Button("Cancel");
FlowLayout f=new FlowLayout();
setLayout(f);
add(l1);
add(t1);
add(l2);
add(t2);
add(l3);
add(c1);
add(l4);
add(lst1);
add(cb1);
add(l5);
add(r1);
add(r2);
add(b1);
add(b2);
setLocation (300,50);
setSize(500,700);
setVisible(true);
}
}

class awt1psp 
{
public static void main(String gg[])
{
awt1 a=new awt1();
}
}
