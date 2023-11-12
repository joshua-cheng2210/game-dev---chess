public class Rook {
    private int row;
    private int col;
    private boolean isBlack;
    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) == false) {
            return false;
        }
        if ((board.verifyHorizontal(row, col, endRow, endCol) == false && board.verifyVertical(row, col, endRow, endCol) == false)) {
            return false;
        }
        return true;
    }
}
