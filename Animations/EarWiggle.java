public class EarWiggle extends Animation
{

    public static final float DefaultMaxTimeFinal = 2.0f;
    public static final float DefaultSpeedFinal = 10.0f;
    float time, maxTime, speedScalar;
    public float originalAngle;
    Doggo.Ear left, right;

    public void initalize( Doggo.Ear left, Doggo.Ear right, float maxTime, float speedScalar )
    {
        this.left = left;
        this.right = right;
        this.maxTime = maxTime;
        this.speedScalar = speedScalar;
        originalAngle = this.left.orientation;
        Play();
    }

    public void initalizeEarPositions()
    {
        this.left.position.x = ( -this.left.earLength / 2 ) - this.left.earRadius / 2;
        this.right.position.x = this.right.earRadius / 2;
        this.left.position.y = 0;
        this.right.position.y = this.right.earRadius / 2;
        this.left.center = new Vector2( this.left.earLength, 0 );
        this.right.center = new Vector2( this.right.earLength, 0 );
    }

    public void Play() {
        super.Play();
        initalizeEarPositions();
    }

    public void Pause()
    {
        super.Pause();
        this.left.initalize();
        this.right.initalize();
    }

    public EarWiggle( Doggo.Ear left, Doggo.Ear right, float maxTime, float speedScalar ) {
        initalize( left, right, maxTime, speedScalar );
    }
    
    public EarWiggle( Doggo.Ear left, Doggo.Ear right, float maxTime ) {
        initalize( left, right, maxTime, DefaultSpeedFinal );
    }

    public EarWiggle( Doggo.Ear left, Doggo.Ear right ) {
        initalize( left, right, DefaultMaxTimeFinal, DefaultSpeedFinal );
    }


    public String name() {
        return "EarWiggle";
    }
    public float speed() {
        return speedScalar;
    }

    public void OnUpdate( float deltaTime )
    {
        time += deltaTime;
        //Magic numbers :D//
        right.orientation = ( float ) ( Math.sin( speed() * time * 3.0f ) * 0.2f ) + 180;
        left.orientation = -right.orientation + ( float ) Math.PI ;
        if (time >= maxTime)
        {
            this.Pause();
            time = 0f;
        }
            
    }
}