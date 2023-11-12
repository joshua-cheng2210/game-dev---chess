public class King {
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifyAdjacent(row, col, endRow, endCol) == false) {
            return false;
        }
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) == false) {
            return false;
        }
        return true;
    }
}
