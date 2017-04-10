/*
 * This file is part of BukkitMimic.
 * Copyright (C) 2017 Osip Fatkullin
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

package ru.endlesscode.mimic.bukkit;

import org.bukkit.Bukkit;
import org.junit.Before;

/**
 * @author Osip Fatkullin
 * @since 1.0
 */
public class BukkitTest {
    @Before
    public void setUp() {
        if (!bukkitIsReady()) {
            createDummies();
        }
    }

    private static boolean bukkitIsReady() {
        return Bukkit.getServer() != null;
    }

    private void createDummies() {
        Bukkit.setServer(new DummyServer());
    }
}
