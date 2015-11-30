import java.util.Hashtable;
import java.sql.*;

public class Database {

    //JDBC-felter
    Connection connection;

    //Navne til tabeller i vores database
    private String boardTable = "boards";
    private String pieceTable = "pieces";
    private Hashtable onlyPlayers;
    private Hashtable onlyMoveable;
    private Hashtable onlyPieces;

    public Database(String host, String port, String database, String username, String password) {

        //Brug klasse fra JDBC-driver (vores .jar-fil)
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                log("Forbindelse oprettet");
            }
            catch (SQLException e) {
                log("Kunne ikke oprette forbindelse til " + host + ":" + port + ".");
                log(String.valueOf(e)); //skriv fejlmeddelelse på næste linje.
            }

        }
        catch (ClassNotFoundException e) {
            log("JDBC driver blev ikke fundet: " + e);
        }

        log("Database blev oprettet");
    }

    public Hashtable getBoard(int boardId) {

        //Forsøg at opret et statement
        try {
            Statement statement = connection.createStatement();

            //SQL-sætning / Syntax
            String SQLQuery = "SELECT * FROM " + boardTable + " WHERE id = " + boardId;

            //Lav et ResultSet
            try {
                ResultSet boardData = statement.executeQuery(SQLQuery);
                Hashtable data = new Hashtable();

                //Loop igennem alle rækkerne, der er gemt i boardData
                //Vi forventer at lykken KUN kører 1 gang.
                while(boardData.next()) {
                    data.put("id", boardData.getInt("id"));
                    data.put("name", boardData.getString("name"));
                    data.put("width", boardData.getInt("width"));
                    data.put("height", boardData.getInt("height"));
                    data.put("depth", boardData.getInt("depth"));
                }

                //Luk statement igen, fordi det er sikkert en god idé
                statement.close();

                //Returnér Hashtable data, som indeholder id, name, width osv.
                log("Modtog board data fra DB: " + data);

                //TODO: Overvej om vi skal tjekke om data er et tomt Hashtable
                return data;
            }
            catch (SQLException e) {
                log("Fejl i udførelse af query: " + e);
            }
        }
        catch (SQLException e) {
            log("Fejl ved oprettelse af SQL-statement: " + e);
        }

        //Hvis ikke vi retrunerer andet før det her, returnerer vi null
        return null;
    }

    private void log(String logMessage) {
        System.out.println(logMessage);
    }

    public Hashtable getOnlyPlayers() {
        String SQL = "SELECT pieces.id, x, y, z, width, height, depth, speed, acceleration, weight, name, roll, pitch, yaw FROM pieces, moveable, players WHERE moveable.id = pieces.id AND players.id = moveable.id";

        return onlyPlayers;
    }

    public Hashtable getOnlyMoveable() {
        return onlyMoveable;
    }

    public Hashtable getOnlyPieces() {
        return onlyPieces;
    }
}
