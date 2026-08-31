package net.mrbt0907.thetitans.util;

import com.google.common.collect.Lists;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.mrbt0907.thetitans.enchantment.dao.EnchantmentData;
import org.apache.commons.collections4.*;

import java.util.*;
import java.util.function.Function;

public class EnchantmentUtils {
    private EnchantmentUtils() {
    }

    public static Collection<EnchantmentData> getAllEnchantmentData(ItemStack itemStack) {
        if (itemStack == null) {
            return CollectionUtils.emptyCollection();
        }

        NBTTagList enchantmentTagList = itemStack.getEnchantmentTagList(); // If itemStack is EMPTY, it will return an empty list
        List<EnchantmentData> enchantmentDatum = Lists.newArrayList();

        for (int i = 0; i < enchantmentTagList.tagCount(); i++) {
            NBTTagCompound enchantmentTagCompound = enchantmentTagList.getCompoundTagAt(i);
            short id = enchantmentTagCompound.getShort("id");
            short lvl = enchantmentTagCompound.getShort("lvl");
            Enchantment enchantment = Enchantment.getEnchantmentByID(id);

            if (enchantment != null) {
                enchantmentDatum.add(EnchantmentData.of(enchantment, lvl));
            }
        }

        return enchantmentDatum;
    }

    public static NBTTagList getEnchantmentsTagList(Enchantment enchantment, ItemStack itemStack) {
        NBTTagList matchTagList = new NBTTagList();

        if (isEnchantmentItemStackInvalid(enchantment, itemStack)) {
            return matchTagList;
        }

        int enchantmentID = Enchantment.getEnchantmentID(enchantment);
        NBTTagList enchantmentTagList = itemStack.getEnchantmentTagList();

        for (int i = 0; i < enchantmentTagList.tagCount(); i++) {
            NBTTagCompound enchantmentTagCompound = enchantmentTagList.getCompoundTagAt(i);
            short id = enchantmentTagCompound.getShort("id");

            if (id == enchantmentID) {
                matchTagList.appendTag(enchantmentTagCompound);
            }
        }

        return matchTagList;
    }

    public static List<EnchantmentData> getEnchantments(Enchantment enchantment, ItemStack itemStack){
        return tagListToData(getEnchantmentsTagList(enchantment, itemStack));
    }

    public static void removeEnchantments(Enchantment enchantment, ItemStack itemStack) {
        if (isEnchantmentItemStackInvalid(enchantment, itemStack)) {
            return;
        }

        int enchantmentID = Enchantment.getEnchantmentID(enchantment);
        NBTTagList enchantmentTagList = itemStack.getEnchantmentTagList();
        Iterator<NBTBase> iterator = enchantmentTagList.iterator();

        while (iterator.hasNext()) {
            NBTBase enchantmentTagCompound = iterator.next();

            if (enchantmentTagCompound instanceof NBTTagCompound) {
                short id = ((NBTTagCompound) enchantmentTagCompound).getShort("id");

                if (id == enchantmentID) {
                    iterator.remove();
                }
            }
        }
    }

    public static List<EnchantmentData> tagListToData(NBTTagList tagList) {
        List<EnchantmentData> enchantmentData = Lists.newArrayList();

        if (NBTUtils.isEmptyNBTTagList(tagList)) {
            return enchantmentData;
        }

        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound compound = tagList.getCompoundTagAt(i);
            short id = compound.getShort("id");
            short lvl = compound.getShort("lvl");
            Enchantment enchantment = Enchantment.getEnchantmentByID(id);

            if (enchantment != null){
                enchantmentData.add(EnchantmentData.of(enchantment, lvl));
            }
        }

        return enchantmentData;
    }

    public static int getEnchantmentLevel(Enchantment enchantment, ItemStack itemStack){
        List<EnchantmentData> enchantmentData = getEnchantments(enchantment, itemStack);

        if (CollectionUtils.isEmpty(enchantmentData)){
            return 0;
        }

        return enchantmentData.stream().mapToInt(EnchantmentData::getLvl).max().orElse(0);
    }

    public static boolean isEnchantmentItemStackInvalid(Enchantment enchantment, ItemStack itemStack) {
        if (itemStack == null || itemStack.isEmpty() || enchantment == null) {
            return true;
        }

        int enchantmentID = Enchantment.getEnchantmentID(enchantment);

        return enchantmentID < 0;
    }
}
