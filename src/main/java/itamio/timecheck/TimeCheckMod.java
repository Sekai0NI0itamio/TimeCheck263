package itamio.timecheck;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("timecheck")
public class TimeCheckMod {
    public TimeCheckMod() {
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("timecheck").requires(s -> s.hasPermission(0))
            .executes(ctx -> {
                long t = ctx.getSource().getLevel().dayTime(); int hr = (int)((t / 1000 + 6) % 24); int min = (int)((t / 1000) % 60); ctx.getSource().sendSuccess(() -> Component.literal("In-game time: " + t + " ticks (" + hr + ":" + String.format("%02d", min) + ")"), false);
                return 1;
            }));
    }
}
