import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/*
Vi kan lave flere constructorer, hvor vi kræver andet end Hashtable
F.eks. int, array osv.
*/

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

    public void newPlayer (Hashtable allPlayerData){
        //For at få adgang til det enkelte player hashtable uden at kende deres ID
        Set s = allPlayerData.entrySet();
        Iterator it = s.iterator();

        while(it.hasNext()) {
            Hashtable playerData = (Hashtable)it.next();//Fuck up waiting....Tror ikke vi må typecaste sådan
            Player player = new Player(
                    (int) playerData.get("id"),
                    (String) playerData.get("name"),
                    (int) playerData.get("x"),
                    (int) playerData.get("y"),
                    (int) playerData.get("z"),
                    (int) playerData.get("width"),
                    (int) playerData.get("height"),
                    (int) playerData.get("depth"),
                    (int) playerData.get("weight"),
                    (int) playerData.get("speed"),
                    (int) playerData.get("acceleration"),
                    (int) playerData.get("health"),
                    (int) playerData.get("yaw"),
                    (int) playerData.get("pitch"),
                    (int) playerData.get("roll"));
            pieceList.add(player);
        }

    }
}
