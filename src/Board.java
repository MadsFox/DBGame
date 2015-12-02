import java.util.ArrayList;
import java.util.Hashtable;

/*
Vi kan lave flere constructorer, hvor vi kræver andet end Hashtable
F.eks. int, array osv.
*/

//blabla

public class Board {

    int id;
    String name;
    int width;
    int height;
    int depth;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Board(Hashtable boardData) {
        this.id = (int) boardData.get("id");
        this.name = (String) boardData.get("name");
        this.width = (int) boardData.get("width");
        this.height = (int) boardData.get("height");
        this.depth = (int) boardData.get("depth");
        log("Nyt board oprettet. Data: " + boardData);
    }

    private void log(String logMessage) {
        System.out.println(logMessage);
    }

    public void newPlayer (Hashtable playerList){

        Player player = new Player(
                (int)playerList.get("id"),
                (String)playerList.get("name"),
                (int)playerList.get("x"),
                (int)playerList.get("y"),
                (int)playerList.get("z"),
                (int)playerList.get("width"),
                (int)playerList.get("height"),
                (int)playerList.get("depth"),
                (int)playerList.get("weight"),
                (int)playerList.get("speed"),
                (int)playerList.get("acceleration"),
                (int)playerList.get("health"),
                (int)playerList.get("yaw"),
                (int)playerList.get("pitch"),
                (int)playerList.get("roll"));

        for(player : playerList){

        }

    }
}
