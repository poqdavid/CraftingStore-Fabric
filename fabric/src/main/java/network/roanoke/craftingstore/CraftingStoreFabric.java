package network.roanoke.craftingstore;

import net.craftingstore.core.CraftingStore;
import net.fabricmc.api.ModInitializer;

public class CraftingStoreFabric implements ModInitializer {

    private CraftingStore craftingStore;

    @Override
    public void onInitialize() {
        this.craftingStore = new CraftingStore(new CraftingStoreFabricImpl(this));
    }
}
