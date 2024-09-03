package entities;

public abstract class Entity
{
    protected float x, y;//the location of the player
    protected int w, h; //the size of the player
    public Entity(float x, float y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
}
