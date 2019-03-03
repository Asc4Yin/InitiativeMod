package com.initstudios.initiative.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class RaytraceHelper
{
    public static RayTraceResult raytrace(EntityPlayer player, double distance)
    {
        double eyeHeight = player.getEyeHeight();

        Vec3d lookVec = player.getLookVec();
        Vec3d origin = new Vec3d(player.posX, player.posY + eyeHeight, player.posZ);

        Vec3d actualVec = new Vec3d(lookVec.x * distance, lookVec.y * distance, lookVec.z * distance);

        Vec3d direction = origin.add(actualVec);

        return player.world.rayTraceBlocks(origin, direction);
    }
}
