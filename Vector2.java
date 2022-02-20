public class Vector2
{
    public int x;
    public int y;

    public Vector2( int x, int y )
    {
        this.x = x;
        this.y = y;
    }

    public static Vector2 add(Vector2 vecA, Vector2 vecB)
    {
        return new Vector2(vecA.x + vecB.x, vecA.y + vecB.y);
    }

    public double magnitude() {
        return Math.hypot( ( double ) x, ( double ) y );
    }

    public Vector2 negate() {
        return new Vector2( -x, -y );
    }

    public String toString() {
        return "Vector2< " + x + ", " + y + " >";
    }
}
