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
import ru.endlesscode.mimic.system.PermissionsClassSystem;
import ru.endlesscode.mimic.system.PlayerSystem;
import ru.endlesscode.mimic.system.VanillaLevelSystem;
import ru.endlesscode.mimic.system.registry.SystemNotNeededException;
import ru.endlesscode.mimic.system.registry.SystemNotRegisteredException;
import ru.endlesscode.mimic.system.registry.SystemRegistry;

import java.util.logging.Logger;

/**
 * Main class of the plugin
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class BukkitMimic extends JavaPlugin {
    private static Logger log;

    private SystemRegistry systemRegistry;

    @Override
    public void onEnable() {
        log = this.getLogger();

        initRegistry();
        hookDefaultSystems();
    }

    private void initRegistry() {
        ServicesManager sm = getServer().getServicesManager();
        this.systemRegistry = new BukkitSystemRegistry(this, sm);
    }

    private void hookDefaultSystems() {
        this.hookSystem(new VanillaLevelSystem());
        this.hookSystem(new PermissionsClassSystem());
    }

    private void hookSystem(PlayerSystem system) {
        try {
            this.systemRegistry.registerSubsystem(system);
        } catch (SystemNotRegisteredException e) {
            log.warning(e.getMessage());
        } catch (SystemNotNeededException ignored) {}
    }

    @Override
    public void onDisable() {
        this.systemRegistry.unregisterAllSubsystems();
    }

    /**
     * @return Mimic system registry
     */
    @SuppressWarnings("unused")
    public SystemRegistry getSystemRegistry() {
        return systemRegistry;
    }
}
