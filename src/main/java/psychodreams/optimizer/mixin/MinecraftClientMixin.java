package psychodreams.optimizer.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import psychodreams.optimizer.Optimizer;
import psychodreams.optimizer.command.EnableCommand;
import static psychodreams.optimizer.Optimizer.limitPackets;
import static psychodreams.optimizer.Optimizer.mc;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin
{
    @Inject(at = @At("HEAD"), method = "doItemUse", cancellable = true)
    private void onDoItemUse(CallbackInfo ci)
    {
        if (EnableCommand.fCrystal)
        {
            ItemStack mainHand = mc.player.getMainHandStack();
            if (mainHand.isOf(Items.END_CRYSTAL))
                if (Optimizer.hitCount != limitPackets())
                    ci.cancel();
        }
    }
}