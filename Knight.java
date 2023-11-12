public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack) == false) {
            return false;
        }
        if (board.verifyHorizontal(row, col, endRow, endCol) == true || board.verifyVertical(row, col, endRow, endCol) == true || board.verifyDiagonal(row, col, endRow, endCol) == true) {
            return false;
        }
        int row_diff;
        int col_diff;
        row_diff = this.row - endRow;
        col_diff = this.col - endCol;
        if (row_diff < -2 || row_diff > 2 || col_diff < -2 || col_diff > 2){
            return false;
        }
        return true;
    }
}
