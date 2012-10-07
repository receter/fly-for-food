package at.gehirnstroem;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class FlyForFoodEventListener implements Listener {

	FlyForFood fff = null;
	
    public FlyForFoodEventListener(FlyForFood fff){
    	this.fff = fff;
    }
    
	
	@EventHandler(priority=EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		Location loc = player.getEyeLocation().clone();
		int foodLevel = player.getFoodLevel();
		
        if(player.getItemInHand().getType() == Material.FEATHER
           && (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) ){

            //String playerName = player.getName();
            
    		int currentFoodLevel = player.getFoodLevel();
    		//boolean flightAllowed = player.getAllowFlight();
    		
    		if(currentFoodLevel > 0 && player.hasPermission("flyforfood.fly"))
    		{
    			if(!fff.playersInFlightMode.contains(player))
    			{
    				fff.playersInFlightMode.add(player);
    				fff.locationsInFlightMode.add(fff.playersInFlightMode.indexOf(player), loc);
    				fff.fineFoodLevelInFlightMode.add(fff.playersInFlightMode.indexOf(player), (double) foodLevel);
    			}
    			
    			player.setAllowFlight(true);
    			player.setFlying(true);
    			player.sendMessage("You spread your wings, have fun and don't forget to eat!");
    		}
    		else if (currentFoodLevel <= 0)
    		{
    			player.setAllowFlight(false);
    			if(fff.playersInFlightMode.contains(player))
    			{
    				fff.locationsInFlightMode.remove(fff.playersInFlightMode.indexOf(player));
    				fff.fineFoodLevelInFlightMode.remove(fff.playersInFlightMode.indexOf(player));
    				fff.playersInFlightMode.remove(player);
    			}
    			player.sendMessage("You are too hungry to fly!");
    		}
        }
        
    }
	
    @EventHandler(priority=EventPriority.HIGH)
    public void onPlayerLogin(PlayerLoginEvent event){
        System.out.println("hui");
    }

}
