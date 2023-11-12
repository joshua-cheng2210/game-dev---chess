public class Board {

    // Instance variables
    private Piece[][] board;
    private Board b;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];

    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece currPiece = getPiece(startRow, startCol);
        if (currPiece == null){
            return false;
        }
        if (currPiece.isMoveLegal(this, endRow,endCol)) {
            setPiece(endRow, endCol, currPiece);
            currPiece.setPosition(endRow, endCol);
            setPiece(startRow, startCol, null);
            return true;
        }
        else {
            return false;
        }
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int kingCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece currPiece = board[i][j];
                if(currPiece != null) {
                    if (currPiece.getCharacter() == '\u265a' || currPiece.getCharacter() == '\u2654') {
                        kingCount += 1;
                    }
                }
            }
        }
        if (kingCount == 2) {
            return false;
        }

        return true;
    }

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String b = "";
        b = "\u3000\t \uff10 \uff11 \uff12 \uff13 \uff14 \uff15 \uff16 \uff17";
        b += "\n";

        for (int row = 0; row < 8; row++) {
            b += row + "\t";
            for (int col = 0; col < 8; col++) {
                b += "|";
                if (board[row][col] == null) {
                    b += "\u3000";
                }
                else {
                    b += board[row][col];
                }
            }
            b += "|\n";
        }
        return b;
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board [i][j] = null;

            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow >= 8 || startCol >= 8 || endRow >= 8 || endCol >= 8 || startRow < 0 || startCol < 0 || endRow < 0 || endCol <0) {
            return false;
        }
        if (getPiece(startRow, startCol) == null) {
            return false;
        }
        if (getPiece(startRow, startCol).getIsBlack() != isBlack) {
            return false;
        }
        if (getPiece(endRow, endCol) != null && getPiece(endRow,endCol).getIsBlack() == isBlack){
            return false;
        }
        return true;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1) {
            return true;
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        // Verifies the move occurs on the same horizontal
        if (startRow != endRow) {
            return false;
        }
        // Verifies there are no pieces between start and end position
        int minCol = Math.min(startCol, endCol);
        int maxCol = Math.max(startCol, endCol);
        for (int col = minCol + 1; col < maxCol; col++) {
            if (board[startRow][col] != null) {
                return false;
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        // Verifies the move occurs on the same horizontal
        if (startCol != endCol) {
            return false;
        }
        // Verifies there are no pieces between start and end position
        int minRow = Math.min(startRow, endRow);
        int maxRow = Math.max(startRow, endRow);
        for (int row = minRow + 1; row < maxRow; row++) {
            if (board[row][startCol] != null) {
                return false;
            }
        }
        return true;
    }


    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);
        // Verifies the move is diagonal
        if (rowDiff != colDiff) {
            return false;
        }
        // Verifies the diagonal path is clear
        int rowDir = (endRow - startRow > 0) ? 1 : -1; // 1 if moving up, -1 if moving down
        int colDir = (endCol - startCol > 0) ? 1 : -1; // 1 if moving up, -1 if moving down
        for (int i = 1; i < rowDiff; i++) {
            int row = startRow + i * rowDir;
            int col = startCol + i * colDir;
            if (board[row][col] != null) {
                return false;
            }
        }
        return true;
    }
}