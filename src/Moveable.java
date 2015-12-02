
public class Moveable extends Piece{
    int weight;
    int speed;
    int acceleration;
    int health;

   public Moveable(int id, int x, int y, int z, int width, int height, int depth, int weight, int speed, int acceleration, int health){
        super(id, x, y, z, width, height, depth);
        this.weight = weight;
        this.speed = speed;
        this.acceleration = acceleration;
        this.health = health;
    }


}
