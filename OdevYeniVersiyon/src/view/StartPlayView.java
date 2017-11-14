package view;
import model.MessageDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPlayView extends JFrame{

    public static final int MIN_PLAYER_COUNT = 2;
    public static final int MAX_PLAYER_COUNT = 5;

    public static final int WIDTH_FRAME = 300;
    public static final int HEIGTH_FRAME = 300;

    public static final int PANEL_WIDTH = 200;
    public static final int PANEL_HEIGHT = 200;
    JFrame jFrame = new JFrame();
    JComboBox jComboBox = new JComboBox();
    JLabel jlabel = new JLabel(MessageDictionary.selectPlayerCount);
    JButton jButton = new JButton(MessageDictionary.startButton);

    public StartPlayView()
    {
        JPanel jpanel = new JPanel();
        jFrame.setSize(WIDTH_FRAME, HEIGTH_FRAME);
        jFrame.setBackground(Color.RED);
        jpanel.setBackground(Color.PINK);
        jpanel.add(jlabel, BorderLayout.CENTER);
        jpanel.add(jComboBox, BorderLayout.AFTER_LAST_LINE);
        jpanel.add(jButton, BorderLayout.AFTER_LINE_ENDS);
        jpanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        jFrame.add(jpanel, BorderLayout.CENTER);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        for(int i = MIN_PLAYER_COUNT; i <= MAX_PLAYER_COUNT; i++)
        {
            jComboBox.addItem(i);
        }
    }

    public int getPlayerCount()
    {
        return jComboBox.getSelectedIndex() + 2;
    }

    public void startPlayListener(ActionListener listenerStartPlayButton)
    {
        jButton.addActionListener(listenerStartPlayButton);
    }
}