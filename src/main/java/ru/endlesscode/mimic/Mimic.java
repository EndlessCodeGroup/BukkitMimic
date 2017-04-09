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

package ru.endlesscode.mimic;

import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Main Plugin class
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class Mimic extends JavaPlugin {
    private static Logger log;

    private ServicesManager sm;

    @Override
    public void onEnable() {
        log = this.getLogger();
        this.sm = this.getServer().getServicesManager();

        hookInternalSystems();
    }

    @Override
    public void onDisable() {
        this.sm.unregisterAll(this);
    }

    private void hookInternalSystems() {
//        hookSystem(VanillaLevelSystem.class);
//        hookSystem(PermissionsClassSystem.class);
    }
}
