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

package ru.endlesscode.mimic;

import com.google.common.annotations.VisibleForTesting;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.jetbrains.annotations.NotNull;
import ru.endlesscode.mimic.system.PlayerSystem;
import ru.endlesscode.mimic.system.registry.MetadataAdapter;
import ru.endlesscode.mimic.system.registry.SystemNotFoundException;
import ru.endlesscode.mimic.system.registry.SystemPriority;
import ru.endlesscode.mimic.system.registry.SystemRegistry;

/**
 * Implementation of system registry for bukkit.
 * Using {@link org.bukkit.plugin.ServicesManager}
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class BukkitSystemRegistry extends SystemRegistry {
    private final Plugin plugin;
    private final ServicesManager servicesManager;

    BukkitSystemRegistry(Plugin plugin, ServicesManager servicesManager) {
        this.plugin = plugin;
        this.servicesManager = servicesManager;
    }

    /**
     * Registers approved subsystem.
     *
     * @param subsystem Instance of the subsystem
     * @param meta      Subsystem metadata
     */
    @Override
    protected <SubsystemT extends PlayerSystem> void registerSystem(
            @NotNull SubsystemT subsystem,
            @NotNull MetadataAdapter meta) {
        //noinspection unchecked
        Class<? super SubsystemT> systemClass = meta.getSystemClass();
        ServicePriority priority = servicePriorityFromSystem(meta.getPriority());
        this.servicesManager.register(systemClass, subsystem, this.plugin, priority);
    }

    /**
     * Utility method that returns bukkit analog for {@code SystemPriority}
     *
     * @param priority System priority
     * @return Same service priority
     */
    @VisibleForTesting
    static @NotNull ServicePriority servicePriorityFromSystem(@NotNull SystemPriority priority) {
        int priorityIndex = priority.ordinal();
        return ServicePriority.values()[priorityIndex];
    }

    /**
     * Gets system assigned to specified bukkit player.
     *
     * @param systemTypeClass System type class
     * @param player          Bukkit player
     * @return System assigned to given player
     * @throws SystemNotFoundException If needed system not registered
     */
    @SuppressWarnings("WeakerAccess")
    public @NotNull <SystemT extends PlayerSystem> SystemT getSystem(
            @NotNull Class<SystemT> systemTypeClass,
            Player player)
            throws SystemNotFoundException, CloneNotSupportedException {
        return this.getSystem(systemTypeClass, (Object) player);
    }

    /**
     * Gets system assigned to specified player.
     *
     * @implNote
     * Use pattern Prototype to initialize new system objects. All subsystems
     * contains method {@link PlayerSystem#initializedCopy(Object...)} for this.
     *
     * @implSpec
     * Never return {@code null}. Throw exception instead. Also you must create
     * public method that will use this method, and filter args.
     *
     * @param systemTypeClass System type class
     * @param args            Arguments that needed to initialize system
     * @return System assigned to player
     * @throws SystemNotFoundException If needed system not found in registry
     */
    @Override
    protected @NotNull <SystemT extends PlayerSystem> SystemT getSystem(
            @NotNull Class<SystemT> systemTypeClass,
            Object... args)
            throws SystemNotFoundException, CloneNotSupportedException {
        RegisteredServiceProvider<SystemT> systemProvider = this.servicesManager.getRegistration(systemTypeClass);
        if (systemProvider == null) {
            throw new SystemNotFoundException(
                    String.format("No one system '%s' found", systemTypeClass.getSimpleName()));
        }

        //noinspection unchecked
        return (SystemT) systemProvider.getProvider().initializedCopy(args);
    }

    /**
     * Unregisters all subsystems
     *
     * @apiNote Use it before plugin disabling
     */
    @Override
    public void unregisterAllSubsystems() {
        servicesManager.unregisterAll(this.plugin);
    }

    /**
     * Unregister specified subsystem
     *
     * @param subsystem The subsystem
     */
    @Override
    public <SubsystemT extends PlayerSystem> void unregisterSubsystem(@NotNull SubsystemT subsystem) {
        servicesManager.unregister(subsystem);
    }
}
