package core.control;

public class Statistics {

    private boolean gameOn;
    private int clickCount;

    public Statistics() {
        this.gameOn = true;
        this.clickCount = 0;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    public void increaseClickCount() {
        this.clickCount++;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void resetClickCount(){
        this.clickCount = 0;
    }
}
