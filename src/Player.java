public class Player {
    private int playerID;
    private boolean isPlayerTurn;
    private String playerName;

    Player(String playerName, int playerID) {
        this.playerName = playerName;
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    public boolean getPlayerTurn() {
        return this.isPlayerTurn;
    }

    public int getPlayerID() {
        return playerID;
    }
}
