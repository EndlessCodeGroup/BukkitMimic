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

package ru.endlesscode.mimic.bukkit;

import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.endlesscode.mimic.api.system.PlayerSystem;
import ru.endlesscode.mimic.api.system.registry.SystemNotNeededException;
import ru.endlesscode.mimic.api.system.registry.SystemNotRegisteredException;
import ru.endlesscode.mimic.bukkit.system.PermissionsClassSystem;
import ru.endlesscode.mimic.bukkit.system.VanillaLevelSystem;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Main class of the plugin
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class BukkitMimic extends JavaPlugin {
    private static BukkitMimic instance;
    private static Logger log;

    private BukkitSystemRegistry systemRegistry;

    /**
     * All subsystems
     */
    private final List<Class<? extends PlayerSystem>> defaultSubsystems = Arrays.asList(
            VanillaLevelSystem.class, PermissionsClassSystem.class
    );

    @Override
    public void onEnable() {
        instance = this;
        log = this.getLogger();

        this.initRegistry();
        this.hookDefaultSystems();
    }

    private void initRegistry() {
        ServicesManager sm = this.getServer().getServicesManager();
        this.systemRegistry = new BukkitSystemRegistry(this, sm);
    }

    private void hookDefaultSystems() {
        defaultSubsystems.forEach(this::hookSystem);
    }

    private <T extends PlayerSystem> void hookSystem(Class<? extends T> system) {
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
    @SuppressWarnings({"unused", "WeakerAccess"})
    public static BukkitSystemRegistry getSystemRegistry() {
        return instance.systemRegistry;
    }
}
