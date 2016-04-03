package org.devoxx4kids.forge.mods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ArrowShotgun {

	private final int arrows = 20;

	@SubscribeEvent
	public void shootArrows(ArrowLooseEvent event) {
		EntityPlayer player = event.getEntityPlayer();
		World world = event.getWorld();

		for (int i = 0; i < arrows; i++) {
			EntityArrow arrow = new EntityTippedArrow(world, player);
			arrow.func_184547_a(player, player.rotationPitch,
					player.rotationYaw, 0.0F,
					ItemBow.func_185059_b(event.getCharge()) * 3.0F, 12.0F);
			arrow.canBePickedUp = EntityArrow.PickupStatus.DISALLOWED;
			arrow.setFire(10000);
			if (!world.isRemote) {
				world.spawnEntityInWorld(arrow);
			}
		}

		event.setCanceled(true);
	}
}