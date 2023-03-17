package br.com.celeste.celestebedwars;

import br.com.celeste.celestebedwars.teams.chest;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class CelesteBedWars extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
       System.out.println("[CelesteBedWars] O plugin foi iniciado.");
       registerEvents();

    }

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents((Listener) new chest(),this);
        this.getCommand("escolhertime").setExecutor(new chest());
    }

    @Override
    public void onDisable() {
        System.out.println("[CelesteBedWars] O plugin foi desligado.");
    }
}
