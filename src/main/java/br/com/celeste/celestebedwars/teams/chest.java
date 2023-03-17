package br.com.celeste.celestebedwars.teams;

import br.com.celeste.celestebedwars.CelesteBedWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class chest implements CommandExecutor, Listener{
    private List<String> redTeam = new ArrayList<String>();
    private List<String> blueTeam = new ArrayList<String>();
    private List<String> greenTeam = new ArrayList<String>();
    private List<String> yellowTeam = new ArrayList<String>();

    @EventHandler
    public void JoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        ItemStack wool = new ItemStack(Material.WOOL, 1);
        ItemMeta woolItemMetalMeta = wool.getItemMeta();

        woolItemMetalMeta.setDisplayName("§bSelecione seu time!");
        wool.setItemMeta(woolItemMetalMeta);
        player.getInventory().setItem(0, wool);
    }

    @EventHandler
    public void laInventario(InventoryClickEvent event){
        ItemStack item = event.getCurrentItem();
        if (item != null && item.getType() == Material.WOOL) {
            event.setCancelled(true);
        }
        }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        // Verifica se o item que esta sendo dropado e a lã
        if (event.getItemDrop().getItemStack().getType() == Material.WOOL) {
            // Cancela o evento
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if (item != null && item.getType() == Material.WOOL) {
            event.setCancelled(true);

        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;


        if (command.getName().equalsIgnoreCase("escolhertime")) {


            Player player = (Player) commandSender;
            Inventory inventario = Bukkit.createInventory(null, 27, "Selecione seu time:");

            ItemStack redWool = new ItemStack(Material.WOOL, 1, DyeColor.RED.getWoolData()); //Time vermelho
            ItemStack blueWool = new ItemStack(Material.WOOL, 1, DyeColor.BLUE.getWoolData()); //Time Azul
            ItemStack greenWool = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getWoolData()); //Time Verde
            ItemStack yellowWool = new ItemStack(Material.WOOL, 1, DyeColor.YELLOW.getWoolData()); //Time Amarelo

            ItemMeta redWoolMeta = redWool.getItemMeta();
            ItemMeta blueWoolMeta = blueWool.getItemMeta();
            ItemMeta greenWoolMeta = greenWool.getItemMeta();
            ItemMeta yellowWoolMeta = yellowWool.getItemMeta();

            redWoolMeta.setDisplayName("§cTime Vermelho");
            blueWoolMeta.setDisplayName("§9Time Azul");
            greenWoolMeta.setDisplayName("§aTime Verde");
            yellowWoolMeta.setDisplayName("§eTime Amarelo");


            List<String> lore = new ArrayList<String>();
            lore.add("§7Jogadores:");
            lore.add("");
            lore.add("§aClique para selecionar a equipe!");

            redWoolMeta.setLore(lore);
            blueWoolMeta.setLore(lore);
            greenWoolMeta.setLore(lore);
            yellowWoolMeta.setLore(lore);

            redWool.setItemMeta(redWoolMeta);
            blueWool.setItemMeta(blueWoolMeta);
            greenWool.setItemMeta(greenWoolMeta);
            yellowWool.setItemMeta(yellowWoolMeta);

            inventario.setItem(10, redWool);
            inventario.setItem(12, greenWool);
            inventario.setItem(14, blueWool);
            inventario.setItem(16, yellowWool);


            redWool.setItemMeta(redWoolMeta);
            blueWool.setItemMeta(blueWoolMeta);
            greenWool.setItemMeta(greenWoolMeta);
            yellowWool.setItemMeta(yellowWoolMeta);

    player.openInventory(inventario);




    }
        return false;
}



    @EventHandler
    public void teamRed(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventario = event.getInventory();
        ItemStack item = event.getCurrentItem();
        String playerName = player.getName();

        if (inventario == null || item == null || item.getType() != Material.WOOL || item.getDurability() != 14) {
            return;
        }

        if (redTeam.size() >= 4) {
            player.sendMessage("§cO time vermelho já está completo!");
            return;
        }

        if (redTeam.contains(player.getName())) {
            player.sendMessage("§cVocê já está no time vermelho!");
            return;
        }

        if (yellowTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            yellowTeam.remove(player.getName());
            updateYellowWool(item);
        }

        if (blueTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            blueTeam.remove(player.getName());
            updateBlueWool(item);
        }

        if (greenTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            greenTeam.remove(player.getName());
            updateGreenWool(item);
        }


        redTeam.add(player.getName());
        event.setCancelled(true);
        player.sendMessage("§fVocê entrou no time §c§lVERMELHO!");
        player.setPlayerListName(ChatColor.RED + playerName);


updateRedWool(item);
inventario.setItem(10, item);
player.getInventory().setItem(0, item);



        }


    @EventHandler
    public void teamBlue(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventario = event.getInventory();
        ItemStack item = event.getCurrentItem();
        String playerName = player.getName();

        if (inventario == null || item == null || item.getType() != Material.WOOL || item.getDurability() != 11) {
            return;
        }

        if (blueTeam.size() >= 4) {
            player.sendMessage("§9O time azul já está completo!");
            return;
        }

        if (blueTeam.contains(player.getName())) {
            player.sendMessage("§9Você já está no time azul!");
            return;
        }

        event.setCancelled(true);
        player.sendMessage("§fVocê entrou no time §9§lAZUL!");

        if (yellowTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            yellowTeam.remove(player.getName());
            updateYellowWool(item);
        }

        if (redTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            redTeam.remove(player.getName());
            updateRedWool(item);
        }

        if (greenTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            greenTeam.remove(player.getName());
            updateGreenWool(item);
        }

        inventario.setItem(14, item);

        blueTeam.add(player.getName());
        player.setPlayerListName(ChatColor.BLUE + playerName);
        player.getInventory().setItem(0, item);
        updateBlueWool(item);

    }
    @EventHandler
    public void teamGreen(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventario = event.getInventory();
        ItemStack item = event.getCurrentItem();
        String playerName = player.getName();


        if (inventario == null || item == null || item.getType() != Material.WOOL || item.getDurability() != 13) {
            return;
        }

        if (greenTeam.size() >= 4) {
            player.sendMessage("§aO time verde já está completo!");
            return;
        }

        if (greenTeam.contains(player.getName())) {
            player.sendMessage("§aVocê já está no time verde!");
            return;
        }

        if (redTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            redTeam.remove(player.getName());
            updateRedWool(item);
        }

        if (yellowTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            yellowTeam.remove(player.getName());
            updateYellowWool(item);
        }

        if (blueTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            blueTeam.remove(player.getName());
            updateBlueWool(item);

        }



        event.setCancelled(true);
        player.sendMessage("§fVocê entrou no time §a§lVERDE!");


        inventario.setItem(12, item);

        greenTeam.add(player.getName());
        player.setPlayerListName(ChatColor.GREEN + playerName);
        player.getInventory().setItem(0, item);
        updateGreenWool(item);

    }
    @EventHandler
    public void teamYellow(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventario = event.getInventory();
        ItemStack item = event.getCurrentItem();
        String playerName = player.getName();




        if (inventario == null || item == null || item.getType() != Material.WOOL || item.getDurability() != 4) {
            return;
        }

        if (yellowTeam.size() >= 4) {
            player.sendMessage("§eO time amarelo já está completo!");
            return;
        }

        if (yellowTeam.contains(player.getName())) {
            player.sendMessage("§eVocê já está no time amarelo!");
            return;
        }

        if (redTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            redTeam.remove(player.getName());
            updateRedWool(item);
        }

        if (greenTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            greenTeam.remove(player.getName());
            updateGreenWool(item);
        }

        if (blueTeam.contains(player.getName())) {
            // Remove o jogador da redTeam
            blueTeam.remove(player.getName());
            updateBlueWool(item);
        }


        event.setCancelled(true);
        player.sendMessage("§fVocê entrou no time §e§lAMARELO!");
        player.setPlayerListName(ChatColor.YELLOW + playerName);


        yellowTeam.add(player.getName());

        updateYellowWool(item);
        inventario.setItem(16, item);
        player.getInventory().setItem(0, item);


    }

    private void updateRedWool(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        int members = redTeam.size();
        String name = "§cTime Vermelho (" + members + ")";
        List<String> lore = new ArrayList<String>();
        lore.add("§7Jogadores:");
        lore.add("");

        for (String player : redTeam) {
            lore.add(ChatColor.RED + "- " + player);
        }

        lore.add("");
        lore.add("§aEquipe selecionada!!");

        meta.setLore(lore);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }

    private void updateBlueWool(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        int members = blueTeam.size();
        String name = "§9Time Azul (" + members + ")";
        List<String> lore = new ArrayList<String>();
        lore.add("§7Jogadores:");
        lore.add("");

        for (String player : blueTeam) {
            lore.add(ChatColor.RED + "- " + player);
        }

        lore.add("");
        lore.add("§aEquipe selecionada!!");

        meta.setLore(lore);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }
    private void updateYellowWool(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        int members = yellowTeam.size();
        String name = "§eTime Amarelo (" + members + ")";
        List<String> lore = new ArrayList<String>();
        lore.add("§7Jogadores:");

        for (String player : yellowTeam) {
            lore.add(ChatColor.RED + "- " + player);
        }

        lore.add("§aEquipe selecionada!!");

        meta.setLore(lore);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }
    private void updateGreenWool(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        int members = greenTeam.size();
        String name = "§aTime Verde (" + 1 + members + ")";
        List<String> lore = new ArrayList<String>();
        lore.add("§7Jogadores:");

        for (String player : greenTeam) {
            lore.add(ChatColor.RED + "- " + player);
        }

        lore.add("§aEquipe selecionada!!");

        meta.setLore(lore);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }

    @EventHandler
    public void woolClick(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if(event.getMaterial() == Material.WOOL){
                Bukkit.getServer().dispatchCommand(player, "escolhertime");
            }

        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        if (redTeam.contains(playerName)) {
            redTeam.remove(playerName);
        }

        if (blueTeam.contains(playerName)) {
            blueTeam.remove(playerName);
        }

        if (greenTeam.contains(playerName)) {
            greenTeam.remove(playerName);
        }

        if (yellowTeam.contains(playerName)) {
            yellowTeam.remove(playerName);
        }


    }


}



