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

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.*;

import java.io.File;
import java.util.Set;

/**
 * @author Osip Fatkullin
 * @since 1.0
 */
class DummyPluginManager implements PluginManager {
    /**
     * Registers the specified plugin loader
     *
     * @param loader Class name of the PluginLoader to register
     * @throws IllegalArgumentException Thrown when the given Class is not a
     *                                  valid PluginLoader
     */
    @Override
    public void registerInterface(Class<? extends PluginLoader> loader) throws IllegalArgumentException {

    }

    /**
     * Checks if the given plugin is loaded and returns it when applicable
     * <p>
     * Please note that the name of the plugin is case-sensitive
     *
     * @param name Name of the plugin to check
     * @return Plugin if it exists, otherwise null
     */
    @Override
    public Plugin getPlugin(String name) {
        return null;
    }

    /**
     * Gets a list of all currently loaded plugins
     *
     * @return Array of Plugins
     */
    @Override
    public Plugin[] getPlugins() {
        return new Plugin[0];
    }

    /**
     * Checks if the given plugin is enabled or not
     * <p>
     * Please note that the name of the plugin is case-sensitive.
     *
     * @param name Name of the plugin to check
     * @return true if the plugin is enabled, otherwise false
     */
    @Override
    public boolean isPluginEnabled(String name) {
        return false;
    }

    /**
     * Checks if the given plugin is enabled or not
     *
     * @param plugin Plugin to check
     * @return true if the plugin is enabled, otherwise false
     */
    @Override
    public boolean isPluginEnabled(Plugin plugin) {
        return false;
    }

    /**
     * Loads the plugin in the specified file
     * <p>
     * File must be valid according to the current enabled Plugin interfaces
     *
     * @param file File containing the plugin to load
     * @return The Plugin loaded, or null if it was invalid
     * @throws InvalidPluginException      Thrown when the specified file is not a
     *                                     valid plugin
     * @throws InvalidDescriptionException Thrown when the specified file
     *                                     contains an invalid description
     * @throws UnknownDependencyException  If a required dependency could not
     *                                     be resolved
     */
    @Override
    public Plugin loadPlugin(File file) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException {
        return null;
    }

    /**
     * Loads the plugins contained within the specified directory
     *
     * @param directory Directory to check for plugins
     * @return A list of all plugins loaded
     */
    @Override
    public Plugin[] loadPlugins(File directory) {
        return new Plugin[0];
    }

    /**
     * Disables all the loaded plugins
     */
    @Override
    public void disablePlugins() {

    }

    /**
     * Disables and removes all plugins
     */
    @Override
    public void clearPlugins() {

    }

    /**
     * Calls an event with the given details
     *
     * @param event Event details
     * @throws IllegalStateException Thrown when an asynchronous event is
     *                               fired from synchronous code.
     *                               <p>
     *                               <i>Note: This is best-effort basis, and should not be used to test
     *                               synchronized state. This is an indicator for flawed flow logic.</i>
     */
    @Override
    public void callEvent(Event event) throws IllegalStateException {

    }

    /**
     * Registers all the events in the given listener class
     *
     * @param listener Listener to register
     * @param plugin   Plugin to register
     */
    @Override
    public void registerEvents(Listener listener, Plugin plugin) {

    }

    /**
     * Registers the specified executor to the given event class
     *
     * @param event    Event type to register
     * @param listener Listener to register
     * @param priority Priority to register this event at
     * @param executor EventExecutor to register
     * @param plugin   Plugin to register
     */
    @Override
    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin) {

    }

    /**
     * Registers the specified executor to the given event class
     *
     * @param event           Event type to register
     * @param listener        Listener to register
     * @param priority        Priority to register this event at
     * @param executor        EventExecutor to register
     * @param plugin          Plugin to register
     * @param ignoreCancelled Whether to pass cancelled events or not
     */
    @Override
    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin, boolean ignoreCancelled) {

    }

    /**
     * Enables the specified plugin
     * <p>
     * Attempting to enable a plugin that is already enabled will have no
     * effect
     *
     * @param plugin Plugin to enable
     */
    @Override
    public void enablePlugin(Plugin plugin) {

    }

    /**
     * Disables the specified plugin
     * <p>
     * Attempting to disable a plugin that is not enabled will have no effect
     *
     * @param plugin Plugin to disable
     */
    @Override
    public void disablePlugin(Plugin plugin) {

    }

    /**
     * Gets a {@link Permission} from its fully qualified name
     *
     * @param name Name of the permission
     * @return Permission, or null if none
     */
    @Override
    public Permission getPermission(String name) {
        return null;
    }

    /**
     * Adds a {@link Permission} to this plugin manager.
     * <p>
     * If a permission is already defined with the given name of the new
     * permission, an exception will be thrown.
     *
     * @param perm Permission to add
     * @throws IllegalArgumentException Thrown when a permission with the same
     *                                  name already exists
     */
    @Override
    public void addPermission(Permission perm) {

    }

    /**
     * Removes a {@link Permission} registration from this plugin manager.
     * <p>
     * If the specified permission does not exist in this plugin manager,
     * nothing will happen.
     * <p>
     * Removing a permission registration will <b>not</b> remove the
     * permission from any {@link Permissible}s that have it.
     *
     * @param perm Permission to remove
     */
    @Override
    public void removePermission(Permission perm) {

    }

    /**
     * Removes a {@link Permission} registration from this plugin manager.
     * <p>
     * If the specified permission does not exist in this plugin manager,
     * nothing will happen.
     * <p>
     * Removing a permission registration will <b>not</b> remove the
     * permission from any {@link Permissible}s that have it.
     *
     * @param name Permission to remove
     */
    @Override
    public void removePermission(String name) {

    }

    /**
     * Gets the default permissions for the given op status
     *
     * @param op Which set of default permissions to get
     * @return The default permissions
     */
    @Override
    public Set<Permission> getDefaultPermissions(boolean op) {
        return null;
    }

    /**
     * Recalculates the defaults for the given {@link Permission}.
     * <p>
     * This will have no effect if the specified permission is not registered
     * here.
     *
     * @param perm Permission to recalculate
     */
    @Override
    public void recalculatePermissionDefaults(Permission perm) {

    }

    /**
     * Subscribes the given Permissible for information about the requested
     * Permission, by name.
     * <p>
     * If the specified Permission changes in any form, the Permissible will
     * be asked to recalculate.
     *
     * @param permission  Permission to subscribe to
     * @param permissible Permissible subscribing
     */
    @Override
    public void subscribeToPermission(String permission, Permissible permissible) {

    }

    /**
     * Unsubscribes the given Permissible for information about the requested
     * Permission, by name.
     *
     * @param permission  Permission to unsubscribe from
     * @param permissible Permissible subscribing
     */
    @Override
    public void unsubscribeFromPermission(String permission, Permissible permissible) {

    }

    /**
     * Gets a set containing all subscribed {@link Permissible}s to the given
     * permission, by name
     *
     * @param permission Permission to query for
     * @return Set containing all subscribed permissions
     */
    @Override
    public Set<Permissible> getPermissionSubscriptions(String permission) {
        return null;
    }

    /**
     * Subscribes to the given Default permissions by operator status
     * <p>
     * If the specified defaults change in any form, the Permissible will be
     * asked to recalculate.
     *
     * @param op          Default list to subscribe to
     * @param permissible Permissible subscribing
     */
    @Override
    public void subscribeToDefaultPerms(boolean op, Permissible permissible) {

    }

    /**
     * Unsubscribes from the given Default permissions by operator status
     *
     * @param op          Default list to unsubscribe from
     * @param permissible Permissible subscribing
     */
    @Override
    public void unsubscribeFromDefaultPerms(boolean op, Permissible permissible) {

    }

    /**
     * Gets a set containing all subscribed {@link Permissible}s to the given
     * default list, by op status
     *
     * @param op Default list to query for
     * @return Set containing all subscribed permissions
     */
    @Override
    public Set<Permissible> getDefaultPermSubscriptions(boolean op) {
        return null;
    }

    /**
     * Gets a set of all registered permissions.
     * <p>
     * This set is a copy and will not be modified live.
     *
     * @return Set containing all current registered permissions
     */
    @Override
    public Set<Permission> getPermissions() {
        return null;
    }

    /**
     * Returns whether or not timing code should be used for event calls
     *
     * @return True if event timings are to be used
     */
    @Override
    public boolean useTimings() {
        return false;
    }
}
