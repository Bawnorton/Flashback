package com.moulberry.flashback.mixin.audio;

import com.moulberry.flashback.Flashback;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundEngine.class)
public class MixinSoundEngine {

    @Unique
    private boolean wasExportingAudio = false;

    @Inject(method = "shouldChangeDevice", at = @At("HEAD"), cancellable = true)
    public void shouldChangeDevice(CallbackInfoReturnable<Boolean> cir) {
        boolean isExportingAudio = Flashback.isExporting() && Flashback.EXPORT_JOB.getSettings().recordAudio();

        if (wasExportingAudio != isExportingAudio) {
            wasExportingAudio = isExportingAudio;
            cir.setReturnValue(true);
        } else if (isExportingAudio) {
            cir.setReturnValue(false);
        }
    }

}
