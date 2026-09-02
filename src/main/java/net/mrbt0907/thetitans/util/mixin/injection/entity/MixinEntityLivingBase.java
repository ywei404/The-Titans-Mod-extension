package net.mrbt0907.thetitans.util.mixin.injection.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.mrbt0907.thetitans.event.hook.BaseEventHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityLivingBase.class)
public class MixinEntityLivingBase {
    static {
        System.out.println(">>> MixinEntityLivingBase CLASS LOADED <<<");
    }

    @ModifyArg(
            method = "setHealth",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/MathHelper;clamp(FFF)F"
            ),
            index = 0
    )
    private float modifyHealth(float health) {
        EntityLivingBase entity = (EntityLivingBase) (Object) this;
        World world = entity.world;

        boolean ready = world.loadedEntityList.contains(entity);
        boolean server = !world.isRemote;

        if (!ready || !server) {
            return health;
        }

        health = BaseEventHooks.onLivingModifyHealth(entity, entity.getHealth(), health);

        return health;
    }
}
