
public class Monster {
    int monsterPosX;
    int monsterPosY;
    char monster = 'M';

    public int getMonsterPosX() {
        return monsterPosX;
    }

    public int getMonsterPosY() {
        return monsterPosY;
    }

    public char getMonster() {
        return monster;
    }
    public void kill() {
        System.out.println("Game Over!" + '\u2620');
    }
}

