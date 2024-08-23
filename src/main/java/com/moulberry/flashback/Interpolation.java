package com.moulberry.flashback;

import net.minecraft.util.Mth;
import org.joml.Quaternionf;

public class Interpolation {

    public static double linear(double from, double to, float amount) {
        return from + (to - from) * amount;
    }

    public static float linear(float from, float to, float amount) {
        return from + (to - from) * amount;
    }


    public static float linearAngle(float from, float to, float amount) {
        return from + Mth.wrapDegrees(to - from) * amount;
    }

    public static Quaternionf linear(Quaternionf from, Quaternionf to, float amount) {
        return from.slerp(to, amount, new Quaternionf());
    }

}
