
public class Player extends Moveable{
    int yaw;
    int pitch;
    int roll;
    String name;

   public Player(int id, String name, int x, int y, int z, int width, int height, int depth, int weight, int speed, int acceleration, int health, int yaw, int pitch, int roll){
        super(id, x, y, z, width, height, depth, weight, speed, acceleration, health);
        this.name = name;
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;

    }


}
