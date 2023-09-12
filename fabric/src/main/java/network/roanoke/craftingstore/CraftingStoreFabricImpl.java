package network.roanoke.craftingstore;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.craftingstore.core.CraftingStorePlugin;
import net.craftingstore.core.PluginConfiguration;
import net.craftingstore.core.logging.CraftingStoreLogger;
import net.craftingstore.core.logging.impl.JavaLogger;
import net.craftingstore.core.models.donation.Donation;
import network.roanoke.craftingstore.utils.MultiThreading;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CraftingStoreFabricImpl implements CraftingStorePlugin {

    private CraftingStoreFabric fabricPlugin;
    private JavaLogger logger;
    private PluginConfiguration pluginConfiguration;

    CraftingStoreFabricImpl(CraftingStoreFabric fabricPlugin) {
        this.fabricPlugin = fabricPlugin;
        this.logger = new JavaLogger(Logger.getLogger("CraftingStore"));
        this.pluginConfiguration = new FabricPluginConfiguration(fabricPlugin);
    }

    @Override
    public boolean executeDonation(Donation donation) {
        if (donation.getPlayer().isRequiredOnline()) {
            if (fabricPlugin.getServer().getPlayerManager().getPlayerList().stream().noneMatch(player -> player.getUuid().equals(donation.getPlayer().getUUID()))) {
                return false;
            }
        }

        // implement event so it can be cancelled

        try {
            fabricPlugin.getServer().getCommandManager().getDispatcher().execute(donation.getCommand(), fabricPlugin.getServer().getCommandSource());
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public CraftingStoreLogger getLogger() {
        return this.logger;
    }

    @Override
    public void registerRunnable(Runnable runnable, int i, int i1) {
        MultiThreading.schedule(runnable, (long) i, (long) i1, TimeUnit.SECONDS);
    }

    @Override
    public void runAsyncTask(Runnable runnable) {
        MultiThreading.runAsync(runnable);
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public PluginConfiguration getConfiguration() {
        return this.pluginConfiguration;
    }
}
