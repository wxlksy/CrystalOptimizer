package psychodreams.optimizer.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.item.EndCrystalItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static psychodreams.optimizer.Optimizer.*;

@Mixin({EndCrystalItem.class})
public class EndCrystalItemMixin {
    @Inject(method = {"useOnBlock"}, at = {@At("HEAD")}, cancellable = true)
    private void modifyDecrementAmount(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir)
    {
        ItemStack mainHandStack = mc.player.getMainHandStack();
        if (mainHandStack.isOf(Items.END_CRYSTAL))
        {
            if (isLookingAt(Blocks.OBSIDIAN, generalLookPos().getBlockPos())
                    || isLookingAt(Blocks.BEDROCK, generalLookPos().getBlockPos()))
            {
                HitResult hitResult = mc.crosshairTarget;
                if (hitResult instanceof BlockHitResult)
                {
                    BlockHitResult hit = (BlockHitResult) hitResult;
                    BlockPos block = hit.getBlockPos();
                    if (canPlaceCrystalServer(block))
                        context.getStack().decrement(-1);
                }
            }
        }
    }
}
