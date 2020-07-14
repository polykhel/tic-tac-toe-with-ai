package tictactoe;

import static tictactoe.GameState.*;

public class Board {
    private final Cell[][] gameBoard;
    private final int boardSize;
    private boolean rowMatch;
    private boolean colMatch;
    private boolean diagMatch;
    private boolean antiDiagMatch;
    private boolean full;
    private boolean xMove;

    Board(int boardSize) {
        this.boardSize = boardSize;
        this.gameBoard = new Cell[boardSize][boardSize];
        this.xMove = true;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.gameBoard[i][j] = Cell.EMPTY;
            }
        }
    }

    public boolean putCell(int x, int y) {
        int xIndex = boardSize - y;
        int yIndex = x - 1;
        if (isEmpty(xIndex, yIndex)) {
            Cell move = getNextMove();
            gameBoard[xIndex][yIndex] = move;
            updateConditions(xIndex, yIndex, move);
            xMove = !xMove;
            return true;
        }
        return false;
    }

    public void putCellAI(int x, int y, Cell cell) {
        gameBoard[x][y] = cell;
        updateConditions(x, y, cell);
        xMove = !xMove;
    }

    public boolean willWin(int x, int y, Cell cell) {
        int xIndex = boardSize - y;
        int yIndex = x - 1;
        if (isEmpty(xIndex, yIndex)) {
            Cell temp = gameBoard[xIndex][yIndex];
            gameBoard[xIndex][yIndex] = cell;
            updateConditions(xIndex, yIndex, cell);

            boolean won = colMatch || rowMatch || diagMatch || antiDiagMatch || full;
            gameBoard[xIndex][yIndex] = temp;

            return won;
        }
        return false;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public boolean isEmpty(int x, int y) {
        return gameBoard[x][y] == Cell.EMPTY;
    }

    private void updateConditions(int x, int y, Cell cell) {
        colMatch = true;
        rowMatch = true;
        diagMatch = true;
        antiDiagMatch = true;
        full = true;
        for (int i = 0; i < boardSize; i++) {
            if (colMatch) {
                colMatch = gameBoard[x][i] == cell;
            }

            if (rowMatch) {
                rowMatch = gameBoard[i][y] == cell;
            }

            if (diagMatch) {
                diagMatch = gameBoard[i][i] == cell;
            }

            if (antiDiagMatch) {
                antiDiagMatch = gameBoard[i][boardSize - i - 1] == cell;
            }

            for (int j = 0; j < boardSize && full; j++) {
                if (gameBoard[i][j] == Cell.EMPTY) {
                    full = false;
                    break;
                }
            }
        }
    }

    public GameState checkGameState() {
        GameState newGameState = NOT_FINISHED;
        if (colMatch || rowMatch || diagMatch || antiDiagMatch) {
            switch (getLastMove()) {
                case O:
                    newGameState = O_WIN;
                    break;
                case X:
                    newGameState = X_WIN;
                    break;
                default:
                    newGameState = INVALID;
                    break;
            }
        } else if (full) {
            newGameState = DRAW;
        }
        return newGameState;
    }

    public void setXMove(Cell currentMove) {
        xMove = currentMove != Cell.X;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------").append(System.lineSeparator());
        for (Cell[] row : gameBoard) {
            sb.append("| ");
            for (Cell column : row) {
                sb.append(column.getValue()).append(" ");
            }
            sb.append("|").append(System.lineSeparator());
        }
        sb.append("---------");
        return sb.toString();
    }

    public Cell getNextMove() {
        return xMove ? Cell.X : Cell.O;
    }

    public Cell getLastMove() {
        return xMove ? Cell.O : Cell.X;
    }
}
