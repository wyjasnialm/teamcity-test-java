package pl.edu.agh.automatedgrader.jtp2.lab5.additionals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new Main();
    }

    private JTextArea textArea;
    private JButton button1;
    private JButton button2;

    public Main() {
        super("Buttons");
        setSize(300, 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeComponents();
        pack();
        setVisible(true);
    }

    private void initializeComponents() {
        JPanel textPanel = new JPanel();
        textArea = new JTextArea(2, 20);
        textPanel.add(textArea);
        add(textPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        button1 = new JButton("Button1");
        button1.addActionListener(this);
        buttonsPanel.add(button1);

        button2 = new JButton("Button2");
        button2.addActionListener(this);
        buttonsPanel.add(button2);

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if(source == button1) textArea.setText("Button1 clicked");
        else if(source == button2) textArea.setText("Button2 clicked");
    }
}
