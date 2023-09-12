package network.roanoke.craftingstore;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.craftingstore.core.CraftingStorePlugin;
import net.craftingstore.core.PluginConfiguration;
import net.craftingstore.core.logging.CraftingStoreLogger;
import net.craftingstore.core.models.donation.Donation;

public class CraftingStoreFabricImpl implements CraftingStorePlugin {

    private CraftingStoreFabric fabricPlugin;

    CraftingStoreFabricImpl(CraftingStoreFabric fabricPlugin) {
        this.fabricPlugin = fabricPlugin;
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

        return false;
    }

    @Override
    public CraftingStoreLogger getLogger() {
        return null;
    }

    @Override
    public void registerRunnable(Runnable runnable, int i, int i1) {

    }

    @Override
    public void runAsyncTask(Runnable runnable) {

    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public PluginConfiguration getConfiguration() {
        return null;
    }
}
