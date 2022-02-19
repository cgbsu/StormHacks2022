import java.awt.Graphics2D;


//Exists for math purposes.//
public class PlainTransform extends Transform
{
    public final String NameFinal;
    public static final String DefaultNameFinal = "PlainTransform";
    public PlainTransform( String name ) {
        NameFinal = name;
    }
    public PlainTransform() {
        this( DefaultNameFinal );
    }
    public String name() {
        return NameFinal;
    }
    public void draw( Graphics2D g ) {
    }
}
