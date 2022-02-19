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

    //Describe the bone
    String name;
    BodyVertical vertical = BodyVertical.NONE;
    BodyHorizontal horizontal = BodyHorizontal.NONE;

    public String toString()
    {
        String fullTitle = "";
        if( vertical != BodyVertical.NONE )
            fullTitle = vertical.toString() + ".";
        if( horizontal != BodyHorizontal.NONE )
            fullTitle = fullTitle + horizontal.toString() + ".";
        return ( fullTitle + "name" );
    }
}
