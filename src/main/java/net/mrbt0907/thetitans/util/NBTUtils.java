package net.mrbt0907.thetitans.util;

import net.minecraft.nbt.NBTTagList;

public class NBTUtils {
    private NBTUtils(){}

    public static boolean isInvalidNBTTagList(NBTTagList tagList){
        return tagList == null;
    }

    public static boolean isEmptyNBTTagList(NBTTagList tagList){
        return isInvalidNBTTagList(tagList) || tagList.tagCount() <= 0;
    }
}
