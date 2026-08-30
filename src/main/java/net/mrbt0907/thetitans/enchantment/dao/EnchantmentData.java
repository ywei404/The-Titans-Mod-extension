package net.mrbt0907.thetitans.enchantment.dao;

import com.google.common.collect.Lists;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;

public class EnchantmentData {
    private final int id;
    private final int lvl;
    private final Enchantment enchantment;
    private final NBTTagCompound nbtTagCompound;
    private final Pair<Integer, Integer> idLvlPair;
    private final Pair<Enchantment, Integer> enchantmentLvlPair;

    private EnchantmentData(Enchantment enchantment, int lvl) {
        this.id = Enchantment.getEnchantmentID(enchantment);
        this.lvl = lvl;
        this.enchantment = enchantment;
        this.nbtTagCompound = new NBTTagCompound();
        this.nbtTagCompound.setShort("id", (short) this.id);
        this.nbtTagCompound.setShort("lvl", (short) this.lvl);
        this.idLvlPair = Pair.of(this.id, this.lvl);
        this.enchantmentLvlPair = Pair.of(this.enchantment, this.lvl);
    }

    public static EnchantmentData of(Enchantment enchantment, int lvl) {
        if (enchantment == null) {
            throw new IllegalArgumentException("enchantment cannot be null!!!");
        }

        return new EnchantmentData(enchantment, lvl);
    }

    public int getId() {
        return id;
    }

    public int getLvl() {
        return lvl;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public NBTTagCompound getNbtTagCompound() {
        return this.nbtTagCompound.copy();
    }

    public Pair<Integer, Integer> getIdLvlPair() {
        return idLvlPair;
    }

    public Pair<Enchantment, Integer> getEnchantmentLvlPair() {
        return enchantmentLvlPair;
    }

    public EnchantmentData addEnchantment(ItemStack itemStack) {
        if (itemStack == null || itemStack.isEmpty()) {
            return this;
        }

        itemStack.addEnchantment(this.enchantment, this.lvl);

        return this;
    }

    public static Collection<EnchantmentData> getAllEnchantments(ItemStack itemStack) {
        if (itemStack == null) {
            return CollectionUtils.emptyCollection();
        }

        NBTTagList enchantmentTagList = itemStack.getEnchantmentTagList(); // If itemStack is EMPTY, it will return an empty list
        List<EnchantmentData> enchantmentDatas = Lists.newArrayList();

        for (int i = 0; i < enchantmentTagList.tagCount(); i++) {
            NBTTagCompound enchantmentTagCompound = enchantmentTagList.getCompoundTagAt(i);
            short id = enchantmentTagCompound.getShort("id");
            short lvl = enchantmentTagCompound.getShort("lvl");
            Enchantment enchantment = Enchantment.getEnchantmentByID(id);

            if (enchantment != null) {
                enchantmentDatas.add(EnchantmentData.of(enchantment, lvl));
            }
        }

        return enchantmentDatas;
    }
}
