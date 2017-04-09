/*
 * This file is part of Mimic.
 * Copyright (C) 2017 osipf
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

package ru.endlesscode.mimic.system;

import org.bukkit.entity.Player;
import ru.endlesscode.mimic.ref.ExistingWeakReference;
import ru.endlesscode.mimic.system.registry.Metadata;
import ru.endlesscode.mimic.system.registry.SystemPriority;

/**
 * Vanilla experience bar system
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
@Metadata(priority = SystemPriority.LOWEST)
public class VanillaLevelSystem extends LevelSystem {
    private final ExistingWeakReference<Player> playerRef;

    protected VanillaLevelSystem(Player player) {
        super(VanillaConverter.getInstance());

        this.playerRef = new ExistingWeakReference<>(player);
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
        int allowedExperience = Math.max(0, newExperience);
        allowedExperience = Math.min(allowedExperience - 1, expToNextLevel);

        Player player = playerRef.get();
        player.setExp(allowedExperience / expToNextLevel);
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
