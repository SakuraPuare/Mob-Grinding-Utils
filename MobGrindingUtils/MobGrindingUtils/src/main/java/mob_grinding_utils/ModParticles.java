package mob_grinding_utils;

import mob_grinding_utils.client.particles.ParticleFluidXP;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {
    @SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerParticleFactory(RegisterParticleProvidersEvent event) {
		Minecraft.getInstance().particleEngine.register(MobGrindingUtils.PARTICLE_FLUID_XP.get(), sprite -> new ParticleFluidXP.Factory(sprite));
	}
}
