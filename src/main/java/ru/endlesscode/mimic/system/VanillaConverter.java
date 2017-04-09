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

import org.jetbrains.annotations.NotNull;

/**
 * Converter for vanilla experience system
 *
 * @see <a href="http://minecraft.gamepedia.com/Experience#Leveling_up">Minecraft Wiki: Experience - Leveling Up</a>
 * @author Osip Fatkullin
 * @since 1.0
 */
public class VanillaConverter extends ExpLevelConverter {
    private static VanillaConverter instance;

    private VanillaConverter() {
        // Deny access to constructor from other classes
    }

    static @NotNull VanillaConverter getInstance() {
        if (instance == null) {
            instance = new VanillaConverter();
        }

        return instance;
    }

    /**
     * Converts experience to level
     *
     * @param exp Experience amount
     * @return Level amount
     */
    @Override
    public double expToLevel(int exp) {
        if (exp >= 1628) {
            return (Math.sqrt(72 * exp - 54215) + 325) / 18;
        }

        if (exp >= 394) {
            return Math.sqrt(40 * exp - 7839) / 10 + 8.1;
        }

        if (exp > 0) {
            return Math.sqrt(exp + 9) - 3;
        }

        return 0;
    }

    /**
     * Converts level to exp
     *
     * @param level Player level
     * @return Experience amount to reach given level from 0 exp
     */
    @Override
    public int levelToExp(int level) {
        if (level >= 32) {
            return (int) (4.5*level*level - 162.5*level + 2220);
        }

        if (level >= 17) {
            return (int) (2.5*level*level - 40.5*level + 360);
        }

        return level*level + 6*level;
    }

    /**
     * Gets how much experience you need to reach specified level
     *
     * @param level Current level
     * @return Experience from current to next level
     */
    @Override
    public int getExpToReachNextLevel(int level) {
        if (level >= 31) {
            return 9*level - 158;
        }

        if (level >= 16) {
            return 5*level - 38;
        }

        return 2*level + 7;
    }
}
