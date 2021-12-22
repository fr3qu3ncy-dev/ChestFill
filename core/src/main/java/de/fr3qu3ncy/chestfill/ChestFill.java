package de.fr3qu3ncy.chestfill;

import de.fr3qu3ncy.bukkittools.util.ServerVersion;
import de.fr3qu3ncy.chestfill.command.FillCommand;
import de.fr3qu3ncy.chestfill.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class ChestFill extends JavaPlugin {

    private static VersionProvider versionProvider;

    private Lang lang;

    static {
        try {
            String packageName = ChestFill.class.getPackage().getName();
            ServerVersion serverVersion = ServerVersion.getServerVersion();
            String version = serverVersion.ordinal() <= ServerVersion.V_1_8_8.ordinal() ? "8" : "17";

            versionProvider = (VersionProvider) Class.forName(packageName + ".VersionProvider_" + version).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException exception) {
            Bukkit.getLogger().log(Level.SEVERE, "ChestFill doesn't support this server version!");
        }
    }

    @Override
    public void onEnable() {
        lang = new Lang(this);

        getCommand("fill").setExecutor(new FillCommand(lang));
    }

    public Lang getLang() {
        return lang;
    }

    public static VersionProvider getVersionProvider() {
        return versionProvider;
    }
}
