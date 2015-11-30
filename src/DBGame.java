/*
    Hovedklassen og start-punkt for programmet.
    Version: 1.2
    Dato: 30/11/2015

    Her laver vi følgende objekter:
        1. UI-objekt (der står for visualisering og bruger-input)
        2. Database-objekt (der står for kommunikation frem og tilbage til vores MYSQL database)
        3. GameController (der står for at være laget mellem UI og Database. Kommunikation/logik/osv.)
*/

public class DBGame {

    public Database database;
    public GameController gameController;
    public UserInterface userInterface;

    public DBGame() {
        //Initialiser objekterne
        this.database = new Database("localhost", "3306", "version2", "root", "");
        this.gameController = new GameController(database);
        this.gameController.startGame(1);

        //this.userInterface = new UserInterface(gameController);
    }

    public static void main(String[] args) {
        //Start ny applikation
        DBGame application = new DBGame();
    }

    public static void log(String hej) {

    }
}
