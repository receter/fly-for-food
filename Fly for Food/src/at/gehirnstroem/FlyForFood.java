package at.gehirnstroem;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FlyForFood extends JavaPlugin {
	
	public List<Player> playersInFlightMode = new ArrayList<Player>();
	public List<Location> locationsInFlightMode = new ArrayList<Location>();
	public List<Double> fineFoodLevelInFlightMode = new ArrayList<Double>();
	
    @Override
    public void onEnable(){
		getLogger().info("Fly For Food has been enabled.");
		//Am anfang abfragen wer im flug modus ist?
		this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new HungerMaker(this), 60L, 60L);
		this.getServer().getPluginManager().registerEvents(new FlyForFoodEventListener(this), this);
    }
 
    @Override
    public void onDisable() {
		getLogger().info("Fly For Food has been disabled!");
	}
}

/*
 * 
 *   dont spam the console
 *  more output in console when turned on  
 * 
 * */
