import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
class eg6 extends JFrame implements ActionListener
{
private JFrame frame;
private JTextField textField;
private JButton button;
private JLabel label;   
private Container c;     
public eg6() {
JFrame frame = new JFrame("Digit Only Input");
        textField = new JTextField(20);
	button=new JButton("Click");
	label=new JLabel("");
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // ignore this character
                }
            }
        });
	button.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c=getContentPane();
	c.setLayout(new FlowLayout());
	c.add(textField);
	c.add(button);
        c.add(label);
        setVisible(true);
    }
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==button)
{
label.setText(textField.getText());
}
}
}
class eg6psp
{
public static void main(String gg[])
{
eg6 e= new eg6();
}
}
