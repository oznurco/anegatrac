package controller;

import model.*;
import model.CardPackage.Card;
import view.PlayScreenView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayScreenController {

    public static final int PLAY_PATH_CARD_COUNT = 37;
    PlayScreenView theView;
    CarteganaPlayModel theModel;

    public PlayScreenController(PlayScreenView playScreenView, CarteganaPlayModel carteganaPlayModel)
    {
        theView = playScreenView;
        theModel = carteganaPlayModel;
    }

    public void preparePlayScreen(int player_count)
    {
        int i = 0;
        ArrayList<Card> cards = new ArrayList<Card>();
        ArrayList<Segment> playPath = new ArrayList<Segment>();
        ArrayList<Player> players = new ArrayList<Player>();
        ImageIcon[] imageIcons = new ImageIcon[PLAY_PATH_CARD_COUNT];
        Map<ImageIcon, Card> mapCard = new HashMap<ImageIcon, Card>(36);

        cards = theModel.createCards();
        playPath = theModel.createPlayPath();
        players = theModel.createPlayer();

        int index = 0;
        for(i = 0; i < 6; i ++)
        {
            for(int j = 0; j < 6; j ++)
            {
                ImageIcon imageIcon = new ImageIcon(playPath.get(i).getSegmentCell(j).getCardInCell().getImg());
                imageIcons[index] = imageIcon;
                mapCard.put((ImageIcon) imageIcon, (Card) playPath.get(i).getSegmentCell(j).getCardInCell());
                index ++;
            }
        }
        imageIcons[index] = new ImageIcon(theModel.getBoat().getBoatImg());

        Object[] columns_play_path = {"","","","","","","","","","","","",""};
        Object[][] data = {{"", "", "", "", "", "", "", "", "", "", "", "",""},
                {"", imageIcons[12], imageIcons[13], imageIcons[14], imageIcons[15], imageIcons[16], imageIcons[17], "", "", "", "", "",""},
                {"", imageIcons[11], "", "", "", "", imageIcons[18], "", "", "", "", "",""},
                {"", imageIcons[10], imageIcons[9], "", "", imageIcons[20], imageIcons[19], "", "", "", "", "",""},
                {"", "", imageIcons[8], "", "", imageIcons[21], "", "", "", "", "", "",""},
                {"", imageIcons[6], imageIcons[7], "", "", imageIcons[22], imageIcons[23], "", "", "", "", "",""},
                {"", imageIcons[5], "", "", "", "", imageIcons[24], "", "", "", "", "",""},
                {"", imageIcons[4], imageIcons[3], "", "", imageIcons[26], imageIcons[25], "", "", "", "", "",""},
                {"", "", imageIcons[2], "", "", imageIcons[27], "", "", imageIcons[32], imageIcons[33], imageIcons[34], imageIcons[35],imageIcons[36]},
                {"", imageIcons[0], imageIcons[1], "", "", imageIcons[28], imageIcons[29], imageIcons[30], imageIcons[31],"","","",""},
                {"", "", "", "", "", "", "", "", "", "", "", "",""}};

        theView.createPlayTable(columns_play_path, data);

        Object[] columns_player_table = new Object[player_count];
        Object[][] data_player_table = new Object[1][player_count];
        int index_player = player_count;
        for(Player p : players)
        {
            if(index_player > -1)
            {
                ImageIcon imgPlayer = new ImageIcon(p.getPlayerImg());
                data_player_table[0][index_player-1] = imgPlayer;
                index_player --;
            }
        }
        theView.createPlayerTable(columns_player_table, data_player_table);
        theView.pullCardButtonListener(new pullCardButtonListener());
        theView.playButtonListener(new playButtonListener());
        theView.showBoatInfoButtonListener(new showBoatInfoButtonListener());
    }

    public void play(int player_no)
    {
        ArrayList<Player> players = theModel.getPlayers();

         int i = player_no;
          Object[] columns_player_cards = new Object[players.get(i).getCards().size()];
          Object[][] data_player_cards = new Object[1][players.get(i).getCards().size()];

          for (int m = 0; m < players.get(i).getCards().size(); m++) {

            ImageIcon card1 = new ImageIcon(players.get(i).getCards().get(m).getImg());
            data_player_cards[0][m] = card1;
          }

          Object[] columns_player_pirate = new Object[2];
          Object[][] data_player_pirates = new Object[players.get(i).getPirates().size()][2];

          for(int m = 0; m < players.get(i).getPirates().size(); m ++)
          {
              Pirate p = players.get(i).getPirates().get(m);
              ImageIcon pirate = new ImageIcon(p.getPirateImg());
              data_player_pirates[m][0] = pirate;
              data_player_pirates[m][1] = p.getPoint().x  + MessageDictionary.segment + p.getPoint().y + MessageDictionary.cell;
          }
          theView.updatePlayerInformation(i, players.get(i).getCards().size(), theModel.getCards().size());
          theView.updatePlayerCardTable(columns_player_cards, data_player_cards);
          theView.updatePlayerPirate(columns_player_pirate, data_player_pirates);
    }

    class pullCardButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            play(theView.getSelectedColumnPlayer());
            }
    }

    class playButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            if(theView.getSelectedColumnCard() != -1 && theView.getSelectedColumnPirate() != -1)
            {
                Card selectedCard = theModel.getPlayers().get(theView.getSelectedColumnPlayer()).getCards().get(theView.getSelectedColumnCard());

                Pirate selectedPirate = theModel.getPlayers().get(theView.getSelectedColumnPlayer()).selectedPirate(theView.getSelectedColumnPirate());
                if(theModel.isFinishedGame() == false && theModel.getCards().size() != 0) {
                    Point[] points = theModel.playCard(theView.getSelectedColumnPlayer(), selectedCard, selectedPirate);
                    theView.addPirateToPlayPath(theModel.mappingPoint(), points, selectedPirate.getPirateImg());
                }
                else
                {
                    theView.showBoatInfo(theModel.getBoat().getPirateCountInBoat(), true);
                }
            }
        }
    }
    class showBoatInfoButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            theView.showBoatInfo(theModel.getBoat().getPirateCountInBoat(), false);

        }
    }
}
