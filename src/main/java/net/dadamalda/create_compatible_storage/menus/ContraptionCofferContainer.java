package net.dadamalda.create_compatible_storage.menus;

import net.joefoxe.hexerei.container.ModContainers;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ContraptionCofferContainer extends AbstractContainerMenu {
    private final Player playerEntity;
    private final IItemHandler playerInventory;
    private final Container container;
    public static final int OFFSET = 28;

    private Slot cofferSlot(Container container, int slot, int x, int y){
        return new Slot(container, slot, x, y){
            @Override
            public boolean mayPlace(ItemStack pStack) {
                return container.canPlaceItem(slot, pStack);
            }
        };
    }

    public ContraptionCofferContainer(int windowId, Container container, Inventory playerInventory, Player player) {
        super(ModContainers.COFFER_CONTAINER.get(), windowId);
        playerEntity = player;

        this.container = container;

        this.playerInventory = new InvWrapper(playerInventory);

        container.startOpen(player);

        layoutPlayerInventorySlots(11, 147 - OFFSET);

        this.addSlot(cofferSlot(container, 0, 15, 18 - OFFSET));
        this.addSlot(cofferSlot(container, 1, 15 + (21), 18 - OFFSET));
        this.addSlot(cofferSlot(container, 2, 15 + (21 * 2), 18 - OFFSET));
        this.addSlot(cofferSlot(container, 3, 15 + (21 * 3), 18 - OFFSET));
        this.addSlot(cofferSlot(container, 4, 15 + (21 * 4), 18 - OFFSET));
        this.addSlot(cofferSlot(container, 5, 15 + (21 * 5), 18 - OFFSET));
        this.addSlot(cofferSlot(container, 6, 15 + (21 * 6), 18 - OFFSET));
        this.addSlot(cofferSlot(container, 7, 15 + (21 * 7), 18 - OFFSET));
        this.addSlot(cofferSlot(container, 8, 15 + (21 * 8), 18 - OFFSET));
        this.addSlot(cofferSlot(container, 9, 15, 39 - OFFSET));
        this.addSlot(cofferSlot(container, 10, 15 + (21), 39 - OFFSET));
        this.addSlot(cofferSlot(container, 11, 15 + (21 * 2), 39 - OFFSET));
        this.addSlot(cofferSlot(container, 12, 15 + (21 * 6), 39 - OFFSET));
        this.addSlot(cofferSlot(container, 13, 15 + (21 * 7), 39 - OFFSET));
        this.addSlot(cofferSlot(container, 14, 15 + (21 * 8), 39 - OFFSET));
        this.addSlot(cofferSlot(container, 15, 15, 60 - OFFSET));
        this.addSlot(cofferSlot(container, 16, 15 + (21), 60 - OFFSET));
        this.addSlot(cofferSlot(container, 17, 15 + (21 * 2), 60 - OFFSET));
        this.addSlot(cofferSlot(container, 18, 15 + (21 * 6), 60 - OFFSET));
        this.addSlot(cofferSlot(container, 19, 15 + (21 * 7), 60 - OFFSET));
        this.addSlot(cofferSlot(container, 20, 15 + (21 * 8), 60 - OFFSET));
        this.addSlot(cofferSlot(container, 21, 15, 81 - OFFSET));
        this.addSlot(cofferSlot(container, 22, 15 + (21), 81 - OFFSET));
        this.addSlot(cofferSlot(container, 23, 15 + (21 * 2), 81 - OFFSET));
        this.addSlot(cofferSlot(container, 24, 15 + (21 * 6), 81 - OFFSET));
        this.addSlot(cofferSlot(container, 25, 15 + (21 * 7), 81 - OFFSET));
        this.addSlot(cofferSlot(container, 26, 15 + (21 * 8), 81 - OFFSET));
        this.addSlot(cofferSlot(container, 27, 15, 102 - OFFSET));
        this.addSlot(cofferSlot(container, 28, 15 + (21), 102 - OFFSET));
        this.addSlot(cofferSlot(container, 29, 15 + (21 * 2), 102 - OFFSET));
        this.addSlot(cofferSlot(container, 30, 15 + (21 * 3), 102 - OFFSET));
        this.addSlot(cofferSlot(container, 31, 15 + (21 * 4), 102 - OFFSET));
        this.addSlot(cofferSlot(container, 32, 15 + (21 * 5), 102 - OFFSET));
        this.addSlot(cofferSlot(container, 33, 15 + (21 * 6), 102 - OFFSET));
        this.addSlot(cofferSlot(container, 34, 15 + (21 * 7), 102 - OFFSET));
        this.addSlot(cofferSlot(container, 35, 15 + (21 * 8), 102 - OFFSET));
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }

        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 36;  // must match TileEntityInventoryBasic.NUMBER_OF_SLOTS

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();


        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerEntity, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return container.stillValid(pPlayer);
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        container.stopOpen(pPlayer);
    }
}
