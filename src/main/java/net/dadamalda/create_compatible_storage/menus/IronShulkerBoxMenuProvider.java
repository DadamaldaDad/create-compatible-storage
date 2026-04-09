package net.dadamalda.create_compatible_storage.menus;

import com.progwml6.ironshulkerbox.common.inventory.IronShulkerBoxMenu;
import com.simibubi.create.api.contraption.storage.item.menu.StorageInteractionWrapper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class IronShulkerBoxMenuProvider {
    public static MenuProvider create(Component menuName, IItemHandlerModifiable handler,
                                      Predicate<Player> stillValid, Consumer<Player> onClose) {
        Container wrapper = new StorageInteractionWrapper(handler, stillValid, onClose);
        MenuConstructor constructor = null;
        switch (handler.getSlots()) {
            case 45 -> constructor = (id, inv, player) -> IronShulkerBoxMenu.createCopperContainer(id, inv, wrapper);
            case 54 -> constructor = (id, inv, player) -> IronShulkerBoxMenu.createIronContainer(id, inv, wrapper);
            case 81 -> constructor = (id, inv, player) -> IronShulkerBoxMenu.createGoldContainer(id, inv, wrapper);
            case 108 -> constructor = (id, inv, player) -> IronShulkerBoxMenu.createDiamondContainer(id, inv, wrapper);
        }
        return new SimpleMenuProvider(constructor, menuName);
    }
}
