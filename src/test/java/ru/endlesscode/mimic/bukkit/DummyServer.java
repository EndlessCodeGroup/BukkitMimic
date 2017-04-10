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

import com.avaje.ebean.config.ServerConfig;
import com.google.common.collect.ImmutableList;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapView;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.SimpleServicesManager;
import org.bukkit.plugin.messaging.ChannelNotRegisteredException;
import org.bukkit.plugin.messaging.MessageTooLargeException;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author Osip Fatkullinatkullin
 * @since 1.0
 */
class DummyServer implements Server {
    private PluginManager pluginManager;
    private ServicesManager servicesManager;

    DummyServer() {
        this.pluginManager = new DummyPluginManager();
        this.servicesManager = new SimpleServicesManager();
    }

    /**
     * Gets the name of this server implementation.
     *
     * @return name of this server implementation
     */
    @Override
    public String getName() {
        return "DummyBukkit";
    }

    /**
     * Gets the version string of this server implementation.
     *
     * @return version of this server implementation
     */
    @Override
    public String getVersion() {
        return "1.0";
    }

    /**
     * Gets the Bukkit version that this server is running.
     *
     * @return version of Bukkit
     */
    @Override
    public String getBukkitVersion() {
        return null;
    }

    /**
     * Gets an array copy of all currently logged in players.
     * <p>
     * This method exists for legacy reasons to provide backwards
     * compatibility. It will not exist at runtime and should not be used
     * under any circumstances.
     *
     * @return an array of Players that are currently online
     * @deprecated superseded by {@link #getOnlinePlayers()}
     */
    @Override
    public Player[] _INVALID_getOnlinePlayers() {
        return new Player[0];
    }

    /**
     * Gets a view of all currently logged in players. This {@linkplain
     * Collections#unmodifiableCollection(Collection) view} is a reused
     * object, making some operations like {@link Collection#size()}
     * zero-allocation.
     * <p>
     * The collection is a view backed by the internal representation, such
     * that, changes to the internal state of the server will be reflected
     * immediately. However, the reuse of the returned collection (identity)
     * is not strictly guaranteed for future or all implementations. Casting
     * the collection, or relying on interface implementations (like {@link
     * Serializable} or {@link List}), is deprecated.
     * <p>
     * Iteration behavior is undefined outside of self-contained main-thread
     * uses. Normal and immediate iterator use without consequences that
     * affect the collection are fully supported. The effects following
     * (non-exhaustive) {@link Entity#teleport(Location) teleportation},
     * {@link Player#setHealth(double) death}, and {@link Player#kickPlayer(
     *String) kicking} are undefined. Any use of this collection from
     * asynchronous threads is unsafe.
     * <p>
     * For safe consequential iteration or mimicking the old array behavior,
     * using {@link Collection#toArray(Object[])} is recommended. For making
     * snapshots, {@link ImmutableList#copyOf(Collection)} is recommended.
     *
     * @return a view of currently online players.
     */
    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        return null;
    }

    /**
     * Get the maximum amount of players which can login to this server.
     *
     * @return the amount of players this server allows
     */
    @Override
    public int getMaxPlayers() {
        return 0;
    }

    /**
     * Get the game port that the server runs on.
     *
     * @return the port number of this server
     */
    @Override
    public int getPort() {
        return 0;
    }

    /**
     * Get the view distance from this server.
     *
     * @return the view distance from this server.
     */
    @Override
    public int getViewDistance() {
        return 0;
    }

    /**
     * Get the IP that this server is bound to, or empty string if not
     * specified.
     *
     * @return the IP string that this server is bound to, otherwise empty
     * string
     */
    @Override
    public String getIp() {
        return null;
    }

    /**
     * Get the name of this server.
     *
     * @return the name of this server
     */
    @Override
    public String getServerName() {
        return null;
    }

    /**
     * Get an ID of this server. The ID is a simple generally alphanumeric ID
     * that can be used for uniquely identifying this server.
     *
     * @return the ID of this server
     */
    @Override
    public String getServerId() {
        return null;
    }

    /**
     * Get world type (level-type setting) for default world.
     *
     * @return the value of level-type (e.g. DEFAULT, FLAT, DEFAULT_1_1)
     */
    @Override
    public String getWorldType() {
        return null;
    }

    /**
     * Get generate-structures setting.
     *
     * @return true if structure generation is enabled, false otherwise
     */
    @Override
    public boolean getGenerateStructures() {
        return false;
    }

    /**
     * Gets whether this server allows the End or not.
     *
     * @return whether this server allows the End or not
     */
    @Override
    public boolean getAllowEnd() {
        return false;
    }

    /**
     * Gets whether this server allows the Nether or not.
     *
     * @return whether this server allows the Nether or not
     */
    @Override
    public boolean getAllowNether() {
        return false;
    }

    /**
     * Gets whether this server has a whitelist or not.
     *
     * @return whether this server has a whitelist or not
     */
    @Override
    public boolean hasWhitelist() {
        return false;
    }

    /**
     * Sets if the server is whitelisted.
     *
     * @param value true for whitelist on, false for off
     */
    @Override
    public void setWhitelist(boolean value) {

    }

    /**
     * Gets a list of whitelisted players.
     *
     * @return a set containing all whitelisted players
     */
    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        return null;
    }

    /**
     * Reloads the whitelist from disk.
     */
    @Override
    public void reloadWhitelist() {

    }

    /**
     * Broadcast a message to all players.
     * <p>
     * This is the same as calling {@link #broadcast(String, * String)} to {@link #BROADCAST_CHANNEL_USERS}
     *
     * @param message the message
     * @return the number of players
     */
    @Override
    public int broadcastMessage(String message) {
        return 0;
    }

    /**
     * Gets the name of the update folder. The update folder is used to safely
     * update plugins at the right moment on a plugin load.
     * <p>
     * The update folder name is relative to the plugins folder.
     *
     * @return the name of the update folder
     */
    @Override
    public String getUpdateFolder() {
        return null;
    }

    /**
     * Gets the update folder. The update folder is used to safely update
     * plugins at the right moment on a plugin load.
     *
     * @return the update folder
     */
    @Override
    public File getUpdateFolderFile() {
        return null;
    }

    /**
     * Gets the value of the connection throttle setting.
     *
     * @return the value of the connection throttle setting
     */
    @Override
    public long getConnectionThrottle() {
        return 0;
    }

    /**
     * Gets default ticks per animal spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn monsters
     * every tick.
     * <li>A value of 400 will mean the server will attempt to spawn monsters
     * every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, animal spawning will be disabled. We
     * recommend using spawn-animals to control this instead.
     * <p>
     * Minecraft default: 400.
     *
     * @return the default ticks per animal spawns value
     */
    @Override
    public int getTicksPerAnimalSpawns() {
        return 0;
    }

    /**
     * Gets the default ticks per monster spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn monsters
     * every tick.
     * <li>A value of 400 will mean the server will attempt to spawn monsters
     * every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, monsters spawning will be disabled. We
     * recommend using spawn-monsters to control this instead.
     * <p>
     * Minecraft default: 1.
     *
     * @return the default ticks per monsters spawn value
     */
    @Override
    public int getTicksPerMonsterSpawns() {
        return 0;
    }

    /**
     * Gets a player object by the given username.
     * <p>
     * This method may not return objects for offline players.
     *
     * @param name the name to look up
     * @return a player if one was found, null otherwise
     * @deprecated Use {@link #getPlayer(UUID)} as player names are no longer
     * guaranteed to be unique
     */
    @Override
    public Player getPlayer(String name) {
        return null;
    }

    /**
     * Gets the player with the exact given name, case insensitive.
     *
     * @param name Exact name of the player to retrieve
     * @return a player object if one was found, null otherwise
     * @deprecated Use {@link #getPlayer(UUID)} as player names are no longer
     * guaranteed to be unique
     */
    @Override
    public Player getPlayerExact(String name) {
        return null;
    }

    /**
     * Attempts to match any players with the given name, and returns a list
     * of all possibly matches.
     * <p>
     * This list is not sorted in any particular order. If an exact match is
     * found, the returned list will only contain a single result.
     *
     * @param name the (partial) name to match
     * @return list of all possible players
     * @deprecated Use {@link #getPlayer(UUID)} as player names are no longer
     * guaranteed to be unique
     */
    @Override
    public List<Player> matchPlayer(String name) {
        return null;
    }

    /**
     * Gets the player with the given UUID.
     *
     * @param id UUID of the player to retrieve
     * @return a player object if one was found, null otherwise
     */
    @Override
    public Player getPlayer(UUID id) {
        return null;
    }

    /**
     * Gets the plugin manager for interfacing with plugins.
     *
     * @return a plugin manager for this Server instance
     */
    @Override
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    /**
     * Gets the scheduler for managing scheduled events.
     *
     * @return a scheduling service for this server
     */
    @Override
    public BukkitScheduler getScheduler() {
        return null;
    }

    /**
     * Gets a services manager.
     *
     * @return s services manager
     */
    @Override
    public ServicesManager getServicesManager() {
        return this.servicesManager;
    }

    /**
     * Gets a list of all worlds on this server.
     *
     * @return a list of worlds
     */
    @Override
    public List<World> getWorlds() {
        return null;
    }

    /**
     * Creates or loads a world with the given name using the specified
     * options.
     * <p>
     * If the world is already loaded, it will just return the equivalent of
     * getWorld(creator.name()).
     *
     * @param creator the options to use when creating the world
     * @return newly created or loaded world
     */
    @Override
    public World createWorld(WorldCreator creator) {
        return null;
    }

    /**
     * Unloads a world with the given name.
     *
     * @param name Name of the world to unload
     * @param save whether to save the chunks before unloading
     * @return true if successful, false otherwise
     */
    @Override
    public boolean unloadWorld(String name, boolean save) {
        return false;
    }

    /**
     * Unloads the given world.
     *
     * @param world the world to unload
     * @param save  whether to save the chunks before unloading
     * @return true if successful, false otherwise
     */
    @Override
    public boolean unloadWorld(World world, boolean save) {
        return false;
    }

    /**
     * Gets the world with the given name.
     *
     * @param name the name of the world to retrieve
     * @return a world with the given name, or null if none exists
     */
    @Override
    public World getWorld(String name) {
        return null;
    }

    /**
     * Gets the world from the given Unique ID.
     *
     * @param uid a unique-id of the world to retrieve
     * @return a world with the given Unique ID, or null if none exists
     */
    @Override
    public World getWorld(UUID uid) {
        return null;
    }

    /**
     * Gets the map from the given item ID.
     *
     * @param id the id of the map to get
     * @return a map view if it exists, or null otherwise
     * @deprecated Magic value
     */
    @Override
    public MapView getMap(short id) {
        return null;
    }

    /**
     * Create a new map with an automatically assigned ID.
     *
     * @param world the world the map will belong to
     * @return a newly created map view
     */
    @Override
    public MapView createMap(World world) {
        return null;
    }

    /**
     * Reloads the server, refreshing settings and plugin information.
     */
    @Override
    public void reload() {

    }

    /**
     * Returns the primary logger associated with this server instance.
     *
     * @return Logger associated with this server
     */
    @Override
    public Logger getLogger() {
        return Logger.getLogger("Dummy Server");
    }

    /**
     * Gets a {@link PluginCommand} with the given name or alias.
     *
     * @param name the name of the command to retrieve
     * @return a plugin command if found, null otherwise
     */
    @Override
    public PluginCommand getPluginCommand(String name) {
        return null;
    }

    /**
     * Writes loaded players to disk.
     */
    @Override
    public void savePlayers() {

    }

    /**
     * Dispatches a command on this server, and executes it if found.
     *
     * @param sender      the apparent sender of the command
     * @param commandLine the command + arguments. Example: <code>test abc
     *                    123</code>
     * @return returns false if no target is found
     * @throws CommandException thrown when the executor for the given command
     *                          fails with an unhandled exception
     */
    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
        return false;
    }

    /**
     * Populates a given {@link ServerConfig} with values attributes to this
     * server.
     *
     * @param config the server config to populate
     */
    @Override
    public void configureDbConfig(ServerConfig config) {

    }

    /**
     * Adds a recipe to the crafting manager.
     *
     * @param recipe the recipe to add
     * @return true if the recipe was added, false if it wasn't for some
     * reason
     */
    @Override
    public boolean addRecipe(Recipe recipe) {
        return false;
    }

    /**
     * Get a list of all recipes for a given item. The stack size is ignored
     * in comparisons. If the durability is -1, it will match any data value.
     *
     * @param result the item to match against recipe results
     * @return a list of recipes with the given result
     */
    @Override
    public List<Recipe> getRecipesFor(ItemStack result) {
        return null;
    }

    /**
     * Get an iterator through the list of crafting recipes.
     *
     * @return an iterator
     */
    @Override
    public Iterator<Recipe> recipeIterator() {
        return null;
    }

    /**
     * Clears the list of crafting recipes.
     */
    @Override
    public void clearRecipes() {

    }

    /**
     * Resets the list of crafting recipes to the default.
     */
    @Override
    public void resetRecipes() {

    }

    /**
     * Gets a list of command aliases defined in the server properties.
     *
     * @return a map of aliases to command names
     */
    @Override
    public Map<String, String[]> getCommandAliases() {
        return null;
    }

    /**
     * Gets the radius, in blocks, around each worlds spawn point to protect.
     *
     * @return spawn radius, or 0 if none
     */
    @Override
    public int getSpawnRadius() {
        return 0;
    }

    /**
     * Sets the radius, in blocks, around each worlds spawn point to protect.
     *
     * @param value new spawn radius, or 0 if none
     */
    @Override
    public void setSpawnRadius(int value) {

    }

    /**
     * Gets whether the Server is in online mode or not.
     *
     * @return true if the server authenticates clients, false otherwise
     */
    @Override
    public boolean getOnlineMode() {
        return false;
    }

    /**
     * Gets whether this server allows flying or not.
     *
     * @return true if the server allows flight, false otherwise
     */
    @Override
    public boolean getAllowFlight() {
        return false;
    }

    /**
     * Gets whether the server is in hardcore mode or not.
     *
     * @return true if the server mode is hardcore, false otherwise
     */
    @Override
    public boolean isHardcore() {
        return false;
    }

    /**
     * Gets whether to use vanilla (false) or exact behaviour (true).
     *
     * <ul>
     * <li>Vanilla behaviour: check for collisions and move the player if
     * needed.
     * <li>Exact behaviour: spawn players exactly where they should be.
     * </ul>
     *
     * @return true if exact location locations are used for spawning, false
     * for vanilla collision detection or otherwise
     * @deprecated non standard and unused feature.
     */
    @Override
    public boolean useExactLoginLocation() {
        return false;
    }

    /**
     * Shutdowns the server, stopping everything.
     */
    @Override
    public void shutdown() {

    }

    /**
     * Broadcasts the specified message to every user with the given
     * permission name.
     *
     * @param message    message to broadcast
     * @param permission the required permission {@link Permissible
     *                   permissibles} must have to receive the broadcast
     * @return number of message recipients
     */
    @Override
    public int broadcast(String message, String permission) {
        return 0;
    }

    /**
     * Gets the player by the given name, regardless if they are offline or
     * online.
     * <p>
     * This method may involve a blocking web request to get the UUID for the
     * given name.
     * <p>
     * This will return an object even if the player does not exist. To this
     * method, all players will exist.
     *
     * @param name the name the player to retrieve
     * @return an offline player
     * @see #getOfflinePlayer(UUID)
     * @deprecated Persistent storage of users should be by UUID as names are no longer
     * unique past a single session.
     */
    @Override
    public OfflinePlayer getOfflinePlayer(String name) {
        return null;
    }

    /**
     * Gets the player by the given UUID, regardless if they are offline or
     * online.
     * <p>
     * This will return an object even if the player does not exist. To this
     * method, all players will exist.
     *
     * @param id the UUID of the player to retrieve
     * @return an offline player
     */
    @Override
    public OfflinePlayer getOfflinePlayer(UUID id) {
        return null;
    }

    /**
     * Gets a set containing all current IPs that are banned.
     *
     * @return a set containing banned IP addresses
     */
    @Override
    public Set<String> getIPBans() {
        return null;
    }

    /**
     * Bans the specified address from the server.
     *
     * @param address the IP address to ban
     */
    @Override
    public void banIP(String address) {

    }

    /**
     * Unbans the specified address from the server.
     *
     * @param address the IP address to unban
     */
    @Override
    public void unbanIP(String address) {

    }

    /**
     * Gets a set containing all banned players.
     *
     * @return a set containing banned players
     */
    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        return null;
    }

    /**
     * Gets a ban list for the supplied type.
     * <p>
     * Bans by name are no longer supported and this method will return
     * null when trying to request them. The replacement is bans by UUID.
     *
     * @param type the type of list to fetch, cannot be null
     * @return a ban list of the specified type
     */
    @Override
    public BanList getBanList(BanList.Type type) {
        return null;
    }

    /**
     * Gets a set containing all player operators.
     *
     * @return a set containing player operators
     */
    @Override
    public Set<OfflinePlayer> getOperators() {
        return null;
    }

    /**
     * Gets the default {@link GameMode} for new players.
     *
     * @return the default game mode
     */
    @Override
    public GameMode getDefaultGameMode() {
        return null;
    }

    /**
     * Sets the default {@link GameMode} for new players.
     *
     * @param mode the new game mode
     */
    @Override
    public void setDefaultGameMode(GameMode mode) {

    }

    /**
     * Gets a {@link ConsoleCommandSender} that may be used as an input source
     * for this server.
     *
     * @return a console command sender
     */
    @Override
    public ConsoleCommandSender getConsoleSender() {
        return null;
    }

    /**
     * Gets the folder that contains all of the various {@link World}s.
     *
     * @return folder that contains all worlds
     */
    @Override
    public File getWorldContainer() {
        return null;
    }

    /**
     * Gets every player that has ever played on this server.
     *
     * @return an array containing all previous players
     */
    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        return new OfflinePlayer[0];
    }

    /**
     * Gets the {@link Messenger} responsible for this server.
     *
     * @return messenger responsible for this server
     */
    @Override
    public Messenger getMessenger() {
        return null;
    }

    /**
     * Gets the {@link HelpMap} providing help topics for this server.
     *
     * @return a help map for this server
     */
    @Override
    public HelpMap getHelpMap() {
        return null;
    }

    /**
     * Creates an empty inventory of the specified type. If the type is {@link
     * InventoryType#CHEST}, the new inventory has a size of 27; otherwise the
     * new inventory has the normal size for its type.
     *
     * @param owner the holder of the inventory, or null to indicate no holder
     * @param type  the type of inventory to create
     * @return a new inventory
     */
    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type) {
        return null;
    }

    /**
     * Creates an empty inventory with the specified type and title. If the type
     * is {@link InventoryType#CHEST}, the new inventory has a size of 27;
     * otherwise the new inventory has the normal size for its type.<br>
     * It should be noted that some inventory types do not support titles and
     * may not render with said titles on the Minecraft client.
     *
     * @param owner The holder of the inventory; can be null if there's no holder.
     * @param type  The type of inventory to create.
     * @param title The title of the inventory, to be displayed when it is viewed.
     * @return The new inventory.
     */
    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
        return null;
    }

    /**
     * Creates an empty inventory of type {@link InventoryType#CHEST} with the
     * specified size.
     *
     * @param owner the holder of the inventory, or null to indicate no holder
     * @param size  a multiple of 9 as the size of inventory to create
     * @return a new inventory
     * @throws IllegalArgumentException if the size is not a multiple of 9
     */
    @Override
    public Inventory createInventory(InventoryHolder owner, int size) throws IllegalArgumentException {
        return null;
    }

    /**
     * Creates an empty inventory of type {@link InventoryType#CHEST} with the
     * specified size and title.
     *
     * @param owner the holder of the inventory, or null to indicate no holder
     * @param size  a multiple of 9 as the size of inventory to create
     * @param title the title of the inventory, displayed when inventory is
     *              viewed
     * @return a new inventory
     * @throws IllegalArgumentException if the size is not a multiple of 9
     */
    @Override
    public Inventory createInventory(InventoryHolder owner, int size, String title) throws IllegalArgumentException {
        return null;
    }

    /**
     * Gets user-specified limit for number of monsters that can spawn in a
     * chunk.
     *
     * @return the monster spawn limit
     */
    @Override
    public int getMonsterSpawnLimit() {
        return 0;
    }

    /**
     * Gets user-specified limit for number of animals that can spawn in a
     * chunk.
     *
     * @return the animal spawn limit
     */
    @Override
    public int getAnimalSpawnLimit() {
        return 0;
    }

    /**
     * Gets user-specified limit for number of water animals that can spawn in
     * a chunk.
     *
     * @return the water animal spawn limit
     */
    @Override
    public int getWaterAnimalSpawnLimit() {
        return 0;
    }

    /**
     * Gets user-specified limit for number of ambient mobs that can spawn in
     * a chunk.
     *
     * @return the ambient spawn limit
     */
    @Override
    public int getAmbientSpawnLimit() {
        return 0;
    }

    /**
     * Checks the current thread against the expected primary thread for the
     * server.
     * <p>
     * <b>Note:</b> this method should not be used to indicate the current
     * synchronized state of the runtime. A current thread matching the main
     * thread indicates that it is synchronized, but a mismatch <b>does not
     * preclude</b> the same assumption.
     *
     * @return true if the current thread matches the expected primary thread,
     * false otherwise
     */
    @Override
    public boolean isPrimaryThread() {
        return false;
    }

    /**
     * Gets the message that is displayed on the server list.
     *
     * @return the servers MOTD
     */
    @Override
    public String getMotd() {
        return null;
    }

    /**
     * Gets the default message that is displayed when the server is stopped.
     *
     * @return the shutdown message
     */
    @Override
    public String getShutdownMessage() {
        return null;
    }

    /**
     * Gets the current warning state for the server.
     *
     * @return the configured warning state
     */
    @Override
    public Warning.WarningState getWarningState() {
        return null;
    }

    /**
     * Gets the instance of the item factory (for {@link ItemMeta}).
     *
     * @return the item factory
     * @see ItemFactory
     */
    @Override
    public ItemFactory getItemFactory() {
        return null;
    }

    /**
     * Gets the instance of the scoreboard manager.
     * <p>
     * This will only exist after the first world has loaded.
     *
     * @return the scoreboard manager or null if no worlds are loaded.
     */
    @Override
    public ScoreboardManager getScoreboardManager() {
        return null;
    }

    /**
     * Gets an instance of the server's default server-icon.
     *
     * @return the default server-icon; null values may be used by the
     * implementation to indicate no defined icon, but this behavior is
     * not guaranteed
     */
    @Override
    public CachedServerIcon getServerIcon() {
        return null;
    }

    /**
     * Loads an image from a file, and returns a cached image for the specific
     * server-icon.
     * <p>
     * Size and type are implementation defined. An incompatible file is
     * guaranteed to throw an implementation-defined {@link Exception}.
     *
     * @param file the file to load the from
     * @return a cached server-icon that can be used for a {@link
     * ServerListPingEvent#setServerIcon(CachedServerIcon)}
     * @throws IllegalArgumentException if image is null
     * @throws Exception                if the image does not meet current server server-icon
     *                                  specifications
     */
    @Override
    public CachedServerIcon loadServerIcon(File file) throws Exception {
        return null;
    }

    /**
     * Creates a cached server-icon for the specific image.
     * <p>
     * Size and type are implementation defined. An incompatible file is
     * guaranteed to throw an implementation-defined {@link Exception}.
     *
     * @param image the image to use
     * @return a cached server-icon that can be used for a {@link
     * ServerListPingEvent#setServerIcon(CachedServerIcon)}
     * @throws IllegalArgumentException if image is null
     * @throws Exception                if the image does not meet current server
     *                                  server-icon specifications
     */
    @Override
    public CachedServerIcon loadServerIcon(BufferedImage image) throws Exception {
        return null;
    }

    /**
     * Set the idle kick timeout. Any players idle for the specified amount of
     * time will be automatically kicked.
     * <p>
     * A value of 0 will disable the idle kick timeout.
     *
     * @param threshold the idle timeout in minutes
     */
    @Override
    public void setIdleTimeout(int threshold) {

    }

    /**
     * Gets the idle kick timeout.
     *
     * @return the idle timeout in minutes
     */
    @Override
    public int getIdleTimeout() {
        return 0;
    }

    /**
     * Create a ChunkData for use in a generator.
     *
     * See {@link ChunkGenerator#generateChunkData(World, Random, int, int, ChunkGenerator.BiomeGrid)}
     *
     * @param world the world to create the ChunkData for
     * @return a new ChunkData for the world
     */
    @Override
    public ChunkGenerator.ChunkData createChunkData(World world) {
        return null;
    }

    /**
     * Creates a boss bar instance to display to players. The progress
     * defaults to 1.0
     *
     * @param title the title of the boss bar
     * @param color the color of the boss bar
     * @param style the style of the boss bar
     * @param flags an optional list of flags to set on the boss bar
     * @return the created boss bar
     */
    @Override
    public BossBar createBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
        return null;
    }

    /**
     * @return the unsafe values instance
     * @see UnsafeValues
     */
    @SuppressWarnings("deprecation")
    @Override
    @Deprecated
    public UnsafeValues getUnsafe() {
        return null;
    }

    /**
     * Sends this recipient a Plugin Message on the specified outgoing
     * channel.
     * <p>
     * The message may not be larger than {@link Messenger#MAX_MESSAGE_SIZE}
     * bytes, and the plugin must be registered to send messages on the
     * specified channel.
     *
     * @param source  The plugin that sent this message.
     * @param channel The channel to send this message on.
     * @param message The raw message to send.
     * @throws IllegalArgumentException      Thrown if the source plugin is
     *                                       disabled.
     * @throws IllegalArgumentException      Thrown if source, channel or message
     *                                       is null.
     * @throws MessageTooLargeException      Thrown if the message is too big.
     * @throws ChannelNotRegisteredException Thrown if the channel is not
     *                                       registered for this plugin.
     */
    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {

    }

    /**
     * Gets a set containing all the Plugin Channels that this client is
     * listening on.
     *
     * @return Set containing all the channels that this client may accept.
     */
    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }
}
