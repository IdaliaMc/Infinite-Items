package fr.ayato.infiniteitems.items;

import fr.ayato.infiniteitems.Main;
import fr.ayato.infiniteitems.utils.Spliter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CreateItem {

    // Plugin
    private static Main plugin;
    public CreateItem(Main main) {
        plugin = main;
    }

    /* Initialize an item with data of the configuration file
       Executed by the command */
    public static ItemStack itemToGive(Material material, String displayName, String loreFromConfig, String enchantments,  String levels, String hide) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta itemMeta = item.getItemMeta();

        // Text
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Spliter.strToList(Spliter.stringToSplit(loreFromConfig)));

        // Enchantments
        List<String> enchantList = Spliter.strToList(Spliter.stringToSplit(enchantments));

        // Enchantments Levels
        List<String> levelsList = Spliter.strToList(Spliter.stringToSplit(levels));

        // Add Enchantments on the item
        for (int i = 0; i < enchantList.size(); i++) {
            itemMeta.addEnchant(Enchantment.getByName(enchantList.get(i)), Integer.parseInt(levelsList.get(i)), true);
        }

        // If the option is enable, enchantments will be hide
        if (hide.contains("true")) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        itemMeta.spigot().setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }
}
