package controller;

import model.CarteganaPlayModel;
import view.PlayScreenView;
import view.StartPlayView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPlayScreenController {

    StartPlayView theView;

    public StartPlayScreenController(StartPlayView startPlayView)
    {
        theView = startPlayView;
        theView.startPlayListener(new startPlayListener());
    }
    class startPlayListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            int player_count = 0;
            player_count = theView.getPlayerCount();
            PlayScreenView playScreenView = new PlayScreenView();
            CarteganaPlayModel carteganaPlayModel = new CarteganaPlayModel(player_count);
            PlayScreenController playScreenController = new PlayScreenController(playScreenView, carteganaPlayModel);
            playScreenView.setVisible(true);
            playScreenController.preparePlayScreen(player_count);
        }
    }
}
