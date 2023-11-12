public class Queen {
    private int row;
    private int col;
    private boolean isBlack;

    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) == false) {
            return false;
        }
        if (!(board.verifyHorizontal(row, col, endRow, endCol) == true || board.verifyVertical(row, col, endRow, endCol) == true || board.verifyDiagonal(row, col, endRow, endCol) == true)) {
            return false;
        }
        return true;
    }
}
