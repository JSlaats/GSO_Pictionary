package domain;

public class ActivePlayer {
    private Player activePlayer;
    private BrushProperties brush;

    public ActivePlayer(Player player){
        this.activePlayer = player;
        this.brush = new BrushProperties();
    }


    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public BrushProperties getBrush() {
        return brush;
    }

    public void setBrush(BrushProperties brush) {
        this.brush = brush;
    }
}
