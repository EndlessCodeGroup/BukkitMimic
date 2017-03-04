package ru.endlesscode.mimic.util;

import org.bukkit.plugin.ServicePriority;
import org.jetbrains.annotations.NotNull;
import ru.endlesscode.mimic.BaseSystem;
import ru.endlesscode.mimic.Metadata;

/**
 * Adapter to work with systems {@code Metadata}
 *
 * @see ru.endlesscode.mimic.Metadata
 * @author Osip Fatkullin
 * @since 1.0
 */
public class MetadataAdapter {
    private Metadata meta;

    /**
     * Creates {@code MetadataAdapter}
     *
     * @param meta The metadata
     */
    public MetadataAdapter(@NotNull Metadata meta) {
        this.meta = meta;
    }

    public boolean requiredClassesExists() {
        return ClassUtil.classesExists(meta.classes());
    }

    public <T extends BaseSystem> Class<T> getSystemClass() {
        return meta.systemType().getSystemClass();
    }

    public String getSystemName() {
        return getSystemClass().getSimpleName();
    }

    public ServicePriority getPriority() {
        return meta.priority();
    }
}
