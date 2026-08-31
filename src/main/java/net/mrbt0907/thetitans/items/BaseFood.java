package net.mrbt0907.thetitans.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrbt0907.thetitans.TheTitans;
import net.mrbt0907.thetitans.items.functions.OnFoodEaten;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.Collection;
import java.util.function.Consumer;

public class BaseFood extends ItemFood {
    public BaseFood(int hunger, float saturation, boolean isWolfFood) {
        super(hunger, saturation, isWolfFood);
        this.setCreativeTab(TheTitans.TAB_ITEMS);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);

        if (!worldIn.isRemote) {
            Collection<TriConsumer<ItemStack, World, EntityPlayer>> functionImplements = OnFoodEaten.ON_FOOD_EATEN_IMPLEMENTS.get(this);

            if (CollectionUtils.isNotEmpty(functionImplements)) {
                functionImplements.forEach(triConsumer -> triConsumer.accept(stack, worldIn, player));
            }
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab) && this.hasSubtypes)
        {
            items.add(new ItemStack(this));
            items.add(new ItemStack(this, 1, 1)); // Use for enchanted food
        }
    }
}