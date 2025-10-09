package net.dadamalda.create_compatible_storage.foundation;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

// Some of the following code was created with the help of artificial intelligence
public class VectorMathUtils {
    public static Vec3 rotatePoint(Vec3 point, Direction facing) {
        // assuming local forward is +Z, up is +Y, right is +X
        return switch (facing) {
            case NORTH -> new Vec3(point.x, point.y, point.z);
            case SOUTH -> new Vec3(-point.x, point.y, -point.z);
            case WEST -> new Vec3(point.z, point.y, -point.x);
            case EAST -> new Vec3(-point.z, point.y, point.x);
            case UP -> new Vec3(point.x, point.z, -point.y);
            case DOWN -> new Vec3(point.x, -point.z, point.y);
        };
    }

    public static Vec3 unrotatePoint(Vec3 point, Direction facing) {
        // assuming local forward is +Z, up is +Y, right is +X
        return switch (facing) {
            case NORTH -> new Vec3(point.x, point.y, point.z);
            case SOUTH -> new Vec3(-point.x, point.y, -point.z);
            case WEST -> new Vec3(-point.z, point.y, point.x);
            case EAST -> new Vec3(point.z, point.y, -point.x);
            case UP -> new Vec3(point.x, -point.z, -point.y);
            case DOWN -> new Vec3(point.x, point.z, point.y);
        };
    }

    public static AABB makePixelAABB(int x1, int y1, int z1, int x2, int y2, int z2) {
        return new AABB(x1/16d, y1/16d, z1/16d, x2/16d, y2/16d, z2/16d).inflate(1/160d);
    }

    @Deprecated
    public static Vec3 normalFromTwoPoints(Vec3 origin, Vec3 ahead) {
        Vec3 n = ahead.subtract(origin);
        return n.normalize();
    }

    @Deprecated
    @Nullable
    public static Vec3 rayPlaneIntersection(
            Vec3 rayOrigin,
            Vec3 rayDir,
            Vec3 planeCenter,
            Vec3 planeNormal,
            double halfWidth,
            double halfHeight
    ) {
        rayDir = rayDir.normalize();
        planeNormal = planeNormal.normalize();

        // dot(rayDir, normal) > 0 -> ray points *away* from the front
        double denom = rayDir.dot(planeNormal);
        if (denom >= 0) return null; // back-face or parallel

        // distance from ray origin to plane along normal
        double t = planeCenter.subtract(rayOrigin).dot(planeNormal) / denom;
        if (t < 0) return null; // intersection is behind origin

        Vec3 hit = rayOrigin.add(rayDir.scale(t));

        // build a stable local basis on the plane
        Vec3 reference = Math.abs(planeNormal.y) < 0.99 ? new Vec3(0, 1, 0) : new Vec3(1, 0, 0);
        Vec3 planeU = reference.cross(planeNormal).normalize(); // right axis
        Vec3 planeV = planeNormal.cross(planeU).normalize();    // up axis

        // project the hit point onto plane space
        Vec3 local = hit.subtract(planeCenter);
        double u = local.dot(planeU);
        double v = local.dot(planeV);

        if (Math.abs(u) <= halfWidth && Math.abs(v) <= halfHeight)
            return hit; // intersection within rectangle

        return null; // outside bounds
    }
}
