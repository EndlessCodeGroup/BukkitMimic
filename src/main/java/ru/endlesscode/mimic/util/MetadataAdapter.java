/*
 * This file is part of Mimic.
 * Copyright (C) 2017 Osip Fatkullin
 *
 * Mimic is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Mimic is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Mimic.  If not, see <http://www.gnu.org/licenses/>.
 */

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
