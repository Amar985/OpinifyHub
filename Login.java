import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class Login {

    int id;

    public void loginView() throws SQLException {
        SQLManage manage = new SQLManage();
        JFrame frame = new JFrame("LOGIN");
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

        JLabel uname = new JLabel("Username : ");
        uname.setBounds(50, 130, 150, 50);
        uname.setFont(new Font("Courier", Font.PLAIN, 18));
        uname.setForeground(Color.white);
        l5.add(uname);

        JTextField name = new JTextField();
        name.setBounds(50, 170, 350, 30);
        name.setFont(new Font("Courier", Font.PLAIN, 18));
        l5.add(name);

        JLabel upass = new JLabel("Password : ");
        upass.setBounds(50, 200, 150, 50);
        upass.setFont(new Font("Courier", Font.PLAIN, 18));
        upass.setForeground(Color.white);
        l5.add(upass);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(50, 240, 350, 30);
        pass.setFont(new Font("Courier", Font.PLAIN, 18));
        l5.add(pass);

        JButton login = new JButton("LOGIN");
        login.setBounds(100, 300, 100, 40);
        login.setFont(new Font("Courier", Font.PLAIN, 15));
        login.setForeground(Color.white);
        login.setBackground(Color.blue);
        l5.add(login);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = name.getText();
                String password = pass.getText();
                if(username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please Enter All Details!!!", "Alert!", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    try {
                        SQLManage manage= new SQLManage();
                        id = manage.authUser(username, password);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (id == -1) {
                        JOptionPane.showMessageDialog(frame, "No User Found!!!", "Alert!", JOptionPane.WARNING_MESSAGE);
                    }
                    else if(id == 0) {
                        JOptionPane.showMessageDialog(frame, "Wrong Password!!!", "Alert!", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        MainPage mainPage = new MainPage();
                        try {
                            mainPage.mainPageView(id);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        frame.dispose();
                    }
                }
            }
        });

        JButton signUp = new JButton("SIGNUP");
        signUp.setBounds(250, 300, 100, 40);
        signUp.setFont(new Font("Courier", Font.PLAIN, 15));
        signUp.setForeground(Color.white);
        signUp.setBackground(Color.blue);
        l5.add(signUp);
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signup = new SignUp();
                signup.signUpView();
            }
        });

        JButton attend = new JButton("ATTEND A SURVEY (GUEST)");
        attend.setBounds(100, 350, 250, 40);
        attend.setFont(new Font("Courier", Font.PLAIN, 12));
        attend.setForeground(Color.white);
        attend.setBackground(Color.blue);
        l5.add(attend);
        attend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String surveyCode = JOptionPane.showInputDialog("Enter the Survey Code : ");
                try {
                    if(!surveyCode.isEmpty() && surveyCode.length() == 5) {
                        if(manage.check(surveyCode)) {
                            Guest guest = new Guest();
                            guest.guestView(surveyCode);
                        }
                        else {
                            JOptionPane.showMessageDialog(frame, "No Survey Available!!!", "Alert!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                catch(Exception e2) {

                }
            }
        });

        frame.setVisible(true);
    }
}