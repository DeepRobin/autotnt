package de.deeprobin.autotnt;

import de.deeprobin.autotnt.listener.TntListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The plugin class of the plugin AutoTnt.
 * Created by Robin L. on 11.01.2019.
 * (c) Copyright DeepRobin 2019 - All rights reserved.
 */
public class AutoTnt extends JavaPlugin {

    public final static String CONFIG_FUSE_TICKS = "autotnt.fuse-ticks";
    public final static String CONFIG_USE_PERMS = "autotnt.use-permissions";
    private final static String CONFIG_USE_METRICS = "autotnt.metrics";
    public final static String PERMISSION_USE = "autotnt.use";

    /**
     * This method is called on plugin enable.
     */
    @Override
    public void onEnable(){
        FileConfiguration config = this.getConfig();
        config.addDefault(CONFIG_FUSE_TICKS, 35);
        config.addDefault(CONFIG_USE_PERMS, true);
        config.addDefault(CONFIG_USE_METRICS, true);
        config.options().copyHeader(true);
        config.options().copyDefaults(true);
        saveConfig();
        this.getServer().getPluginManager().registerEvents(new TntListener(this), this);
        if(config.getBoolean(CONFIG_USE_METRICS)){
            Metrics metrics = new Metrics(this);
            metrics.addCustomChart(new Metrics.SimplePie("config_fuse_ticks", () -> String.valueOf(config.getInt(CONFIG_FUSE_TICKS))));
        }
    }

}
