public class Bishop {
    private int row;
    private int col;
    private boolean isBlack;

    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) == false) {
            return false;
        }
        if (board.verifyDiagonal(row, col, endRow, endCol) == false) {
            return false;
        }
        return true;
    }
}
