package de.deeprobin.autotnt.listener;

import de.deeprobin.autotnt.AutoTnt;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by Robin L. on 11.01.2019.
 * (c) Copyright DeepRobin 2019 - All rights reserved.
 */
public class TntListener implements Listener {
    private AutoTnt plugin;

    public TntListener(AutoTnt plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        Player player = event.getPlayer();
        if (Material.TNT.equals(block.getType())) {
            if (this.plugin.getConfig().getBoolean(AutoTnt.CONFIG_USE_PERMS) && !player.hasPermission(AutoTnt.PERMISSION_USE))
                return;

            block.setType(Material.AIR);
            Location location = event.getBlock().getLocation();
            location.add(0.5, 0, 0.5);
            TNTPrimed tnt = event.getBlock().getWorld().spawn(location, TNTPrimed.class);
            tnt.setFuseTicks(this.plugin.getConfig().getInt(AutoTnt.CONFIG_FUSE_TICKS));
        }
    }

}
