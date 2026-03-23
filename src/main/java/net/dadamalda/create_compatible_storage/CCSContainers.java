package net.dadamalda.create_compatible_storage;

import net.dadamalda.create_compatible_storage.menus.ContraptionCofferContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCSContainers {
    public static DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Create_compatible_storage.MODID);

    public static final RegistryObject<MenuType<ContraptionCofferContainer>> CONTRAPTION_COFFER = CONTAINERS.register("contraption_coffer_container",
                    () -> IForgeMenuType.create(((windowId, inv, data) -> {
            return new ContraptionCofferContainer(windowId, world, pos, inv, inv.player);
        } else {

            if(data.readInt() == 0)
                return new CofferContainer(windowId, inv.player.getMainHandItem(), inv, inv.player, InteractionHand.MAIN_HAND);
            else
                return new CofferContainer(windowId, inv.player.getOffhandItem(), inv, inv.player, InteractionHand.OFF_HAND);
        }
    })));
}
