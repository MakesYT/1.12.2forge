package com.example.modid.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


/**
 * 注入修改剪切板,支持粘贴图片
 *
 * @author MakesYT
 */
@SideOnly(Side.CLIENT)
@Mixin(GuiScreen.class)
public abstract class ClipboardMixin {

    private static boolean isWindows() {
        return System.getProperty("os.name").toUpperCase().contains("WINDOWS");
    }

    @Inject(at = @At("RETURN"), method = "getClipboardString", cancellable = true)
    private static void readImg(CallbackInfoReturnable<String> cir) {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString("获取图片"));
        if (isWindows() && Minecraft.getMinecraft().currentScreen instanceof net.minecraft.client.gui.GuiChat) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("获取2图片"));
        }
    }
}