package net.mrbt0907.thetitans.util.mixin.injection.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public class MixinEntityLivingBase {
    static {
        System.out.println(">>> MixinEntityLivingBase CLASS LOADED <<<");
    }

    @Inject(
            method = "setHealth",
            at = @At("HEAD")
    )
    private void beforeSetHealth(float health, CallbackInfo ci) {
        EntityLivingBase entity = (EntityLivingBase) (Object) this;

        if (entity instanceof EntityPlayer && !entity.world.isRemote){
            System.out.println("setHealth called: " + health);
        }
    }
}
