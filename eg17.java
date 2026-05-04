import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class eg17psp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ComboBox Example");
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Item 1", "Item 2", "Item 3"});

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                System.out.println("Selected item: " + selectedItem);
                // Perform other actions based on the selected item
            }
        });

        frame.getContentPane().add(comboBox);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
