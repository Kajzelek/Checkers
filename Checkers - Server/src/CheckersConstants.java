public enum CheckersConstants {

    NUM_ROWS(8),
    NUM_COLS(8),
    TOTAL_PIECES(12),
    TOTAL_SQUARES(64),
    PLAYABLE_SQUARES(32),
    EMPTY_SQUARES(0),
    PLAYER_ONE(1),
    PLAYER_TWO(2),
    WIN(10),
    LOSE(11),
    DOUBLE(12);

    private int value;
    private CheckersConstants(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}
