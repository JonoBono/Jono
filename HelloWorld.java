/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class HelloWorld extends JavaPlugin { 
  public static Logger log = Logger.getLogger("Minecraft");
  public void onLoad() {
    log.info("[JonoCraft] Loaded.");
  }
  public void onEnable() {
    log.info("[JonoCraft] Starting up.");
  }
  public void onDisable() {
    log.info("[JonoCraft] Stopping.");
  }

  public boolean onCommand(CommandSender sender, Command command,
		     String commandLabel, String[] args) {

    if (sender instanceof Player)
    {
        Player player = (Player)sender;

        return onPlayerCommand(player, command, commandLabel.toLowerCase(), args);
    }

    return false;
  }




  private boolean onPlayerCommand(Player player, Command command,
                                    String commandLabel, String[] args)
  {
      if (commandLabel.equals("info"))
      {
          return onInfo();
      }
      if (commandLabel.equals("sky"))
      {
          return onSky(player);
      }
      if (commandLabel.equals("bomb"))
      {
          return onBomb(player, args);
      }
      if (commandLabel.equals("ride"))
      {
          return onRide(player, args);
      }
      return false;
  }

  private void Broadcast(String message)
  {
      String msg = "[Server] " + message;
      getServer().broadcastMessage(msg);
  }

  private boolean onInfo()
  {
      Broadcast(knownEntities.listAll());
      return true;
  }

    private boolean onSky(Player player)
    {
        Location location = player.getLocation();
        location.setY( location.getY() + 50 );
        player.teleport(location);
        return true;
    }

    private boolean onRide(Player player, String[] args)
    {
        return ride(player, DecideEntity(args, Bat.class));
    }

    private boolean ride(Player player, Class<? extends Entity> ct)
    {
        Location location = player.getLocation();
        location.setY( location.getY() + 5 );
        player.getWorld().spawn(location, ct).setPassenger(player);
        return true;
    }

    private boolean onBomb(Player player, String[] args)
    {
        return bomb(player, DecideEntity(args, Cow.class));
    }

    private static Entities knownEntities = new Entities();

    private Class<? extends Entity> DecideEntity(String[] args,
                                                 Class<? extends Entity> defaultClass)
    {
        if (args.length > 0)
        {
            Class<? extends Entity> foundClass = knownEntities.lookup(args[0]);
            if (foundClass != null)
            {
                return foundClass;
            }
        }
        return defaultClass;
    }

    private boolean bomb(Player player, Class<? extends Entity> ct)
    {
        bomb(player, ct, 1, 1);

        bomb(player, ct, 1, -1);
        bomb(player, ct, -1, -1);
        bomb(player, ct, -1, 1);

        return true;
    }

    private static int HowFarAway = 3;

    private static int HowHigh = 10;

    private void bomb(Player player, Class<? extends Entity> ct, int moreX, int moreZ)
    {
        Location location = player.getLocation();
        location.setY( location.getY() + HowHigh );

        double x=location.getX();

        double z=location.getZ();

        location.setX(x+(moreX * HowFarAway));
        location.setZ(z+(moreZ * HowFarAway));

        player.getWorld().spawn(location, ct);
    }
}
