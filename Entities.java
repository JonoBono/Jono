/**
 * Created by mini9 on 1/19/14.
 */

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.entity.minecart.*;

public class Entities {
    private List<EntityInfo> entities = new ArrayList<EntityInfo>();

    public Entities()
    {
        add(Bat.class);
        add(Blaze.class);
        add(Boat.class);
        add(Cow.class);
        add(CaveSpider.class);
        add(Chicken.class);
        add(EnderDragon.class);
        add(Enderman.class);
        add(ExplosiveMinecart.class);
        add(Firework.class);
        add(Fireball.class);
        add(Ghast.class);
        add(Giant.class);
        add(Horse.class);
        add(IronGolem.class);
        add(MagmaCube.class);
        add(MushroomCow.class);
        add(Ocelot.class);
        add(PigZombie.class);
        add(Pig.class);
        add(Sheep.class);
        add(Skeleton.class);
        add(Spider.class);
        add(Silverfish.class);
        add(Slime.class);
        add(Snowman.class);
        add(Villager.class);
        add(Witch.class);
        add(Wither.class);
        add(WitherSkull.class);
        add(Wolf.class);
        add(Zombie.class);
    }

    private void add(Class<? extends Entity> ct)
    {
        entities.add(new EntityInfo(ct));
    }

    public String listAll()
    {
        StringBuilder s = new StringBuilder();

        for (EntityInfo item  : entities)
        {
            if (s.length() > 0)
            {
                s.append(", ");
            }
            s.append(item.Name);
        }

        return s.toString();
    }

    public Class<? extends Entity> lookup(String name)
    {
        for (EntityInfo possibleEntity : entities)
        {
            if (possibleEntity.Name.equalsIgnoreCase(name))
            {
                return possibleEntity.ClassType;
            }
        }

        return null;
    }
}

