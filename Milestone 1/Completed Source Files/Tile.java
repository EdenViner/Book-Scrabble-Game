package test;


import java.util.Objects;
import java.util.Random;

public class Tile {
    public final char letter;
    public final int score;

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public char getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    public static class Bag {
        //starting quantities
        private int[] Quantities = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        private Tile[] Tiles = new Tile[26];

        //method to get random tile
        public Tile getRand() {
            if (size() == 0)
                return null;
            Random rand = new Random();
            int target;
            do {
                target = rand.nextInt(26);
            } while (Quantities[target] == 0);
            Quantities[target] -= 1;
            return Tiles[target];
        }

        //method to get specific tile
        public Tile getTile(char letter) {
            int target = letter;
            target -= 65;
            if (target < 0 || target > 25)
                return null;
            if (Quantities[target] <= 0)
                return null;
            Quantities[target] -= 1;
            return Tiles[target];
        }

        //put the tile back in the bag
        public void put(Tile tile) {
            //for the case where the bag is full
            if (size() == 98)
                return;
            int target = tile.letter;
            target -= 65;
            Quantities[target] += 1;
        }

        //returns the number of tiles in the bag
        public int size() {
            int sum = 0;
            for (int i = 0; i < 26; i++)
                sum += Quantities[i];
            return sum;
        }

        //returns a copy of the quantities array
        public int[] getQuantities() {
            return Quantities.clone();
        }

        private Bag() {
            //sets all the letters and equivalent scores
            Tiles[0] = new Tile('A', 1);
            Tiles[1] = new Tile('B', 3);
            Tiles[2] = new Tile('C', 3);
            Tiles[3] = new Tile('D', 2);
            Tiles[4] = new Tile('E', 1);
            Tiles[5] = new Tile('F', 4);
            Tiles[6] = new Tile('G', 2);
            Tiles[7] = new Tile('H', 4);
            Tiles[8] = new Tile('I', 1);
            Tiles[9] = new Tile('J', 8);
            Tiles[10] = new Tile('K', 5);
            Tiles[11] = new Tile('L', 1);
            Tiles[12] = new Tile('M', 3);
            Tiles[13] = new Tile('N', 1);
            Tiles[14] = new Tile('O', 1);
            Tiles[15] = new Tile('P', 3);
            Tiles[16] = new Tile('Q', 10);
            Tiles[17] = new Tile('R', 1);
            Tiles[18] = new Tile('S', 1);
            Tiles[19] = new Tile('T', 1);
            Tiles[20] = new Tile('U', 1);
            Tiles[21] = new Tile('V', 4);
            Tiles[22] = new Tile('W', 4);
            Tiles[23] = new Tile('X', 8);
            Tiles[24] = new Tile('Y', 4);
            Tiles[25] = new Tile('Z', 10);
        }

        //singleton
        private static Bag bag = null;

        public static Bag getBag() {
            if (bag == null)
                bag = new Bag();
            return bag;
        }
    }
}
