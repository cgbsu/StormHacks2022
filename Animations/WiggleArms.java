public class WiggleArms extends Animation
{

    public static final float DefaultMaxTimeFinal = 2.0f;
    public static final float DefaultSpeedFinal = 10.0f;
    public static final boolean DefaultPayAttention = false;
    float time, maxTime, speedScalar;
    public float originalAngle;
    Doggo.TopLeg left, right;
    boolean payAttention;

    public void initalize( 
            Doggo.TopLeg left, 
            Doggo.TopLeg right, 
            boolean payAttention, 
            float maxTime, 
            float speedScalar 
        )
    {
        this.left = left;
        this.right = right;
        this.maxTime = maxTime;
        this.speedScalar = speedScalar;
        this.payAttention = payAttention;
        originalAngle = this.left.orientation;
        Play();
    }

    public void initalizeEarPositions()
    {
        /*this.left.position.x = ( -this.left.earLength / 2 ) - this.left.earRadius / 2;
        this.right.position.x = this.right.earRadius / 2;
        this.left.position.y = 0;
        this.right.position.y = this.right.earRadius / 2;
        this.left.center = new Vector2( this.left.earLength, 0 );
        this.right.center = new Vector2( this.right.earLength, 0 );*/
    }

    public void Play() {
        super.Play();
        initalizeEarPositions();
    }

    public void Pause()
    {
        super.Pause();
        // this.left.initalize();
        // this.right.initalize();
    }

    public WiggleArms( Doggo.TopLeg left, Doggo.TopLeg right, boolean payAttention, float maxTime, float speedScalar ) {
        initalize( left, right, payAttention, maxTime, speedScalar );
    }

    public WiggleArms( Doggo.TopLeg left, Doggo.TopLeg right, boolean payAttention, float maxTime ) {
        initalize( left, right, payAttention, maxTime, DefaultSpeedFinal );
    }

    public WiggleArms( Doggo.TopLeg left, Doggo.TopLeg right, boolean payAttention ) {
        initalize( left, right, payAttention, DefaultMaxTimeFinal, DefaultSpeedFinal );
    }

    public WiggleArms( Doggo.TopLeg left, Doggo.TopLeg right ) {
        initalize( left, right, DefaultPayAttention, DefaultMaxTimeFinal, DefaultSpeedFinal );
    }


    public String name() {
        return "WiggleArms";
    }
    public float speed() {
        return speedScalar;
    }

    public void OnUpdate( float deltaTime )
    {
        time += deltaTime;
        if( payAttention == true )
        {
            //Magic numbers :D//
            right.orientation = ( float ) ( Math.sin( speed() * time * 3.0f ) * 0.2f ) + 180;
            left.orientation = -right.orientation + ( float ) Math.PI ;
        }
        else {
            right.orientation = ( float ) ( Math.sin( speed() * time * 3.0f ) * 0.1f );
            left.orientation = -right.orientation;
        }
        if (time >= maxTime)
        {
            this.Pause();
            time = 0f;
        }
            
    }
}