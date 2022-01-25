public class Walls {
    private int wallPosX;
    private int wallPosY;
    final char BLOCK = '\u2588';

    public Walls(int wallPosX, int wallPosY) {
        this.wallPosX = wallPosX;
        this.wallPosY = wallPosY;
    }

    public int getWallPosX() {
        return wallPosX;
    }

    public int getWallPosY() {
        return wallPosY;
    }
}
