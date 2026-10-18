package net.dadamalda.create_compatible_storage.menus;

import blusunrize.immersiveengineering.common.gui.CrateMenu;
import blusunrize.immersiveengineering.common.register.IEMenuTypes;
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

public class CrateMenuProvider {
    public static MenuProvider create(Component menuName, IItemHandlerModifiable handler,
                                      Predicate<Player> stillValid, Consumer<Player> onClose) {
        Container wrapper = new StorageInteractionWrapper(handler, stillValid, onClose);
        MenuConstructor constructor = (id, inv, player) ->
                new CrateMenu(IEMenuTypes.WOODEN_CRATE.get(), id, player.getInventory(), wrapper);
        return new SimpleMenuProvider(constructor, menuName);
    }
}
