package net.dadamalda.create_compatible_storage.sounds;

import net.dadamalda.create_compatible_storage.CCSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class MountedStorageSoundHandler {
    public static MountedStorageSound getSoundFromState(BlockState state) {
        if(state.is(CCSTags.BARREL_SOUND) || state.is(Blocks.BARREL)) {
            return MountedStorageSound.BARREL;
        } else if(state.is(CCSTags.SHULKER_BOXES)) {
            return MountedStorageSound.SHULKER_BOX;
        } else if(state.is(CCSTags.SILENT_MOUNTED_STORAGE)) {
            return MountedStorageSound.SILENT;
        } else if(state.is(CCSTags.SD_CABINET_SOUND)) {
            return MountedStorageSound.SD_CABINET_VARIANT;
        } else if(state.is(CCSTags.FR_DRAWERS)) {
            return MountedStorageSound.FR_DRAWER;
        } else if(state.is(CCSTags.FR_KITCHEN_DRAWERS)) {
            return MountedStorageSound.FR_KITCHEN_DRAWER;
        } else if(state.is(CCSTags.FR_STORAGE_CABINETS)) {
            return MountedStorageSound.FR_STORAGE_CABINET;
        } else if(state.is(CCSTags.FR_KITCHEN_STORAGE_CABINETS)) {
            return MountedStorageSound.FR_STORAGE_CABINET;
        } else if(state.is(CCSTags.FR_CRATES)) {
            return MountedStorageSound.FR_CRATE;
        } else if(state.is(CCSTags.FR_COOLERS)) {
            return MountedStorageSound.FR_COOLER;
        } else if(state.is(CCSTags.WW_CLOSETS)) {
            return MountedStorageSound.WW_CLOSET;
        } else if(state.is(CCSTags.FB_FRAMED_CHEST)) {
            return MountedStorageSound.FB_FRAMED_CHEST;
        } else {
            return MountedStorageSound.UNKNOWN;
        }
    }

    public static void playOpeningSound(MountedStorageSound sound, ServerLevel level, Vec3 pos) {
        switch(sound) {
            case BARREL -> level.playSound(
                    null, BlockPos.containing(pos),
                    SoundEvents.BARREL_OPEN, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case SHULKER_BOX -> level.playSound(
                    null, BlockPos.containing(pos),
                    SoundEvents.SHULKER_BOX_OPEN, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case SD_CABINET_VARIANT -> level.playSound(
                    null, BlockPos.containing(pos),
                    SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case FR_DRAWER -> level.playSound(
                    null, BlockPos.containing(pos),
                    getSound("refurbished_furniture:block.drawer.open"), SoundSource.BLOCKS,
                    1.0F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case FR_KITCHEN_DRAWER -> level.playSound(
                    null, BlockPos.containing(pos),
                    getSound("refurbished_furniture:block.kitchen_drawer.open"), SoundSource.BLOCKS,
                    1.0F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case FR_STORAGE_CABINET -> level.playSound(
                    null, BlockPos.containing(pos),
                    getSound("refurbished_furniture:block.cabinet.open"), SoundSource.BLOCKS,
                    1.0F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case FR_CRATE -> level.playSound(
                    null, BlockPos.containing(pos),
                    SoundEvents.BARREL_OPEN, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.7F
            );
            case FR_COOLER -> level.playSound(
                    null, BlockPos.containing(pos),
                    getSound("refurbished_furniture:block.cooler.open"), SoundSource.BLOCKS,
                    1.0F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case WW_CLOSET -> level.playSound(
                    null, BlockPos.containing(pos),
                    /* getSound("woodworks:block.closet.open") */ SoundEvents.CHEST_OPEN, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case FB_FRAMED_CHEST -> level.playSound(
                    null, BlockPos.containing(pos),
                    SoundEvents.CHEST_OPEN, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.9F
            );
        }
    }

    public static void playClosingSound(MountedStorageSound sound, ServerLevel level, Vec3 pos) {
        switch(sound) {
            case BARREL -> level.playSound(
                    null, BlockPos.containing(pos),
                    SoundEvents.BARREL_CLOSE, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case SHULKER_BOX -> level.playSound(
                    null, BlockPos.containing(pos),
                    SoundEvents.SHULKER_BOX_CLOSE, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case SD_CABINET_VARIANT -> level.playSound(
                    null, BlockPos.containing(pos),
                    SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case FR_DRAWER -> level.playSound(
                    null, BlockPos.containing(pos),
                    getSound("refurbished_furniture:block.drawer.close"), SoundSource.BLOCKS,
                    1.0F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case FR_KITCHEN_DRAWER -> level.playSound(
                    null, BlockPos.containing(pos),
                    getSound("refurbished_furniture:block.kitchen_drawer.close"), SoundSource.BLOCKS,
                    1.0F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case FR_STORAGE_CABINET -> level.playSound(
                    null, BlockPos.containing(pos),
                    getSound("refurbished_furniture:block.cabinet.close"), SoundSource.BLOCKS,
                    1.0F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case FR_CRATE -> level.playSound(
                    null, BlockPos.containing(pos),
                    SoundEvents.BARREL_CLOSE, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.7F
            );
            case FR_COOLER -> level.playSound(
                    null, BlockPos.containing(pos),
                    getSound("refurbished_furniture:block.cooler.close"), SoundSource.BLOCKS,
                    1.0F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case WW_CLOSET -> level.playSound(
                    null, BlockPos.containing(pos),
                    /* getSound("woodworks:block.closet.close") */ SoundEvents.CHEST_CLOSE, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.9F
            );
            case FB_FRAMED_CHEST -> level.playSound(
                    null, BlockPos.containing(pos),
                    SoundEvents.CHEST_CLOSE, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.1F + 0.9F
            );
        }
    }

    private static SoundEvent getSound(String id) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse(id));
    }
}
