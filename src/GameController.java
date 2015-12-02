import java.util.Hashtable;

public class GameController {

    //Reference til database så vi kan hente og sende via metoder
    private Database database;

    //Spilelementer i domænelaget
    Board board;

    public GameController(Database database) {
        this.database = database;

        log("GameController blev oprettet");
    }

    public void startGame(int boardId) {

        //Hent boardData ud fra boardId
        Hashtable boardData = database.getBoard(boardId);

        //Vi initialiserer boardet og smider boardData over i den
        if(boardData != null) {
            board = new Board(boardData);
        }
        else{
            //TODO: Vi kan forsøge at oprette et nyt board igen enten her eller i DBGame. Find alternativ.
            log("Det lykkedes ikke at oprette board.");
        }

        //SQL
        Hashtable allPlayerData = database.getOnlyPlayers();
        Hashtable moveableList = database.getOnlyMoveable(); //Players ekskluderet
        Hashtable Pieces = database.getOnlyPieces(); //Players og Moveable ekskluderet

        //TODO smid data over i board
        //evt. board.add();
        board.newPlayer(allPlayerData);

    }



    private void log(String logMessage) {
        System.out.println(logMessage);
    }

}
