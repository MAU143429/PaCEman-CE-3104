package Game;

public class Fruit {
    private int row,col,value;
    private char fruitType;

    public Fruit(char fruitType , int row, int col, int value) {
        this.col = col;
        this.row = row;
        this.fruitType = fruitType;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public char getFruitType() {
        return fruitType;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setFruitType(char fruitType) {
        this.fruitType = fruitType;
    }
}
