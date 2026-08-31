package net.mrbt0907.thetitans.enchantment.dao;

import com.google.common.collect.Lists;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EnchantmentData {
    private final int id;
    private int lvl;
    private final Enchantment enchantment;
    private final NBTTagCompound nbtTagCompound;
    private final MutablePair<Integer, Integer> idLvlPair;
    private final MutablePair<Enchantment, Integer> enchantmentLvlPair;
    private boolean isRemove;

    private EnchantmentData(Enchantment enchantment, int lvl) {
        this.id = Enchantment.getEnchantmentID(enchantment);
        this.lvl = lvl;
        this.enchantment = enchantment;
        this.nbtTagCompound = new NBTTagCompound();
        this.nbtTagCompound.setShort("id", (short) this.id);
        this.nbtTagCompound.setShort("lvl", (short) this.lvl);
        this.idLvlPair = MutablePair.of(this.id, this.lvl);
        this.enchantmentLvlPair = MutablePair.of(this.enchantment, this.lvl);
        this.isRemove = false;
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

    private EnchantmentData setLvl(int lvl){
        this.lvl = lvl;
        this.idLvlPair.setRight(lvl);
        this.enchantmentLvlPair.setRight(lvl);
        this.nbtTagCompound.setShort("lvl", (short) lvl);

        return this;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public NBTTagCompound getNbtTagCompound() {
        return nbtTagCompound.copy();
    }

    public Pair<Integer, Integer> getIdLvlPair() {
        return Pair.of(idLvlPair.getLeft(), idLvlPair.getRight());
    }

    public Pair<Enchantment, Integer> getEnchantmentLvlPair() {
        return Pair.of(enchantmentLvlPair.getLeft(), enchantmentLvlPair.getRight());
    }

    public boolean isRemove() {
        return isRemove;
    }

    public EnchantmentData addEnchantment(ItemStack itemStack) {
        if (itemStack == null || itemStack.isEmpty() || this.isRemove || hasEnchantment(itemStack)) {
            return this;
        }

        itemStack.addEnchantment(this.enchantment, this.lvl);

        return this;
    }

    public void removeEnchantment(ItemStack itemStack){
        if (itemStack == null || itemStack.isEmpty() || this.isRemove) {
            return;
        }

        NBTTagList enchantmentTagList = itemStack.getEnchantmentTagList();
        Iterator<NBTBase> iterator = enchantmentTagList.iterator();

        while (iterator.hasNext()){
            NBTBase enchantmentTagCompound = iterator.next();

            if (enchantmentTagCompound instanceof NBTTagCompound){
                short id = ((NBTTagCompound) enchantmentTagCompound).getShort("id");
                short lvl = ((NBTTagCompound) enchantmentTagCompound).getShort("lvl");

                if (id == this.id && lvl == this.lvl) {
                    iterator.remove();
                }
            }
        }

        this.isRemove = true;
    }

    public boolean hasEnchantment(ItemStack itemStack) {
        return getEnchantmentData(itemStack) != null;
    }

    public EnchantmentData getEnchantmentData(ItemStack itemStack) {
        NBTTagList enchantmentTagList = getEnchantmentTagList(itemStack);

        if (enchantmentTagList.tagCount() > 0){
            NBTTagCompound enchantmentTagCompound = enchantmentTagList.getCompoundTagAt(0);
            short id = enchantmentTagCompound.getShort("id");
            short lvl = enchantmentTagCompound.getShort("lvl");
            Enchantment enchantment = Enchantment.getEnchantmentByID(id);

            return EnchantmentData.of(enchantment, lvl);
        }

        return null;
    }

    public NBTTagList getEnchantmentTagList(ItemStack itemStack) {
        NBTTagList matchTagList = new NBTTagList();

        if (itemStack == null || this.isRemove) {
            return matchTagList;
        }

        NBTTagList enchantmentTagList = itemStack.getEnchantmentTagList();

        for (int i = 0; i < enchantmentTagList.tagCount(); i++) {
            NBTTagCompound enchantmentTagCompound = enchantmentTagList.getCompoundTagAt(i);
            short id = enchantmentTagCompound.getShort("id");
            short lvl = enchantmentTagCompound.getShort("lvl");

            if (id == this.id && lvl == this.lvl) {
                Enchantment enchantment = Enchantment.getEnchantmentByID(id);

                if (enchantment != null) {
                    matchTagList.appendTag(enchantmentTagCompound);
                }
            }
        }

        return matchTagList;
    }

    public NBTTagList getEnchantmentTagListCopy(ItemStack itemStack){
        return getEnchantmentTagList(itemStack).copy();
    }

    public EnchantmentData setEnchantmentLevel(ItemStack itemStack, int newLvl){
        NBTTagList enchantmentTagList = getEnchantmentTagList(itemStack);

        for (int i = 0; i < enchantmentTagList.tagCount(); i++) {
            NBTTagCompound enchantmentTagCompound = enchantmentTagList.getCompoundTagAt(i);
            enchantmentTagCompound.setShort("lvl", (short) newLvl);
        }

        if (enchantmentTagList.tagCount() > 0){
            setLvl(newLvl);
        }

        return this;
    }
}
