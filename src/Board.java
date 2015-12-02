import java.util.ArrayList;
import java.util.Hashtable;

/*
Vi kan lave flere constructorer, hvor vi kræver andet end Hashtable
F.eks. int, array osv.
*/

//Ændring

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

}
