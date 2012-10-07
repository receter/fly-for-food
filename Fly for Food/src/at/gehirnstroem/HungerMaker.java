package at.gehirnstroem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class HungerMaker implements Runnable {
	
	FlyForFood fff = null;
	
    public HungerMaker(FlyForFood fff){
    	this.fff = fff;
    }
    
    @Override
    public void run() {

    	for(int i=0; i<fff.playersInFlightMode.size(); i++)
    	{
    		Player player = fff.playersInFlightMode.get(i);
    		if(player.isOnline())
    		{
	    		if(player.getFoodLevel()<=0)
	    		{
	    			player.setAllowFlight(false);
	    			if(fff.playersInFlightMode.contains(player))
	    			{
	    				fff.locationsInFlightMode.remove(fff.playersInFlightMode.indexOf(player));
	    				fff.fineFoodLevelInFlightMode.remove(fff.playersInFlightMode.indexOf(player));
	    				fff.playersInFlightMode.remove(player);
	    			}
	    			player.sendMessage("You are too hungry to fly, you had to fold your wings.");
	    		}
	    		else
	    		{
    				String playerName = player.getName();
	    			System.out.println("Looped "+playerName+".");
	    			if(player.isFlying())
	    			{
	    				int playerIndex = fff.playersInFlightMode.indexOf(player);
	    				Location lastLocation = fff.locationsInFlightMode.get(playerIndex);
	    				Location currentLoc = player.getEyeLocation().clone();
	    				
	    				double newFoodLevelDouble;
	    				
	    				if(player.hasPermission("flyforfood.freeflight"))
	    				{
	    					newFoodLevelDouble = fff.fineFoodLevelInFlightMode.get(playerIndex);
	    				}
	    				else
	    				{
	    					newFoodLevelDouble = fff.fineFoodLevelInFlightMode.get(playerIndex)-(lastLocation.distance(currentLoc)/40);
	    				}
	    				
	    				//Calc and set new Food Level
	    				fff.fineFoodLevelInFlightMode.set(playerIndex, newFoodLevelDouble);
	    				
	    				player.setFoodLevel((int) newFoodLevelDouble);
	    				
	    				System.out.println("Flying costed "+playerName+" "+(lastLocation.distance(currentLoc)/40)+" Food.");
	    				//Update Location
	    				fff.locationsInFlightMode.set(fff.playersInFlightMode.indexOf(player), currentLoc);
	    				
	    			}
	    			else if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.EAST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.EAST_NORTH_EAST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.EAST_SOUTH_EAST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.NORTH).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.NORTH_EAST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.NORTH_NORTH_EAST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.NORTH_NORTH_WEST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.NORTH_WEST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.SOUTH).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.SOUTH_EAST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.SOUTH_SOUTH_EAST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.SOUTH_SOUTH_WEST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.SOUTH_WEST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.WEST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.WEST_NORTH_WEST).getType() != Material.AIR
	    					||player.getLocation().getBlock().getRelative(BlockFace.WEST_SOUTH_WEST).getType() != Material.AIR)
	    			{
		    			player.setAllowFlight(false);
		    			if(fff.playersInFlightMode.contains(player))
		    			{
		    				fff.locationsInFlightMode.remove(fff.playersInFlightMode.indexOf(player));
		    				fff.fineFoodLevelInFlightMode.remove(fff.playersInFlightMode.indexOf(player));
		    				fff.playersInFlightMode.remove(player);
		    			}
		    			player.sendMessage("You landed.");
	    			}
	    		}
    		}
    		else
    		{
    			if(fff.playersInFlightMode.contains(player))
    			{
    				fff.locationsInFlightMode.remove(fff.playersInFlightMode.indexOf(player));
    				fff.fineFoodLevelInFlightMode.remove(fff.playersInFlightMode.indexOf(player));
    				fff.playersInFlightMode.remove(player);
    			}
    		}
    		
    	}
    }
}
