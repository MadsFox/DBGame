import java.util.Hashtable;

public class GameController {

    //Reference til database så vi kan hente og sende via metoder
    private Database database;

    //Spilelementer i domænelaget
    Board board;
    private boolean running = false;

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
        Hashtable allMoveableData = database.getOnlyMoveable(); //Players ekskluderet
        Hashtable allPiecesData = database.getOnlyPieces(); //Players og Moveable ekskluderet

        log(String.valueOf(allPlayerData));
        log(String.valueOf(allMoveableData));
        log(String.valueOf(allPiecesData));

        //TODO smid data over i board
        //evt. board.add();
        //board.newPlayer(allPlayerData);
        //board.newMoveablePieces(allMoveableData);
        //board.newPieces(allPiecesData);

    }


    public void startGameLoop() {

        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 1;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        running = true;

        while(running == true) {
            long now = System.nanoTime(); //Find tidspunkt for, hvornår dette loop's iteration er
            long updateLength = now - lastLoopTime; //Find tiden der er gået mellem vores sidste loop og det nuværende
            lastLoopTime = now; //Når vi har fundet tiden mellem sidste loop og nu, sætter vi sidste loop tid til nu.

            double delta = updateLength / ((double) OPTIMAL_TIME); //Tiden mellem nu og sidste frame divideret med den tid vi gerne vil have

            update();
            render();


            try {
                Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
            } catch (InterruptedException e) {
                log("Fejl i game loop: " + e);
            }

        }

        //While loop
        //1. Opdater board
        //1.1 Slet alle pieces i board --> board.reset()
        //1.2 hent alle pieces fra database --> getPieces()
        //1.3 tilføj til board via addPiece() --> Kør alle de pieces vi hentede igennem i et loop, addPiece(PieceSomViErNåetTIlILykken)

        //2. Tegn/system.out.println()

    }

    private void render() {
        //Ingen grafik
    }

    private void update() {
        log("Starter update");

        Hashtable pieceList = getAllPieces(); //Indeholder både pieces, players, moveable

        Hashtable players = (Hashtable) pieceList.get("players");
        board.syncronizePlayers(players);

        Hashtable moveable = (Hashtable) pieceList.get("moveable");
        board.syncronizeMoveable(moveable);

        Hashtable pieces = (Hashtable) pieceList.get("pieces");
        board.syncronizePieces(pieces);
    }

    private Hashtable getAllPieces() {
        //SKAL EVT RYKKES TIL DATABASE

        log("Starter getAllPieces");
        //Her henter vi alle Pieces. Både Player, Moveable og NonMoveable
        //Og samler det i én Hashtable

        Hashtable playerList = database.getOnlyPlayers();
        Hashtable moveableList = database.getOnlyMoveable(); //Players ekskluderet
        Hashtable pieceList = database.getOnlyPieces(); //Players og Moveable ekskluderet

        Hashtable mergedData = new Hashtable();
        mergedData.put("players", playerList);
        mergedData.put("moveable", moveableList);
        mergedData.put("pieces", pieceList);

        return mergedData;
    }

    private void log(String logMessage) {
        System.out.println(logMessage);
    }

}
