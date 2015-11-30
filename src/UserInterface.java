
public class UserInterface {

    private GameController gameController;

    public UserInterface(GameController gameController) {
        this.gameController = gameController;

        log("UserInterface blev oprettet.");
    }

    private void log(String logMessage) {
        System.out.println(logMessage);
    }
}
