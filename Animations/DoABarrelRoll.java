

public class DoABarrelRoll extends Animation
{
    public String name() { return "Flip"; } // eyo do a flip
    public float speed() { return 1f; }

    Transform doggoObj;
    float originalOrientation;
    public DoABarrelRoll(Transform doggo)
    {
        this.doggoObj = doggo;
        originalOrientation = doggo.orientation;
    }

    float angle = 0f;
    
    public void OnUpdate(float deltaTime)
    {
        angle += deltaTime * 4;
        doggoObj.orientation = angle;
        System.out.println(angle);
        if (angle >= 6.3f)
        {
            angle = 0f;
            doggoObj.orientation = originalOrientation;
            this.Pause();
        }
    }
}
