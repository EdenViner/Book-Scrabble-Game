package test;


import java.util.ArrayList;

public class Board {
    private static Tile[][] board = new Tile[15][15];
    private String[][] bonus = new String[15][15];
    private Word[] completedWords = new Word[15];
    private int wordsCount = 0;

    //singleton
    private static test.Board b = null;

    public static test.Board getBoard() {
        if (b == null)
            b = new test.Board();
        return b;
    }

    private Board() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++)
                board[i][j] = null;
        }
        //setting all the bonus tiles
        bonus[0][0] = "Triple Word";
        bonus[0][7] = "Triple Word";
        bonus[0][14] = "Triple Word";
        bonus[7][0] = "Triple Word";
        bonus[7][14] = "Triple Word";
        bonus[14][0] = "Triple Word";
        bonus[14][7] = "Triple Word";
        bonus[14][14] = "Triple Word";
        bonus[1][1] = "Double Word";
        bonus[1][13] = "Double Word";
        bonus[2][2] = "Double Word";
        bonus[2][12] = "Double Word";
        bonus[3][3] = "Double Word";
        bonus[3][11] = "Double Word";
        bonus[4][4] = "Double Word";
        bonus[4][10] = "Double Word";
        bonus[7][7] = "Double Word";
        bonus[10][4] = "Double Word";
        bonus[10][10] = "Double Word";
        bonus[11][3] = "Double Word";
        bonus[11][11] = "Double Word";
        bonus[12][2] = "Double Word";
        bonus[12][12] = "Double Word";
        bonus[13][1] = "Double Word";
        bonus[13][13] = "Double Word";
        bonus[1][5] = "Triple Letter";
        bonus[1][9] = "Triple Letter";
        bonus[5][1] = "Triple Letter";
        bonus[5][5] = "Triple Letter";
        bonus[5][9] = "Triple Letter";
        bonus[5][13] = "Triple Letter";
        bonus[9][1] = "Triple Letter";
        bonus[9][5] = "Triple Letter";
        bonus[9][9] = "Triple Letter";
        bonus[9][13] = "Triple Letter";
        bonus[13][5] = "Triple Letter";
        bonus[13][9] = "Triple Letter";
        bonus[0][3] = "Double Letter";
        bonus[0][11] = "Double Letter";
        bonus[2][6] = "Double Letter";
        bonus[2][8] = "Double Letter";
        bonus[3][0] = "Double Letter";
        bonus[3][7] = "Double Letter";
        bonus[3][14] = "Double Letter";
        bonus[6][2] = "Double Letter";
        bonus[6][6] = "Double Letter";
        bonus[6][8] = "Double Letter";
        bonus[6][12] = "Double Letter";
        bonus[7][3] = "Double Letter";
        bonus[7][11] = "Double Letter";
        bonus[8][2] = "Double Letter";
        bonus[8][6] = "Double Letter";
        bonus[8][8] = "Double Letter";
        bonus[8][12] = "Double Letter";
        bonus[11][0] = "Double Letter";
        bonus[11][7] = "Double Letter";
        bonus[11][14] = "Double Letter";
        bonus[12][6] = "Double Letter";
        bonus[12][8] = "Double Letter";
        bonus[14][3] = "Double Letter";
        bonus[14][11] = "Double Letter";
    }

    //returns a copy of board
    public Tile[][] getTiles() {
        return board.clone();
    }

    //receives a word and decides if the word placement is valid
    public boolean boardLegal(Word w){
        int col = w.getCol();
        int row = w.getRow();
        boolean flag = false;
        boolean vertical = w.isVertical();
        Tile[] tiles = w.getTiles();
        if(col<0 || col>14 || row<0 || row>14){
            return false;
        }
        if(vertical==true){
            if(row + tiles.length > 14){ //check if the word is out of bounds
                return false;
            }
            if(board[7][7]==null){ //check if this is the first word
                if(col!=7) //check if the word is in the middle
                    return false;
                if(row+tiles.length<7 || row>7)
                    return false;
            }
            else{
                for(int i = 0; i < tiles.length; i++){
                    if(board[row+i][col]!=null || (col+1<15 && board[row+i][col+1]!=null) ||(col-1>-1 && board[row+i][col-1]!=null) || (row+i+1 < 15 && board[row+i+1][col]!=null) || (row+i-1>-1 && board[row+i-1][col]!=null))
                        flag = true;
                    if(board[row+i][col]!=null && board[row+i][col].getLetter()!=tiles[i].getLetter())
                        return false;
                }
                if(!flag){
                    return false;
                }
            }
        }
        else if(vertical==false){
            if(col + tiles.length > 14){ //check if the word is out of bounds
                return false;
            }
            if( board[7][7]==null){ //check if this is the first word
                if(row!=7) //check if the word is in the middle
                    return false;
                if(col+tiles.length<7 || col>7)
                    return false;
            }
            else{
                for(int i = 0; i < tiles.length; i++){
                    if((board[row][col+i]!=null || (row+1<15 && board[row+1][col+i]!=null) ||(row-1>-1 && board[row-1][col+i]!=null) || (col+i+1 < 15 && board[row][col+i+1]!=null) || (col+i-1>-1 && board[row][col+i-1]!=null)))
                        flag = true;
                    if(board[row][col+i]!=null && board[row][col+i].getLetter()!=tiles[i].getLetter())
                        return false;
                }
                if(!flag){
                    return false;
                }
            }
        }
        return true;
    }

    //checking if the word exists in the dictionary
    public boolean dictionaryLegal(Word newWord) {
        //TODO implement in future
        return true;
    }

    //receives a word and returns an array list with all the new words that is made using the new word
    public ArrayList<Word> getWords(Word w){
        ArrayList<Word> words = new ArrayList<Word>();
        int col = w.getCol();
        int row = w.getRow();
        int start;
        int finish;
        boolean vertical = w.isVertical();
        Tile[] tiles = w.getTiles();
        ArrayList<Tile> temp = new ArrayList<Tile>();
        words.add(w);
        for(int i = 0; i < tiles.length; i++){
            if(vertical==true){
                start = col;
                finish = col;
                while(start-1>-1 && board[row+i][start-1]!=null){
                    start--;
                }
                while(finish+1<15 && board[row+i][finish+1]!=null){
                    finish++;
                }
                if(start != col|| finish != col){
                    for(int j = start; j <= finish; j++){
                        temp.add(board[row+i][j]);
                    }
                    Word word = new Word(temp.toArray(new Tile[temp.size()]), row + i, start, false);
                    for (int j = 0; j < wordsCount; j++) {
                        if (completedWords[j].equals(word)) {
                            word = null;
                            break;
                        }
                    }
                    if (word != null) {
                        words.add(word);
                    }
                }

            }
            else if(vertical==false){
                start = row;
                finish = row;
                while(start-1>-1 && board[start-1][col+i]!=null){
                    start--;
                }
                while(finish+1<15 && board[finish+1][col+i]!=null){
                    finish++;
                }
                if(start != row|| finish != row){
                    temp.clear();
                    for(int j = start; j <= finish; j++){
                        temp.add(board[j][col+i]);
                    }
                    if(temp.size()>0){
                        Word word = new Word(temp.toArray(new Tile[temp.size()]), start, col+i, true);
                        for(int j = 0; j < wordsCount; j++){
                            if(completedWords[j].equals(word)){
                                word = null;
                                break;
                            }
                        }
                        if(word!=null){
                            words.add(word);
                        }
                    }
                }

            }
        }
        return words;
    }

    //receives a new word and returns the total score of the word including bonuses
    public int getScore(Word w){
        if(wordsCount>0){
            bonus[7][7]=null;
        }
        int score = 0;
        int col = w.getCol();
        int row = w.getRow();
        boolean vertical = w.isVertical();
        Tile[] tiles = w.getTiles();
        for(int i = 0; i < tiles.length; i++){
            if(vertical==true){
                if (bonus[row+i][col] == null || bonus[row + i][col].equals("Double Word")|| bonus[row + i][col].equals("Triple Word"))
                    score += tiles[i].getScore();
                else if(bonus[row+i][col].equals("Triple Letter")){
                    score += tiles[i].getScore()*3;
                }
                else if(bonus[row+i][col].equals("Double Letter")){
                    score += tiles[i].getScore()*2;
                }
            }
            else  if(vertical==false){
                if (bonus[row][col+i]==null || bonus[row][col+i].equals("Double Word") || bonus[row][col + i].equals("Triple Word"))
                    score += tiles[i].getScore();
                else if(bonus[row][col+i].equals("Triple Letter")){
                    score += tiles[i].getScore()*3;
                }
                else if(bonus[row][col+i].equals("Double Letter")){
                    score += tiles[i].getScore()*2;
                }

            }
        }
        for(int i = 0; i < tiles.length; i++){
            if(vertical==true){
                if(bonus[row+i][col]==null)
                    continue;
                else if(bonus[row+i][col].equals("Double Word")){
                    score *= 2;
                }
                else if(bonus[row+i][col].equals("Triple Word")){
                    score *= 3;
                }
            }
            else  if(vertical==false){
                if (bonus[row][col+i]==null)
                    continue;
                else if(bonus[row][col+i].equals("Double Word")){
                    score *= 2;
                }
                else if(bonus[row][col+i].equals("Triple Word")){
                    score *= 3;
                }
            }
        }
        return score;
    }

    //tries to place the given word in the board, does all the validations for it and returns the total score the placement will produce
    public int tryPlaceWord(Word w){
        int score = 0;
        int wCol = w.getCol();
        int wRow = w.getRow();
        boolean wVertical = w.isVertical();
        Tile[] wTiles = w.getTiles();
        for(int i = 0 ; i<wTiles.length; i++){
            if(wVertical){
                if(wTiles[i]==null){
                    wTiles[i] = board[wRow+i][wCol];
                }
            }
            else{
                if(wTiles[i]==null){
                    wTiles[i] = board[wRow][wCol+i];
                }
            }
        }
        w.setTiles(wTiles);
        if(boardLegal(w) && dictionaryLegal(w)){
            this.placeWord(w);
            ArrayList<Word> words = getWords(w);
            for(int i = 0; i < words.size(); i++){
                if(dictionaryLegal(words.get(i))){
                    score += getScore(words.get(i));
                }
                else{
                    return 0;
                }
            }
            for(int i = 0; i < words.size(); i++){
                completedWords[wordsCount] = words.get(i);
                wordsCount++;
                this.placeWord(words.get(i));
            }
            return score;
        }
        else{
            return -1;
        }
    }

    public void placeWord(Word newWord) {
        Tile[] tiles = newWord.getTiles();
        boolean vertical = newWord.isVertical();
        int length = newWord.getTiles().length;
        int col = newWord.getCol();
        int row = newWord.getRow();
        for(int i = 0; i < length; i++){
            if(vertical==true)
                board[row+i][col] = tiles[i];
            if (vertical==false)
                board[row][col+i] = tiles[i];
        }
    }
}