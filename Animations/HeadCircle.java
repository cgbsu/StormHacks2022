
public class HeadCircle extends Animation
{
    public static final float DefaultMaxTimeFinal = 2.0f;
    public String name() { return "HeadCircle"; }
    public float speed() { return 1f; }
    Vector2 defaultPosition;

    Transform head;
    public HeadCircle (Transform head)
    {
        this.head = head;
        this.defaultPosition = new Vector2( head.position.x, head.position.y );
    }

    float time = 0f;
    float frequency = 6f; // yeah I think I know math terms
    public void OnUpdate(float deltaTime)
    {
        time += deltaTime;
        float bobSpeed = 6f;

        int bobAmmount = (int)(Math.sin(time * frequency) * 20);
        int nodAmmount = (int)(Math.cos(time * frequency) * 20);
        head.position = new Vector2(defaultPosition.x + nodAmmount, defaultPosition.y + bobAmmount);

        if (time >= DefaultMaxTimeFinal)
        {
            this.Pause();
            time = 0f;

            head.position = defaultPosition;
        }
    }
}