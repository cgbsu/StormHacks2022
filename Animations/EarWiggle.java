public class EarWiggle extends Animation
{

    public static final float DefaultMaxTimeFinal = 2.0f * ( float ) Math.PI;
    public static final float DefaultMaxAngleFinal = ( float ) Math.PI / 4.0f;
    float time, maxTime, speedScalar;
    public float originalAngle;
    Doggo.Ear left, right;

    public void initalize( Doggo.Ear left, Doggo.Ear right, float maxTime )
    {
        this.left = left;
        this.right = right;
        this.maxTime = maxTime;
        originalAngle = this.left.orientation;
        speedScalar = -1.0f;
        Play();
    }

    public EarWiggle( Doggo.Ear left, Doggo.Ear right, float maxTime ) {
        initalize( left, right, maxTime );
    }
    
    public EarWiggle( Doggo.Ear left, Doggo.Ear right ) {
        initalize( left, right, DefaultMaxTimeFinal );
    }

    public String name() {
        return "EarWiggle";
    }
    public float speed()
    {
        return 1.0f;
    }

    public void OnUpdate( float deltaTime )
    {
        time += deltaTime;
        //time = time % maxTime;
        // 
        // left.ThisTranslate( ( originalAngle - left.orientation ), speed() * deltaTime );
        //right.ThisTranslate( Math.sin(time * 3) * 4, deltaTime );
        right.orientation = ( float ) (Math.sin(time * 3.0f) * 0.2f) + 180;//( float ) (Math.PI + ( Math.PI / 2.0f ) );//270;
        left.orientation = -right.orientation + ( float ) Math.PI ;
        //if (time >= 0.5f)
        //    this.Pause();
    }
}