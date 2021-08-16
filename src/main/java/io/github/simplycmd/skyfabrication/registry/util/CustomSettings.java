package io.github.simplycmd.skyfabrication.registry.util;

import lombok.Getter;
import lombok.Setter;

public class CustomSettings {
    public enum BlockstateType {
        NORMAL,
        RANDOM_X,
        RANDOM_Y,
        RANDOM,
    }
    public enum ItemModelType {
        NORMAL,
        TEXTURE
    }
    public enum LootType {
        NORMAL,
        NONE
    }

    @Getter
    String id;

    @Getter
    String path;

    @Getter
    BlockstateType blockstateType;

    @Getter
    ItemModelType itemModelType;

    @Getter
    LootType lootType;

    public CustomSettings(String id, String path, BlockstateType blockstateType, ItemModelType itemModelType, LootType lootType) {
        this.id = id;
        this.path = path;
        this.blockstateType = blockstateType;
        this.itemModelType = itemModelType;
        this.lootType = lootType;
    }

    public CustomSettings(String id, String path, BlockstateType blockstateType, ItemModelType itemModelType) {
        this.id = id;
        this.path = path;
        this.blockstateType = blockstateType;
        this.itemModelType = itemModelType;
        this.lootType = LootType.NORMAL;
    }

    public CustomSettings(String id, String path, BlockstateType blockstateType) {
        this.id = id;
        this.path = path;
        this.blockstateType = blockstateType;
        this.itemModelType = ItemModelType.NORMAL;
        this.lootType = LootType.NORMAL;
    }

    public CustomSettings(String id, String path, LootType lootType) {
        this.id = id;
        this.path = path;
        this.blockstateType = BlockstateType.NORMAL;
        this.itemModelType = ItemModelType.NORMAL;
        this.lootType = lootType;
    }

    public CustomSettings(String id, String path) {
        this.id = id;
        this.path = path;
        this.blockstateType = BlockstateType.NORMAL;
        this.itemModelType = ItemModelType.NORMAL;
        this.lootType = LootType.NORMAL;
    }
}
