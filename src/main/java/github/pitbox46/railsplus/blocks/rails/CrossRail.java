package github.pitbox46.railsplus.blocks.rails;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class CrossRail extends AbstractRailBlock {
    public static Property<RailShape> SHAPE = BlockStateProperties.RAIL_SHAPE_STRAIGHT;

    public CrossRail() {
        super(true, AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL));

    }

    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SHAPE);
    }

    @Override
    public RailShape getRailDirection(BlockState state, IBlockReader world, BlockPos pos, @Nullable AbstractMinecartEntity cart) {
        if(cart != null) {
            if(Math.abs(cart.getMotion().x) <= Math.abs(cart.getMotion().z)) {
                return RailShape.NORTH_SOUTH;
            }
            else {
                return RailShape.EAST_WEST;
            }
        }
        return RailShape.EAST_WEST;
    }
}
