package net.dadamalda.create_compatible_storage.storage_types;

import com.mojang.serialization.Codec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorage;
import com.simibubi.create.content.contraptions.Contraption;
import net.dadamalda.create_compatible_storage.CCSMountedStorageTypes;
import net.dadamalda.create_compatible_storage.CCSTags;
import net.dadamalda.create_compatible_storage.foundation.PreciseContraptionInteractionStore;
import net.dadamalda.create_compatible_storage.foundation.VectorMathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

public class FurnitureRefurbishedMountedStorage extends SimpleMountedStorage {
    public static final Codec<FurnitureRefurbishedMountedStorage> CODEC = SimpleMountedStorage.codec(FurnitureRefurbishedMountedStorage::new);

    protected FurnitureRefurbishedMountedStorage(MountedItemStorageType<?> type, IItemHandler handler) {
        super(type, handler);
    }

    public FurnitureRefurbishedMountedStorage(IItemHandler handler) {
        this(CCSMountedStorageTypes.FURNITURE_REFURBISHED.get(), handler);
    }

    @Override
    public boolean handleInteraction(ServerPlayer player, Contraption contraption, StructureTemplate.StructureBlockInfo info) {
        BlockState state = info.state();
        Direction facing = Direction.NORTH;
        if(info.state().hasProperty(BlockStateProperties.FACING)) {
            facing = info.state().getValue(BlockStateProperties.FACING);
        } else if(info.state().hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        } else {
            return false;
        }
        Vec3 position = info.pos().getCenter();
        Vec3 clickPoint = PreciseContraptionInteractionStore.getLastInteraction(player);
        Vec3 localClickPoint = VectorMathUtils.unrotatePoint(clickPoint.subtract(position), facing);

        if(state.is(CCSTags.FR_STORAGE_CABINETS)) {
            AABB boundingBox = VectorMathUtils.makePixelAABB(-8, -8, 8, 8, 8, 8);
            if(!boundingBox.contains(localClickPoint)) return false;
        }
        if(state.is(CCSTags.FR_KITCHEN_STORAGE_CABINETS)) {
            AABB boundingBox = VectorMathUtils.makePixelAABB(-8, -8, 6, 8, 8, 6);
            if(!boundingBox.contains(localClickPoint)) return false;
        }
        if(state.is(CCSTags.FR_DRAWERS)) {
            AABB boundingBox = VectorMathUtils.makePixelAABB(-8, -8, 7, 8, 6, 7);
            if(!boundingBox.contains(localClickPoint)) return false;
        }
        if(state.is(CCSTags.FR_KITCHEN_DRAWERS)) {
            AABB boundingBox = VectorMathUtils.makePixelAABB(-8, -8, 6, 8, 6, 6);
            if(!boundingBox.contains(localClickPoint)) return false;
        }

        return super.handleInteraction(player, contraption, info);
    }
}
