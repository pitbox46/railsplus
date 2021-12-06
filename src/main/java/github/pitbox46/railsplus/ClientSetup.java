package github.pitbox46.railsplus;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
    public static void onClientSetup(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(Registration.CROSS_RAIL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Registration.LEFT_TURN_RAIL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Registration.RIGHT_TURN_RAIL.get(), RenderType.getCutout());
    }
}
