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

public class CofferMenuProvider {
    public static MenuProvider create(Component menuName, IItemHandlerModifiable handler,
                                      Predicate<Player> stillValid, Consumer<Player> onClose) {
        Container wrapper = new StorageInteractionWrapper(handler, stillValid, onClose);
        MenuConstructor constructor = (id, inv, player) -> new ContraptionCofferContainer(id, wrapper, inv, player);
        return new SimpleMenuProvider(constructor, menuName);
    }
}
