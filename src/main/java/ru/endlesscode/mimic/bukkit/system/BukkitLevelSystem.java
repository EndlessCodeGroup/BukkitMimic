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

package ru.endlesscode.mimic.bukkit.system;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.endlesscode.mimic.api.ref.ExistingWeakReference;
import ru.endlesscode.mimic.api.system.ExpLevelConverter;
import ru.endlesscode.mimic.api.system.LevelSystem;

/**
 * @author osipf
 * @since 1.0
 */
public abstract class BukkitLevelSystem extends LevelSystem {
    @SuppressWarnings("WeakerAccess")
    protected final ExistingWeakReference<Player> playerRef;

    /**
     * Constructor that initialize converter.
     *
     * @param converter Converter
     * @param player    The player
     */
    BukkitLevelSystem(@NotNull ExpLevelConverter converter, Player player) {
        super(converter);

        this.playerRef = new ExistingWeakReference<>(player);
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
