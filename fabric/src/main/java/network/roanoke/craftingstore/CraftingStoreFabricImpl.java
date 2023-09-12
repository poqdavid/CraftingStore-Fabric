package network.roanoke.craftingstore;

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
