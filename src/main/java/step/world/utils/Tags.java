package step.world.utils;

import net.minestom.server.tag.Tag;

/**
 * Class for storage tags (metadata) of instances.
 */
public class Tags {
    public static final Tag<WorldType> WORLD_TYPE_TAG = Tag.String("worldType").map(
            WorldType::valueOf,
            WorldType::name
    );
}
