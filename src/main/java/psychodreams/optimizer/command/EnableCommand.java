package psychodreams.optimizer.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;

public class EnableCommand
{
    public static boolean fCrystal = true;
    public void initializeToggleCommands()
    {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(ClientCommandManager.literal("PsychodreamsCrystal").executes(context ->
                {
                    if (fCrystal)
                    {
                        fCrystal = false;
                        displayMessage("disabled | Bye!");
                    }

                    else if (!fCrystal)
                    {
                        fCrystal = true;
                        displayMessage("enabled | GL");
                    }
                    return 1;
                }))
        );
    }

    public static void displayMessage(String message)
    {
        if (!inGame()) return;
        MinecraftClient client = MinecraftClient.getInstance();
        ChatHud chatHud = client.inGameHud.getChatHud();
        chatHud.addMessage(Text.of(message));
    }

    public static Boolean inGame()
    {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.player != null && client.getNetworkHandler() != null;
    }
}
