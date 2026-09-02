package net.mrbt0907.thetitans.test;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber
public class Test {
    public static void test(ItemStack itemStack, EntityLivingBase entityLivingBase, EntityItem entityItem) {
        entityLivingBase.onUpdate();
    }

    @SubscribeEvent
    public static void test2(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() instanceof EntityPlayer){
//            System.out.println(Minecraft.getMinecraft().world.isRemote);
//            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
//            System.out.println(Arrays.stream(server.worlds).map(worldServer -> worldServer.isRemote).collect(Collectors.toList()));
//            Arrays.stream(server.worlds).forEach(worldServer -> System.out.println(worldServer.playerEntities));

        }
    }
}
