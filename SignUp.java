import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import java.sql.SQLException;

import javax.swing.*;


public class SignUp {
    public void signUpView() {
        JFrame frame = new JFrame("SIGNUP");
        frame.setSize(450, 450);

        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel l5=new JLabel();
        l5.setBounds(0,0,450,450);
        l5.setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("login.jpg"));
        l5.setIcon(img);
        frame.add(l5);



        JLabel heading = new JLabel("OpinifyHub");
        heading.setBounds(0, 50, 450, 50);
        heading.setForeground(Color.white);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setFont(new Font("Courier", Font.BOLD, 40));
        l5.add(heading);

        JLabel fName = new JLabel("Name : ");
        fName.setForeground(Color.white);
        fName.setFont(new Font("Courier", Font.PLAIN, 18));
        fName.setBounds(50, 120, 150, 50);
        l5.add(fName);

        JTextField fNameField = new JTextField();

        fNameField.setBounds(50, 160, 350, 30);
        fNameField.setFont(new Font("Courier", Font.PLAIN, 18));
        l5.add(fNameField);

        JLabel uName = new JLabel("Username : ");
        uName.setForeground(Color.white);
        uName.setBounds(50, 190, 150, 50);
        uName.setFont(new Font("Courier", Font.PLAIN, 18));
        l5.add(uName);

        JTextField uNameField = new JTextField();
        uNameField.setBounds(50, 230, 350, 30);
        uNameField.setFont(new Font("Courier", Font.PLAIN, 18));
        l5.add(uNameField);

        JLabel uPass = new JLabel("Password : ");
        uPass.setForeground(Color.white);
        uPass.setBounds(50, 260, 150, 50);
        uPass.setFont(new Font("Courier", Font.PLAIN, 18));
        l5.add(uPass);

        JPasswordField uPassField = new JPasswordField();
        uPassField.setBounds(50, 300, 150, 30);
        uPassField.setFont(new Font("Courier", Font.PLAIN, 18));
        l5.add(uPassField);

        JLabel uPass2 = new JLabel("Confirm Password : ");
        uPass2.setForeground(Color.white);
        uPass2.setBounds(250, 260, 150, 50);
        uPass2.setFont(new Font("Courier", Font.PLAIN, 18));
        l5.add(uPass2);

        JPasswordField uPassField2 = new JPasswordField();
        uPassField2.setBounds(250, 300, 150, 30);
        uPassField2.setFont(new Font("Courier", Font.PLAIN, 18));
        l5.add(uPassField2);

        JButton submit = new JButton("SUBMIT");
        submit.setForeground(Color.white);
        submit.setBackground(Color.blue);
        submit.setBounds(175, 350, 100, 40);
        submit.setFont(new Font("Courier", Font.PLAIN, 15));
        l5.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname = fNameField.getText();
                String uname = uNameField.getText();
                String pass1 = uPassField.getText();
                String pass2 = uPassField2.getText();
                if(fname.isEmpty() || uname.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please Enter All Details!!!", "Alert!", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    if(pass1.equals(pass2)) {
                        try {
                            SQLManage manage = new SQLManage();
                            manage.newUser(fname, uname, pass1);
                            fNameField.setText("");
                            uNameField.setText("");
                            uPassField.setText("");
                            uPassField2.setText("");
                            JOptionPane.showMessageDialog(frame, "User Created Succesfully!!!", "Welcome!", JOptionPane.PLAIN_MESSAGE);
                            frame.dispose();

                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(frame, "Something went wrong!!!", "Alert!", JOptionPane.WARNING_MESSAGE);
                        }

                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Password Mismatch", "Alert!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}