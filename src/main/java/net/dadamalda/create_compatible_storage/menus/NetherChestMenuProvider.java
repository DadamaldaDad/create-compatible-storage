package net.dadamalda.create_compatible_storage.menus;

import com.progwml6.ironchest.common.block.IronChestsTypes;
import com.progwml6.ironchest.common.inventory.IronChestMenu;
import com.simibubi.create.api.contraption.storage.item.menu.StorageInteractionWrapper;
import fuzs.netherchested.NetherChested;
import fuzs.netherchested.client.gui.screens.inventory.NetherChestScreen;
import fuzs.netherchested.config.ServerConfig;
import fuzs.netherchested.world.inventory.NetherChestMenu;
import fuzs.puzzlesapi.api.limitlesscontainers.v1.MultipliedContainer;
import fuzs.puzzlesapi.api.limitlesscontainers.v1.MultipliedSimpleContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class NetherChestMenuProvider {
    public static MenuProvider create(Component menuName, IItemHandlerModifiable handler,
                                      Predicate<Player> stillValid2, Consumer<Player> onClose) {
        MultipliedSimpleContainer container = new MultipliedSimpleContainer(NetherChested.CONFIG.get(ServerConfig.class).stackSizeMultiplier, 54) {
            @Override
            public boolean stillValid(Player pPlayer) {
                return stillValid2.test(pPlayer);
            }
        };
        MenuConstructor constructor = (id, inv, player) -> new NetherChestMenu(id, inv, container);
        return new SimpleMenuProvider(constructor, menuName);
    }
}
