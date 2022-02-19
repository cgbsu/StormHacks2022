public abstract class Bone extends Transform
{
    public enum BodyVertical {
        TOP, 
        BOTTOM, 
        NONE
    }
    public enum BodyHorizontal {
        LEFT, 
        RIGHT, 
        NONE 
    }

    public Bone( Bone[] children, 
            BodyHorizontal horizontal, 
            BodyVertical vertical )
    {
        super( children );
        this.vertical = vertical;
        this.horizontal = horizontal;
    }
    public Bone( Bone[] children, BodyHorizontal horizontal ) {
        this( children, horizontal, BodyVertical.NONE );
    }
    public Bone( Bone[] children, BodyVertical vertical ) {
        this( children, BodyHorizontal.NONE, vertical );
    }
    public Bone( Bone[] children ) {
        this( children, BodyHorizontal.NONE, BodyVertical.NONE );
    }

    //Describe the bone
    BodyVertical vertical = BodyVertical.NONE;
    BodyHorizontal horizontal = BodyHorizontal.NONE;

    public String toString()
    {
        String fullTitle = "";
        if( vertical != BodyVertical.NONE )
            fullTitle = vertical.toString() + ".";
        if( horizontal != BodyHorizontal.NONE )
            fullTitle = fullTitle + horizontal.toString() + ".";
        return ( fullTitle + name() );
    }

    public static int leftRightScalar( BodyHorizontal horizontal ) {
        return ( ( horizontal == Bone.BodyHorizontal.LEFT ) ? -1 : 1 );
    }
}
