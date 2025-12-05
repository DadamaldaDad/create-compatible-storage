package net.dadamalda.create_compatible_storage.menus;

import com.progwml6.ironchest.common.block.IronChestsTypes;
import com.progwml6.ironchest.common.inventory.IronChestMenu;
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

public class IronChestMenuProvider {
    public static MenuProvider create(Component menuName, IItemHandlerModifiable handler,
                                      Predicate<Player> stillValid, Consumer<Player> onClose) {
        int dirt = IronChestsTypes.DIRT.size;
        Container wrapper = new StorageInteractionWrapper(handler, stillValid, onClose);
        MenuConstructor constructor = null;
        switch (handler.getSlots()) {
            case 1 -> constructor = (id, inv, player) -> IronChestMenu.createDirtContainer(id, inv, wrapper);
            case 45 -> constructor = (id, inv, player) -> IronChestMenu.createCopperContainer(id, inv, wrapper);
            case 54 -> constructor = (id, inv, player) -> IronChestMenu.createIronContainer(id, inv, wrapper);
            case 81 -> constructor = (id, inv, player) -> IronChestMenu.createGoldContainer(id, inv, wrapper);
            case 108 -> constructor = (id, inv, player) -> IronChestMenu.createDiamondContainer(id, inv, wrapper);
        }
        return new SimpleMenuProvider(constructor, menuName);
    }
}
