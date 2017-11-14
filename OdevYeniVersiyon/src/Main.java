import controller.StartPlayScreenController;
import view.StartPlayView;

public class Main {

    public static void main(String[] args)
    {
        StartPlayView startPlayView = new StartPlayView();
        StartPlayScreenController startPlayScreenController = new StartPlayScreenController(startPlayView);
        startPlayView.setVisible(true);
    }
}
