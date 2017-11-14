package view;

import model.MessageDictionary;
import sun.awt.X11.MWMConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;

import java.util.Map;

public class PlayScreenView extends JFrame {

    public static final int WIDTH = 1800;
    public static final int HEIGHT = 1800;

    public static final int MIN_ROW_WIDTH_TABLE = 60;
    public static final int MIN_ROW_HEIGHT_TABLE = 90;
    public static final int MIN_ROW_PIRATE_CELL = 80;
    public static final int MIN_WIDTH_PLAYER_TABLE = 400;
    public static final int MIN_HEIGHT_PLAYER_TABLE = 400;
    public static final int MIN_WIDTH_BOAT = 180;
    public static final int PLAYER_CARD_TABLE_ROW_HEIGHT = 200;
    public static final int PLAYER_CARD_TABLE_HEIGHT = 200;
    public static final int PLAYER_CARD_TABLE_WIDTH = 400;
    public static final int PLAYER_PIRATE_TABLE_WIDTH = 180;
    public static final int PIRATE_PANEL_WIDTH = 600;
    public static final int PIRATE_PANEL_HEIGHT = 500;
    public static final int PICTURE_WIDTH = 40;
    public static final int PICTURE_HEIGHT = 40;
    public static final int PIRATE_COUNT = 6;


    public static final int COLUMN_COUNT = 11;
    public static final int ROW_COUNT = 11;
    public static final int BOAT_IN_ROW_COUNT = 12;

    JPanel jp_west = new JPanel();
    JPanel jp_east = new JPanel();
    JPanel jp_card = new JPanel();
    JPanel jp_pirate = new JPanel();
    JPanel JPlayerInfo = new JPanel();
    JPanel jpButtons = new JPanel();

    JTable jt;
    JTable jtPlayer;
    JTable jtPlayerCard;
    JTable jtPlayerPirate;

    JButton jButton;
    JButton jPlayButton;
    JButton jButtonBoat;

    JLabel jPlayerNoLabel = new JLabel();
    JLabel jPlayerCardCountLabel = new JLabel();
    JLabel jlabel = new JLabel();


    JSplitPane split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    JSplitPane split3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    JSplitPane split4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    public PlayScreenView() {

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp_west, jp_east);

        jp_east.add(split1);
        jp_east.add(split4);
        jp_east.add(split2);
        jp_east.add(split3);

        jPlayButton = new JButton(MessageDictionary.playButtonText);
        this.add(jSplitPane);

        setTitle(MessageDictionary.playName);
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
    }

    public void createPlayTable(Object[] columns, Object[][] data) {
        int i = 0;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        jt = new JTable(model) {
            public Class getColumnClass(int column) {
                return ImageIcon.class;
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }

            ;
        };

        for (i = 0; i < COLUMN_COUNT; i++) {
            jt.setRowHeight(i, MIN_ROW_HEIGHT_TABLE);
        }

        for (i = 0; i < ROW_COUNT; i++) {
            jt.getColumnModel().getColumn(i).setMinWidth(MIN_ROW_WIDTH_TABLE);
        }

        jt.getColumnModel().getColumn(BOAT_IN_ROW_COUNT).setMinWidth(MIN_WIDTH_BOAT);
        jt.setColumnSelectionAllowed(false);
        jt.setRowSelectionAllowed(false);
        jp_west.setBackground(Color.blue);
        jp_west.add(jt);
    }

    public void createPlayerTable(Object[] columns, Object[][] data) {
        DefaultTableModel modelPlayer = new DefaultTableModel(data, columns);
        jtPlayer = new JTable(modelPlayer) {
            public Class getColumnClass(int column) {
                return ImageIcon.class;
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }

            ;
        };

        jtPlayer.setRowHeight(0, MIN_ROW_HEIGHT_TABLE);
        jtPlayer.setSelectionBackground(Color.ORANGE);
        jp_east.setPreferredSize(new Dimension(MIN_WIDTH_PLAYER_TABLE, MIN_HEIGHT_PLAYER_TABLE));
        jp_east.setBackground(Color.PINK);
        jtPlayer.setColumnSelectionAllowed(true);
        jtPlayer.setRowSelectionAllowed(false);
        jtPlayer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jp_east.setVisible(true);
        jButton = new JButton(MessageDictionary.pushCard);
        jButtonBoat = new JButton(MessageDictionary.pirateCountInBoat);
        jpButtons.add(jButton);
        jpButtons.add(jButtonBoat);
        split1.setTopComponent(jtPlayer);
        split1.setBottomComponent(jpButtons);
    }

    public void updatePlayerInformation(int player_no, int card_count, int total_card_count) {
        jlabel.setText(MessageDictionary.remainCardCount + total_card_count);
        jPlayerNoLabel.setText(MessageDictionary.playerNo + player_no);
        jPlayerCardCountLabel.setText(MessageDictionary.playerCardCount + card_count);

        JPlayerInfo.add(jPlayerNoLabel);
        JPlayerInfo.add(jPlayerCardCountLabel);

        split4.setTopComponent(JPlayerInfo);
        split4.setBottomComponent(jlabel);
    }

    public void updatePlayerCardTable(Object[] columns, Object[][] data) {
        DefaultTableModel modelPlayer = new DefaultTableModel(data, columns);
        jtPlayerCard = new JTable(modelPlayer) {
            public Class getColumnClass(int column) {
                return ImageIcon.class;
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }

            ;
        };

        jtPlayerCard.setSelectionBackground(Color.ORANGE);

        jtPlayerCard.setRowHeight(0, PLAYER_CARD_TABLE_ROW_HEIGHT);
        jp_card.setPreferredSize(new Dimension(PLAYER_CARD_TABLE_WIDTH, PLAYER_CARD_TABLE_HEIGHT));
        jp_card.setBackground(Color.RED);
        jtPlayerCard.setColumnSelectionAllowed(true);
        jtPlayerCard.setRowSelectionAllowed(false);
        jp_card.add(jtPlayerCard);
        split2.setTopComponent(jp_card);
        jp_card.setVisible(true);
        revalidate();
    }

    public void updatePlayerPirate(Object[] columns, Object[][] data) {
        DefaultTableModel modelPlayer = new DefaultTableModel(data, columns);
        jtPlayerPirate = new JTable(modelPlayer) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            ;

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                String status = (String) getValueAt(row, 1);
                if ("Bu korsan bota bindi".equals(status)) {
                    c.setBackground(Color.BLACK);
                } else {
                    c.setBackground(super.getBackground());
                    c.setEnabled(true);
                }
                return c;
            }
        };
        TableColumnModel tcm = jtPlayerPirate.getColumnModel();
        tcm.getColumn(0).setCellRenderer(new IconAndIntegerRenderer());
        jtPlayerPirate.setSelectionBackground(Color.ORANGE);
        for (int i = 0; i < PIRATE_COUNT; i++) {
            jtPlayerPirate.setRowHeight(i, MIN_ROW_PIRATE_CELL);
            if (jtPlayerPirate.getValueAt(i, 1).toString().equals("7. Segment12. Cell")) {
                jtPlayerPirate.setValueAt("Bu korsan bota bindi", i, 1);
                tcm.getColumn(0).setCellRenderer(new IconAndIntegerRenderer());
            }

        }
        jtPlayerPirate.getColumnModel().getColumn(0).setMinWidth(PLAYER_PIRATE_TABLE_WIDTH);
        jtPlayerPirate.getColumnModel().getColumn(1).setMinWidth(PLAYER_PIRATE_TABLE_WIDTH);
        jp_pirate.setPreferredSize(new Dimension(PIRATE_PANEL_WIDTH, PIRATE_PANEL_HEIGHT));
        jp_pirate.setBackground(Color.RED);
        jtPlayerPirate.setColumnSelectionAllowed(false);
        jtPlayerPirate.setRowSelectionAllowed(true);
        jtPlayerPirate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jp_pirate.add(jtPlayerPirate);
        split2.setBottomComponent(jp_pirate);
        jtPlayerPirate.setVisible(true);
        split3.setBottomComponent(jPlayButton);
        jPlayButton.setVisible(true);
        revalidate();
    }

    public int getSelectedColumnCard() {
        int colIndex = 0;
        if (jtPlayerCard.getColumnSelectionAllowed()
                && !jtPlayerCard.getRowSelectionAllowed()) {
            colIndex = jtPlayerCard.getSelectedColumn();

            if (colIndex == -1) {
                String message = MessageDictionary.selectCard;
                JOptionPane.showMessageDialog(new JFrame(), message, "",
                        JOptionPane.ERROR_MESSAGE);
                colIndex = jtPlayerCard.getSelectedColumn();
            }
        }
        return colIndex;
    }

    public int getSelectedColumnPirate() {
        int colIndex = 0;
        if (!jtPlayerPirate.getColumnSelectionAllowed()
                && jtPlayerPirate.getRowSelectionAllowed()) {
            colIndex = jtPlayerPirate.getSelectedRow();
            if (colIndex == -1) {
                String message = MessageDictionary.selectPirate;
                JOptionPane.showMessageDialog(new JFrame(), message, "",
                        JOptionPane.ERROR_MESSAGE);
                colIndex = jtPlayerPirate.getSelectedRow();
            }
        }
        return colIndex;
    }

    public int getSelectedColumnPlayer() {
        int colIndex = 0;

        if (jtPlayer.getColumnSelectionAllowed()
                && !jtPlayer.getRowSelectionAllowed()) {
            colIndex = jtPlayer.getSelectedColumn();
        }
        return colIndex;
    }

    public void showBoatInfo(Map<Color, Integer> hashMap, boolean isFinishedGame) {
        String message = "";
        if (isFinishedGame == true) {
            message += MessageDictionary.finishedGame;
        }

        if (hashMap.containsKey(Color.BLUE)) {
            if (hashMap.get(Color.BLUE) == 6)
                message += Color.BLUE.toString() + MessageDictionary.winnerPlayer;
            if (hashMap.get(Color.BLUE) != 0)
                message += "Mavi korsandan " + hashMap.get(Color.BLUE) + " tane var\n";
        }
        if (hashMap.containsKey(Color.YELLOW)) {
            if (hashMap.get(Color.YELLOW) == 6)
                message += Color.YELLOW.toString() + MessageDictionary.winnerPlayer;
            if (hashMap.get(Color.YELLOW) != 0)
                message += "Sarı korsandan " + hashMap.get(Color.YELLOW) + " tane var\n";
        }
        if (hashMap.containsKey(Color.RED)) {
            if (hashMap.get(Color.RED) == 6)
                message += Color.RED.toString() + MessageDictionary.winnerPlayer;
            if (hashMap.get(Color.RED) != 0)
                message += "Kırmızı korsandan " + hashMap.get(Color.RED) + " tane var\n";
        }
        if (hashMap.containsKey(Color.GREEN)) {
            if (hashMap.get(Color.GREEN) == 6)
                message += Color.GREEN.toString() + MessageDictionary.winnerPlayer;
            if (hashMap.get(Color.GREEN) != 0)
                message += "Yeşil korsandan " + hashMap.get(Color.GREEN) + " tane var\n";
        }
        if (hashMap.containsKey(Color.BLACK)) {
            if (hashMap.get(Color.BLACK) == 6)
                message += Color.BLACK.toString() + MessageDictionary.winnerPlayer;
            if (hashMap.get(Color.BLACK) != 0)
                message += "Kahverengi korsandan " + hashMap.get(Color.BLACK) + " tane var\n";
        }
        if (message == "") {
            message = MessageDictionary.emptyBoat;
        }

        JOptionPane.showMessageDialog(new JFrame(), message, "",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void addPirateToPlayPath(Map<Point, Point> mapPoint, Point[] p, String pirateImg) {
        if (mapPoint.containsKey(p[0])) {
            Point oldPoint = mapPoint.get(p[0]);
            ImageIcon emptyIcon = new ImageIcon();
            jt.setValueAt(emptyIcon, oldPoint.x, oldPoint.y);
        }
        if (mapPoint.containsKey(p[1])) {
            Point newPoint = mapPoint.get(p[1]);
            ImageIcon imageIcon = new ImageIcon(pirateImg);
            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(PICTURE_WIDTH, PICTURE_HEIGHT, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);  // transform it back
            jt.setValueAt(imageIcon, newPoint.x, newPoint.y);
        } else {
            //İmage iconların olduğu array eklenmeli
            ImageIcon imageIcon = new ImageIcon(pirateImg);
            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(PICTURE_WIDTH, PICTURE_HEIGHT, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);  // transform it back
            jt.setValueAt(imageIcon, 7, 12);
        }

        jtPlayerPirate.setVisible(false);
        jtPlayerCard.setVisible(false);
        jPlayerCardCountLabel.setText("");
        jPlayerNoLabel.setText("");
        revalidate();
    }

    public void pullCardButtonListener(ActionListener listenerPullCardButtonListener) {
        jButton.addActionListener(listenerPullCardButtonListener);
    }

    public void showBoatInfoButtonListener(ActionListener listenerShowBoatInfoButtonListener) {
        jButtonBoat.addActionListener(listenerShowBoatInfoButtonListener);
    }

    public void playButtonListener(ActionListener listenerPlayButtonListener) {
        jPlayButton.addActionListener(listenerPlayButtonListener);
    }

    public class IconAndIntegerRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);
            if (value instanceof Icon) {
                setText("");
                setIcon((Icon) value);
            }
            return this;
        }
    }
}
