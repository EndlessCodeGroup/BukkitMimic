/*
 * This file is part of BukkitMimic.
 * Copyright (C) 2017 Osip Fatkullin
 *
 * BukkitMimic is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BukkitMimic is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BukkitMimic.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.endlesscode.mimic.system;

import org.jetbrains.annotations.NotNull;
import ru.endlesscode.mimic.system.registry.Metadata;
import ru.endlesscode.mimic.system.registry.SystemPriority;

/**
 * System that can't be initialized
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
@Metadata(priority = SystemPriority.LOWEST)
public class NotInitializableSystem extends TestSystem {
    /**
     * Initializes and returns copy of current system
     *
     * @param args Args for initialization
     * @return Initialized system copy
     * @throws CloneNotSupportedException If the object's class does not
     *                                    support the {@code Cloneable} interface.
     */
    @Override
    public @NotNull PlayerSystem initializedCopy(Object... args) throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clone not supported :(");
    }

    /**
     * Returns player-related object
     *
     * @return Player-related object
     */
    @Override
    public @NotNull Object getHandler() {
        return this;
    }

    /**
     * Checks if this system is found and enabled
     *
     * @return {@code true} if works, otherwise {@code false}
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

    /**
     * Returns the name of system.
     *
     * @return name of system
     * @implNote Usually used name of the plugin that implements system.
     */
    @Override
    public String getName() {
        return "NotInitializableSystem";
    }
}
