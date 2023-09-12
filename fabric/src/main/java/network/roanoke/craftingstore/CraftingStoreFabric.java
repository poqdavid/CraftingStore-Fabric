package network.roanoke.craftingstore;

import net.craftingstore.core.CraftingStore;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class CraftingStoreFabric implements ModInitializer {

    private CraftingStore craftingStore;
    private MinecraftServer server;

    @Override
    public void onInitialize() {
        this.craftingStore = new CraftingStore(new CraftingStoreFabricImpl(this));

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            this.server = server;
        });
    }

    public MinecraftServer getServer() {
        return this.server;
    }
}
