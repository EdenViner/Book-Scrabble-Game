package test;


import java.util.Arrays;

public class Word {
    private Tile[] tiles;
    private int row, col;
    private boolean vertical;

    public Word(Tile[] tiles, int row, int col, boolean vertical) {
        this.tiles = tiles.clone();
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }

    public Tile[] getTiles() {
        return tiles.clone();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return row == word.row && col == word.col && vertical == word.vertical && Arrays.equals(tiles, word.tiles);
    }
}
