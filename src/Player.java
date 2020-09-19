/**
 * The Player class defines the properties of a player.
 *
 * @author Mohamad Faris Bin Harunasir
 */
public class Player {
    private int playerID;
    private boolean isPlayerTurn;
    private String playerName;

    /**
     * This is the constructor that passes the player's name and ID to its attributes.
     * @param playerName The chess piece's name.
     * @param playerID The chess piece's image path.
     * @author Mohamad Faris Bin Harunasir
     */
    Player(String playerName, int playerID) {
        this.playerName = playerName;
        this.playerID = playerID;
    }

    /**
     * @return The player's name.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * @param playerTurn The player's turn to set.
     */
    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    /**
     * @return The player's turn.
     */
    public boolean getPlayerTurn() {
        return this.isPlayerTurn;
    }

    /**
     * @return The player's ID.
     */
    public int getPlayerID() {
        return playerID;
    }
}
