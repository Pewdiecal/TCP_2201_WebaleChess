public class Player {
    private int playerID;
    private boolean isPlayerTurn;
    private String playerName;

    Player() {

    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean getPlayerTurn() {
        return this.isPlayerTurn;
    }
}
