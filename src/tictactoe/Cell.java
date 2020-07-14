package tictactoe;

public enum Cell {
    X('X') {
        @Override
        public Cell opposite() {
            return O;
        }
    }, O('O') {
        @Override
        public Cell opposite() {
            return X;
        }
    }, EMPTY(' ') {
        @Override
        public Cell opposite() {
            return EMPTY;
        }
    };

    private final char value;

    Cell(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public abstract Cell opposite();
}
