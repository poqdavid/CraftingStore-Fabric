package network.roanoke.craftingstore.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import network.roanoke.craftingstore.CraftingStoreFabric;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CraftingStoreCommand {

    private CraftingStoreFabric instance;

    public CraftingStoreCommand(CraftingStoreFabric instance) {

        this.instance = instance;

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    literal("craftingstore")
                            .requires(Permissions.require("craftingstore.admin", 4))
                            .then(argument("api-key", StringArgumentType.string())
                                    .executes(this::setApiKey)
                            )
            );
        });

    }


    private int setApiKey(CommandContext<ServerCommandSource> ctx) {
        String apiKey = StringArgumentType.getString(ctx, "api-key");
        // actually set this
        return 1;
    }
}