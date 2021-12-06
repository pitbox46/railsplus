package github.pitbox46.railsplus.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends Entity {
    public AbstractMinecartEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

//    @ModifyVariable(at = @At(value = "INVOKE", target = "net/minecraft/entity/item/minecart/AbstractMinecartEntity.getMotion()Lnet/minecraft/util/math/vector/Vector3d;", ordinal = 1), method = "moveAlongTrack", ordinal = 0)
//    public RailShape onMoveAlongTrack(RailShape railshape, BlockPos pos, BlockState state) {
//        if(state.getBlock().getClass() == CrossRail.class) {
//            if(Math.abs(this.getMotion().normalize().z) == 1) {
//                return RailShape.NORTH_SOUTH;
//            } else {
//                return RailShape.EAST_WEST;
//            }
//        }
//        return railshape;
//    }
}
