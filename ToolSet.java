import java.awt.Graphics2D;

public class ToolSet
{
    public static float deltaTime = 0f;
    public static void DrawMultilineText( Graphics2D graphics, String[] text, int x, int y )
    {
        final int LineHeightFinal = graphics.getFontMetrics().getHeight();
        for( String line : text ) {
            graphics.drawString( line, x, y );
            y += LineHeightFinal;
        }
    }
}
