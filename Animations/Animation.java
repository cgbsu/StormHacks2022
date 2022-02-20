import java.util.ArrayList;

public abstract class Animation
{
    public static ArrayList< Animation > PlayingAnimations = new ArrayList< Animation >();
    public abstract String name();
    public abstract float speed();

    // to avoid bloating the paintComponent method
    public static void AnimIterate()
    {
        if( PlayingAnimations.size() == 0 )
            return;

        for( Animation anim : PlayingAnimations )
            anim.OnUpdate(ToolSet.deltaTime);
    }

    public void Play() {
        PlayingAnimations.add(this);
    }

    public void Pause() {
        PlayingAnimations.remove(this);
    }

    public abstract void OnUpdate(float deltaTime);
}