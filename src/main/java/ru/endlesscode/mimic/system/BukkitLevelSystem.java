/*
 * This file is part of BukkitMimic.
 * Copyright (C) 2017 osipf
 *
 * BukkitMimic is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BukkitMimic is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with BukkitMimic.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.endlesscode.mimic.system;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.endlesscode.mimic.ref.ExistingWeakReference;

/**
 * @author osipf
 * @since 1.0
 */
public abstract class BukkitLevelSystem extends LevelSystem {
    ExistingWeakReference<Player> playerRef;

    /**
     * Constructor that initialize converter.
     *
     * @param converter Converter
     */
    BukkitLevelSystem(@NotNull ExpLevelConverter converter) {
        super(converter);
    }

    /**
     * Initializes and returns copy of current system
     *
     * @param args Args for initialization
     * @return Initialized system copy
     * @throws CloneNotSupportedException If the object's class does not
     *                  support the {@code Cloneable} interface.
     */
    @Override
    public @NotNull BukkitLevelSystem initializedCopy(Object... args) throws CloneNotSupportedException {
        BukkitLevelSystem copy = this.clone();
        Player player = (Player) args[0];
        copy.playerRef = new ExistingWeakReference<>(player);
        return copy;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return Clone of class system
     * @throws CloneNotSupportedException If the object's class does not
     *                  support the {@code Cloneable} interface.
     */
    @Override
    protected BukkitLevelSystem clone() throws CloneNotSupportedException {
        return (BukkitLevelSystem) super.clone();
    }

    /**
     * Returns player object
     *
     * @return The Player
     */
    @Override
    public @NotNull Player getHandler() {
        return playerRef.get();
    }
}
