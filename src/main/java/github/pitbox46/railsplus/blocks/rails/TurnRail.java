package github.pitbox46.railsplus.blocks.rails;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TurnRail extends AbstractRailBlock {
    public static Property<RailShape> SHAPE = BlockStateProperties.RAIL_SHAPE;
    public static DirectionProperty DIRECTION = BlockStateProperties.FACING;
    public static BooleanProperty LEFT = BooleanProperty.create("left");

    private final boolean left;

    public TurnRail(boolean left) {
        super(false, AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL));
        this.left = left;
    }

    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        switch(rot) {
            case CLOCKWISE_180:
                switch(state.get(SHAPE)) {
                    case ASCENDING_EAST:
                    case ASCENDING_NORTH:
                    case ASCENDING_WEST:
                    case ASCENDING_SOUTH:
                    case SOUTH_EAST:
                        return state.with(SHAPE, RailShape.NORTH_WEST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_WEST:
                        return state.with(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_EAST:
                        return state.with(SHAPE, RailShape.SOUTH_WEST);
                    case NORTH_SOUTH: //Forge fix: MC-196102
                    case EAST_WEST:
                        return state;
                }
            case COUNTERCLOCKWISE_90:
                switch(state.get(SHAPE)) {
                    case ASCENDING_EAST:
                    case ASCENDING_WEST:
                    case ASCENDING_NORTH:
                    case ASCENDING_SOUTH:
                    case SOUTH_EAST:
                        return state.with(SHAPE, RailShape.NORTH_EAST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return state.with(SHAPE, RailShape.SOUTH_WEST);
                    case NORTH_EAST:
                        return state.with(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_SOUTH:
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST:
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                }
            case CLOCKWISE_90:
                switch(state.get(SHAPE)) {
                    case ASCENDING_EAST:
                    case ASCENDING_WEST:
                    case ASCENDING_NORTH:
                    case ASCENDING_SOUTH:
                    case SOUTH_EAST:
                        return state.with(SHAPE, RailShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_WEST:
                        return state.with(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_EAST:
                        return state.with(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_SOUTH:
                        return state.with(SHAPE, RailShape.EAST_WEST);
                    case EAST_WEST:
                        return state.with(SHAPE, RailShape.NORTH_SOUTH);
                }
            default:
                return state;
        }
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        RailShape railshape = state.get(SHAPE);
        switch(mirrorIn) {
            case LEFT_RIGHT:
                switch(railshape) {
                    case ASCENDING_NORTH:
                    case ASCENDING_SOUTH:
                    case SOUTH_EAST:
                        return state.with(SHAPE, RailShape.NORTH_EAST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_WEST:
                        return state.with(SHAPE, RailShape.SOUTH_WEST);
                    case NORTH_EAST:
                        return state.with(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_SOUTH:
                    case EAST_WEST:
                        return state.with(LEFT, !state.get(LEFT));
                    default:
                        return super.mirror(state, mirrorIn);
                }
            case FRONT_BACK:
                switch(railshape) {
                    case ASCENDING_EAST:
                    case ASCENDING_WEST:
                    case ASCENDING_NORTH:
                    case ASCENDING_SOUTH:
                    default:
                        break;
                    case SOUTH_EAST:
                        return state.with(SHAPE, RailShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return state.with(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_EAST:
                        return state.with(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_SOUTH:
                    case EAST_WEST:
                        return state.with(LEFT, !state.get(LEFT));
                }
        }

        return super.mirror(state, mirrorIn);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).with(LEFT, left).with(DIRECTION, context.getPlacementHorizontalFacing());
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, LEFT, DIRECTION);
    }

    @Override
    public RailShape getRailDirection(BlockState state, IBlockReader worldIn, BlockPos pos, @Nullable AbstractMinecartEntity cart) {
        if(worldIn instanceof World && cart != null) {
            World world = (World) worldIn;
            Direction direction = state.get(DIRECTION);
            if (world.isBlockPowered(pos)) {
                switch (direction) {
                    case NORTH:
                        return left ? RailShape.SOUTH_WEST : RailShape.SOUTH_EAST;
                    case EAST:
                        return left ? RailShape.NORTH_WEST : RailShape.SOUTH_WEST;
                    case SOUTH:
                        return left ? RailShape.NORTH_EAST : RailShape.NORTH_WEST;
                    case WEST:
                        return left ? RailShape.SOUTH_EAST : RailShape.NORTH_EAST;
                    default:
                        return RailShape.NORTH_SOUTH;
                }
            } else {
                switch (direction) {
                    case EAST:
                    case WEST:
                        return RailShape.EAST_WEST;
                    default:
                        return RailShape.NORTH_SOUTH;
                }
            }
        }
        return RailShape.NORTH_SOUTH;
    }
}
