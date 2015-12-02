import java.util.Hashtable;
import java.sql.*;

//hej heh

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

        //Forsøg at opret et statement
        try {
            Statement statement = connection.createStatement();

            //SQL-sætning / Syntax
            String SQL = "SELECT pieces.id, x, y, z, width, height, depth, speed, acceleration, weight, name, roll, pitch, yaw FROM pieces, moveable, players WHERE moveable.id = pieces.id AND players.id = moveable.id";
            //Lav et ResultSet
            try {
                ResultSet playerDataSQL = statement.executeQuery(SQL);
                Hashtable allPlayerdata = new Hashtable();

                //Loop igennem alle rækkerne, der er gemt i playerData
                //Vi forventer at lykken KUN kører 1 gang.

                while(playerDataSQL.next()) {
                    Hashtable playerData = new Hashtable();
                    playerData.put("id", playerDataSQL.getInt("id"));
                    playerData.put("name", playerDataSQL.getString("name"));
                    playerData.put("x", playerDataSQL.getInt("x"));
                    playerData.put("y", playerDataSQL.getInt("y"));
                    playerData.put("z", playerDataSQL.getInt("z"));
                    playerData.put("width", playerDataSQL.getInt("width"));
                    playerData.put("height", playerDataSQL.getInt("height"));
                    playerData.put("depth", playerDataSQL.getInt("depth"));
                    playerData.put("speed", playerDataSQL.getInt("speed"));
                    playerData.put("acceleration", playerDataSQL.getInt("acceleration"));
                    playerData.put("weight", playerDataSQL.getInt("weight"));
                    playerData.put("name", playerDataSQL.getInt("name"));
                    playerData.put("roll", playerDataSQL.getInt("roll"));
                    playerData.put("pitch", playerDataSQL.getInt("pitch"));
                    playerData.put("yaw", playerDataSQL.getInt("yaw"));
                    allPlayerdata.put("" + playerDataSQL.getInt("id"), playerData);
                }

                //Luk statement igen, fordi det er sikkert en god idé
                statement.close();

                //Returnér Hashtable data, som indeholder id, name, width osv.
                log("Modtog board data fra DB: " + allPlayerdata);

                //TODO: Overvej om vi skal tjekke om data er et tomt Hashtable
                return allPlayerdata;
            }
            catch (SQLException e) {
                log("Fejl i udførelse af query: " + e);
            }
        }
        catch (SQLException e) {
            log("Fejl ved oprettelse af SQL-statement: " + e);
        }
        return onlyPlayers;
    }

    public Hashtable getOnlyMoveable() {
        //Forsøg at opret et statement
        try {
            Statement statement = connection.createStatement();

            //SQL-sætning / Syntax
            String MoveSQL = "SELECT pieces.id, x, y, z, width, height, depth, speed, acceleration, weight FROM pieces, moveable, players WHERE moveable.id = pieces.id AND players.id != moveable.id";
            //Lav et ResultSet
            try {
                ResultSet moveDataSQL = statement.executeQuery(MoveSQL);
                Hashtable allMovedata = new Hashtable();

                //Loop igennem alle rækkerne, der er gemt i playerData
                //Vi forventer at lykken KUN kører 1 gang.

                while(moveDataSQL.next()) {
                    Hashtable moveData = new Hashtable();
                    moveData.put("id", moveDataSQL.getInt("id"));
                    moveData.put("name", moveDataSQL.getString("name"));
                    moveData.put("x", moveDataSQL.getInt("x"));
                    moveData.put("y", moveDataSQL.getInt("y"));
                    moveData.put("z", moveDataSQL.getInt("z"));
                    moveData.put("width", moveDataSQL.getInt("width"));
                    moveData.put("height", moveDataSQL.getInt("height"));
                    moveData.put("depth", moveDataSQL.getInt("depth"));
                    moveData.put("speed", moveDataSQL.getInt("speed"));
                    moveData.put("acceleration", moveDataSQL.getInt("acceleration"));
                    moveData.put("weight", moveDataSQL.getInt("weight"));
                    moveData.put("name", moveDataSQL.getInt("name"));
                    moveData.put("roll", moveDataSQL.getInt("roll"));
                    moveData.put("pitch", moveDataSQL.getInt("pitch"));
                    moveData.put("yaw", moveDataSQL.getInt("yaw"));
                    allMovedata.put("" + moveDataSQL.getInt("id"), moveData);
                }

                //Luk statement igen, fordi det er sikkert en god idé
                statement.close();

                //Returnér Hashtable data, som indeholder id, name, width osv.
                log("Modtog board data fra DB: " + allMovedata);

                //TODO: Overvej om vi skal tjekke om data er et tomt Hashtable
                return allMovedata;
            }
            catch (SQLException e) {
                log("Fejl i udførelse af query: " + e);
            }
        }
        catch (SQLException e) {
            log("Fejl ved oprettelse af SQL-statement: " + e);
        }
        return onlyMoveable;
    }

    public Hashtable getOnlyPieces() {

        //Forsøg at opret et statement
        try {
            Statement statement = connection.createStatement();

            //SQL-sætning / Syntax
            String PieceSQL = "SELECT pieces.id, x, y, z, width, height, depth FROM pieces, moveable WHERE moveable.id != pieces.id";
            //Lav et ResultSet
            try {
                ResultSet moveDataSQL = statement.executeQuery(PieceSQL);
                Hashtable allPiecedata = new Hashtable();

                //Loop igennem alle rækkerne, der er gemt i playerData
                //Vi forventer at lykken KUN kører 1 gang.

                while(moveDataSQL.next()) {
                    Hashtable pieceData = new Hashtable();
                    pieceData.put("id", moveDataSQL.getInt("id"));
                    pieceData.put("name", moveDataSQL.getString("name"));
                    pieceData.put("x", moveDataSQL.getInt("x"));
                    pieceData.put("y", moveDataSQL.getInt("y"));
                    pieceData.put("z", moveDataSQL.getInt("z"));
                    pieceData.put("width", moveDataSQL.getInt("width"));
                    pieceData.put("height", moveDataSQL.getInt("height"));
                    pieceData.put("depth", moveDataSQL.getInt("depth"));
                    pieceData.put("speed", moveDataSQL.getInt("speed"));
                    pieceData.put("acceleration", moveDataSQL.getInt("acceleration"));
                    pieceData.put("weight", moveDataSQL.getInt("weight"));
                    pieceData.put("name", moveDataSQL.getInt("name"));
                    pieceData.put("roll", moveDataSQL.getInt("roll"));
                    pieceData.put("pitch", moveDataSQL.getInt("pitch"));
                    pieceData.put("yaw", moveDataSQL.getInt("yaw"));
                    allPiecedata.put("" + moveDataSQL.getInt("id"), pieceData);
                }

                //Luk statement igen, fordi det er sikkert en god idé
                statement.close();

                //Returnér Hashtable data, som indeholder id, name, width osv.
                log("Modtog board data fra DB: " + allPiecedata);

                //TODO: Overvej om vi skal tjekke om data er et tomt Hashtable
                return allPiecedata;
            }
            catch (SQLException e) {
                log("Fejl i udførelse af query: " + e);
            }
        }
        catch (SQLException e) {
            log("Fejl ved oprettelse af SQL-statement: " + e);
        }return onlyPieces;
    }
}
