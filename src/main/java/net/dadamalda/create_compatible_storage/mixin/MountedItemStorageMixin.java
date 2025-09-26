package net.dadamalda.create_compatible_storage.mixin;

import com.simibubi.create.api.contraption.storage.item.MountedItemStorage;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.foundation.utility.CreateLang;
import net.dadamalda.create_compatible_storage.CCSTags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MountedItemStorage.class)
public abstract class MountedItemStorageMixin {
    @Inject(method = "getMenuName", at = @At("HEAD"), cancellable = true, remap = false)
    protected void getMenuName(StructureTemplate.StructureBlockInfo info, Contraption contraption, CallbackInfoReturnable<Component> cir) {
        BlockState state = info.state();
        String translationKey = "";
        if(state.is(CCSTags.CHEST_MOUNTED_STORAGE)) {
            boolean isSingle = info.state().getValue(ChestBlock.TYPE) == ChestType.SINGLE;
            translationKey = isSingle ? "container.chest" : "container.chestDouble";
            // Farmer's Delight
        } else if (state.is(CCSTags.FD_CABINETS)) {
            translationKey = "farmersdelight.container.cabinet";
            // Storage Delight
        } else if (state.is(CCSTags.SD_CABINET_VARIANTS)) {
            translationKey = "container.storagedelight.cabinet_variant";
        } else if (state.is(CCSTags.SD_GLASS_CABINETS)) {
            translationKey = "container.storagedelight.glass_cabinet";
        } else if (state.is(CCSTags.SD_BOOKSHELVES_WITH_DOOR)) {
            translationKey = "container.storagedelight.bookshelf_door";
        } else if (state.is(CCSTags.SD_SMALL_DRAWERS)) {
            translationKey = "container.storagedelight.small_drawers";
        } else if (state.is(CCSTags.SD_DRAWERS_WITH_BOOKS)) {
            translationKey = "container.storagedelight.drawer_books";
        } else if (state.is(CCSTags.SD_DRAWERS_WITH_DOOR)) {
            translationKey = "container.storagedelight.drawer_door";
        } else if (state.is(CCSTags.SD_DRAWERS)) {
            translationKey = "container.storagedelight.drawer";
            // Furniture Refurbished
        } else if (state.is(CCSTags.FR_DRAWERS)) {
            translationKey = "container.refurbished_furniture.drawer";
        } else if (state.is(CCSTags.FR_KITCHEN_DRAWERS)) {
            translationKey = "container.refurbished_furniture.kitchen_drawer";
        } else if (state.is(CCSTags.FR_STORAGE_CABINETS)) {
            translationKey = "container.refurbished_furniture.storage_cabinet";
        } else if (state.is(CCSTags.FR_COOLERS)) {
            translationKey = "container.refurbished_furniture.cooler";
        } else if (state.is(CCSTags.FR_CRATES)) {
            translationKey = "container.refurbished_furniture.crate";
        } else if (state.is(CCSTags.FR_MAILBOXES)) {
            translationKey = "container.refurbished_furniture.mailbox";
        }

        if(!translationKey.isEmpty()) {
            cir.setReturnValue(
                    CreateLang.translateDirect("contraptions.moving_container", Component.translatable(translationKey)));
            cir.cancel();
        }
    }
}
