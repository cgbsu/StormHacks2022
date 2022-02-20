public class EarWiggle extends Animation
{

    public static final float DefaultMaxTimeFinal = 4.0f * ( float ) Math.PI;
    float time, maxTime;
    Doggo.Ear left, right;

    public void initalize( Doggo.Ear left, Doggo.Ear right, float maxTime )
    {
        this.left = left;
        this.right = right;
        this.maxTime = maxTime;
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
    public float speed() {
        return ( float ) -Math.sin( time );
    }

    public void OnUpdate( float deltaTime )
    {
        time += deltaTime;
        time = time % maxTime;
        left.ThisTranslate( speed(), deltaTime );
        right.ThisTranslate( -speed(), deltaTime );
        
        //if (time >= 0.5f)
        //    this.Pause();
    }
}