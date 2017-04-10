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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.junit.Before;
import org.junit.Test;
import ru.endlesscode.mimic.bukkit.BukkitTest;
import ru.endlesscode.mimic.bukkit.DummyPlayer;
import ru.endlesscode.mimic.bukkit.DummyPlugin;
import ru.endlesscode.mimic.system.*;
import ru.endlesscode.mimic.system.registry.SystemNotFoundException;
import ru.endlesscode.mimic.system.registry.SystemPriority;

import static org.junit.Assert.*;

/**
 * @author Osip Fatkullin
 * @since 1.0
 */
public class BukkitSystemRegistryTest extends BukkitTest {
    private BukkitSystemRegistry systemRegistry;
    private Player dummyPlayer;

    @Before
    public void setUp() {
        super.setUp();

        Plugin plugin = DummyPlugin.getInstance();
        ServicesManager servicesManager = Bukkit.getServicesManager();
        servicesManager.unregisterAll(plugin);

        this.systemRegistry = new BukkitSystemRegistry(plugin, servicesManager);
        this.dummyPlayer = new DummyPlayer();
    }

    @Test
    public void testRegisterRightSubsystemMustBeSuccessful() throws Exception {
        this.systemRegistry.registerSubsystem(new VanillaLevelSystem());
        this.systemRegistry.registerSubsystem(new PermissionsClassSystem());

        LevelSystem levelSystem = this.systemRegistry.getSystem(LevelSystem.class, dummyPlayer);
        ClassSystem classSystem = this.systemRegistry.getSystem(ClassSystem.class, dummyPlayer);

        assertNotNull("System must be initialized", levelSystem);
        assertNotNull("System must be initialized", classSystem);
    }

    @Test(expected = SystemNotFoundException.class)
    public void testGetNotRegisteredSystemMustThrowException() throws Exception {
        this.systemRegistry.getSystem(TestSystem.class, dummyPlayer);
    }

    @Test(expected = CloneNotSupportedException.class)
    public void testGetNotInitializableSystemMustThrowException() throws Exception {
        this.systemRegistry.registerSubsystem(new NotInitializableSystem());

        this.systemRegistry.getSystem(TestSystem.class, dummyPlayer);
    }

    @Test
    public void testGetServicePriorityFromSystem() throws Exception {
        assertEquals(ServicePriority.Lowest, BukkitSystemRegistry.servicePriorityFromSystem(SystemPriority.LOWEST));
        assertEquals(ServicePriority.Low, BukkitSystemRegistry.servicePriorityFromSystem(SystemPriority.LOW));
        assertEquals(ServicePriority.Normal, BukkitSystemRegistry.servicePriorityFromSystem(SystemPriority.NORMAL));
        assertEquals(ServicePriority.High, BukkitSystemRegistry.servicePriorityFromSystem(SystemPriority.HIGH));
        assertEquals(ServicePriority.Highest, BukkitSystemRegistry.servicePriorityFromSystem(SystemPriority.HIGHEST));
    }

    @Test
    public void testUnregisterAllSubsystemsMustBeSuccessful() throws Exception {
        this.systemRegistry.registerSubsystem(new VanillaLevelSystem());
        this.systemRegistry.registerSubsystem(new PermissionsClassSystem());

        this.systemRegistry.unregisterAllSubsystems();

        checkSystemExistence(LevelSystem.class);
        checkSystemExistence(ClassSystem.class);
    }

    @Test
    public void testUnregisterExistingSubsystemMustBeSuccessful() throws Exception {
        PermissionsClassSystem subsystem = new PermissionsClassSystem();
        this.systemRegistry.registerSubsystem(subsystem);
        this.systemRegistry.unregisterSubsystem(subsystem);

        checkSystemExistence(ClassSystem.class);
    }

    @Test
    public void testUnregisterNotExistingSubsystemMustBeSuccessful() throws Exception {
        PermissionsClassSystem subsystem = new PermissionsClassSystem();
        this.systemRegistry.unregisterSubsystem(subsystem);

        checkSystemExistence(ClassSystem.class);
    }

    private <SystemT extends PlayerSystem> void checkSystemExistence(Class<SystemT> systemClass) throws Exception {
        try {
            this.systemRegistry.getSystem(systemClass, dummyPlayer);
        } catch (SystemNotFoundException ignored) {
            return;
        }

        fail("System must be not registered");
    }
}