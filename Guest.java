import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Guest {

    SQLManage manage;
    int[] opt;
    int k;

    public void guestView(String surveyCode) throws SQLException {

        manage = new SQLManage();
        ResultSet rst = manage.getQuestions(surveyCode);
        opt = new int[50];

        Font options = new Font("Times New Roman", Font.BOLD, 15);

        JFrame frame = new JFrame("OpinifyHub");
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);


        JLabel l5=new JLabel();
        l5.setBounds(0,0,800,600);
        l5.setLayout(null);
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("guest.png"));
        l5.setIcon(img);
        frame.add(l5);

        JLabel start = new JLabel("Welcome! Please Attend The Survey");
        start.setBounds(0, 50, 800, 50);
        start.setHorizontalAlignment(JLabel.CENTER);
        start.setFont(new Font("Courier", Font.BOLD, 40));
        start.setForeground(Color.white);
        l5.add(start);

        JLabel ques = new JLabel("Question Here!!!");
        ques.setBounds(80, 200, 500, 30);
        ques.setFont(new Font("Courier", Font.BOLD, 18));
        ques.setForeground(Color.white);
        l5.add(ques);

        JRadioButton op1 = new JRadioButton("Option1");
        JRadioButton op2 = new JRadioButton("Option2");
        JRadioButton op3 = new JRadioButton("Opyion3");
        JRadioButton op4 = new JRadioButton("Option4");

        op1.setForeground(Color.white);
        op1.setOpaque(false);
        op2.setForeground(Color.white);
        op2.setOpaque(false);
        op3.setForeground(Color.white);
        op3.setOpaque(false);
        op4.setForeground(Color.white);
        op4.setOpaque(false);

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(op1);
        bgroup.add(op2);
        bgroup.add(op3);
        bgroup.add(op4);

        op1.setBounds(100, 250, 500, 30);
        op2.setBounds(100, 300, 500, 30);
        op3.setBounds(100, 350, 500, 30);
        op4.setBounds(100, 400, 500, 30);

        op1.setFont(options);
        op2.setFont(options);
        op3.setFont(options);
        op4.setFont(options);

        if(rst.next()) {
            ques.setText(rst.getString("question"));
            op1.setText(rst.getString("option1"));
            op2.setText(rst.getString("option2"));
            op3.setText(rst.getString("option3"));
            op4.setText(rst.getString("option4"));
        }

        l5.add(op1);
        l5.add(op2);
        l5.add(op3);
        l5.add(op4);
        k=0;

        JButton nextButton = new JButton("NEXT");
        nextButton.setBounds(100, 470, 600, 50);
        l5.add(nextButton);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x;
                if(op1.isSelected()) {
                    x=1;
                }
                else if(op2.isSelected()) {
                    x=2;
                }
                else if(op3.isSelected()) {
                    x=3;
                }
                else if(op4.isSelected()) {
                    x=4;
                }
                else
                    x=0;

                if(x!=0) {
                    opt[k] = x;
                    k++;
                    try {
                        if(rst.next()) {
                            ques.setText(rst.getString("question"));
                            op1.setText(rst.getString("option1"));
                            op2.setText(rst.getString("option2"));
                            op3.setText(rst.getString("option3"));
                            op4.setText(rst.getString("option4"));
                        }
                        else {
                            for(int j=0; j<k; j++) {
                                manage.answerUpdt(surveyCode, j+1, opt[j]);
                            }
                            JOptionPane.showMessageDialog(frame, "Survey Completed. Thank You.", "Congradulations", JOptionPane.PLAIN_MESSAGE);
                            manage.addTotal();
                            frame.dispose();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Select an option!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                bgroup.clearSelection();
            }
        });


        frame.setVisible(true);

    }
}
