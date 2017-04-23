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
import ru.endlesscode.mimic.api.system.LevelSystem;
import ru.endlesscode.mimic.api.system.registry.Metadata;
import ru.endlesscode.mimic.api.system.registry.SystemPriority;

/**
 * Vanilla experience bar system
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
@Metadata(priority = SystemPriority.LOWEST)
public class VanillaLevelSystem extends BukkitLevelSystem {
    public static final LevelSystem.Factory FACTORY =
            new LevelSystem.Factory(playerObj -> new VanillaLevelSystem((Player) playerObj));

    public VanillaLevelSystem(@NotNull Player player) {
        super(VanillaConverter.getInstance(), player);
    }

    /**
     * Gets current experience level of player.
     *
     * @return Current experience level
     * @throws IllegalStateException If player-related object not exists
     */
    @Override
    public int getLevel() {
        Player player = playerRef.get();
        return player.getLevel();
    }

    /**
     * Sets current experience level for player.
     *
     * @apiNote
     * Argument can not be lesser than zero.
     *
     * @param newLevel New experience level
     * @throws IllegalStateException If player-related object not exists
     */
    @Override
    public void setLevel(int newLevel) {
        int allowedLevel = Math.max(0, newLevel);

        Player player = playerRef.get();
        player.setLevel(allowedLevel);
    }

    /**
     * Gets player's current level experience points
     *
     * @apiNote
     * This method returns experience on current level, to get total player
     * experience use {@link #getTotalExp()}
     *
     * @return Current level experience points
     * @throws IllegalStateException If player-related object not exists
     */
    @Override
    public int getExp() {
        int level = getLevel();
        int expToLevel = converter.getExpToReachNextLevel(level);

        Player player = playerRef.get();
        return (int) (expToLevel * player.getExp());
    }

    /**
     * Sets player's current level experience points.
     *
     * @apiNote
     * Be careful with this method! To change experience value better to use
     * {@link #giveExp(int)} and {@link #takeExp(int)}. This method changes
     * experience on current level, to set total player experience use
     * {@link #setTotalExp(int)}. New experience value shouldn't be less than 0
     * and bigger than maximal possible XP on current level.
     *
     * @implNote
     * You should add argument value validation to your implementation because
     * new value may be bigger than maximal possible experience on current level,
     * and you must trim it to the limit.
     *
     * @param newExperience New level experience points
     * @throws IllegalStateException If player-related object not exists
     */
    @Override
    public void setExp(int newExperience) {
        int level = getLevel();
        int expToNextLevel = converter.getExpToReachNextLevel(level);
        float allowedExperience = Math.max(0, newExperience);
        allowedExperience = Math.min(allowedExperience, expToNextLevel);

        Player player = playerRef.get();
        player.setExp(allowedExperience / expToNextLevel);
    }

    /**
     * Gets player's current fractional XP
     *
     * @apiNote
     * This is a percentage value. 0 is "no progress" and 1 is "next level".
     *
     * @return Current fractional XP.
     */
    public double getFractionalExp() {
        Player player = playerRef.get();
        return player.getExp();
    }

    /**
     * Sets player's current fractional XP
     *
     * @apiNote
     * This is a percentage value. 0 is "no progress" and 1 is "next level".
     *
     * @param fractionalExp Fractional XP value between 0 ans 1.
     */
    @Override
    public void setFractionalExp(double fractionalExp) {
        Player player = playerRef.get();

        float allowedExp = Math.min(1, (float) fractionalExp);
        allowedExp = Math.max(0, allowedExp);

        player.setExp(allowedExp);
    }

    /**
     * Get the total amount of experience required for the player to reach level.
     *
     * @return Experience required to level up
     * @throws IllegalStateException If player-related object not exists
     */
    @Override
    public int getExpToNextLevel() {
        Player player = playerRef.get();
        return player.getExpToLevel();
    }

    /**
     * Checks if this system is found and enabled
     *
     * @return {@code true} if works, otherwise {@code false}
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the name of system.
     *
     * @implNote Usually used name of the plugin that implements system.
     *
     * @return name of system
     */
    @Override
    public String getName() {
        return "Vanilla Level System";
    }
}
