/**
 * Created by mini9 on 1/19/14.
 */
import org.bukkit.entity.*;

public class EntityInfo {

    public EntityInfo(Class<? extends Entity> classType)
    {
        ClassType = classType;
        Name = classType.getSimpleName();
    }

    public Class<? extends Entity> ClassType;
    public String Name;
}
