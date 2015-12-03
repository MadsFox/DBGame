
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

    // dependent on the players acceleration
    public void increasePosition(int id, int x, int y, int z, int speed){
        if(x!=0){
            moveRight();
            moveLight();
        } else if(y != 0){

        }
    }

    public void moveRight(int x){
        x++;
    }
    public void moveLight(int x){
        x--;
    }

    public void moveForward(int y){
        y--;
    }

    public void moveBack(int y){
        y++;
    }


}
