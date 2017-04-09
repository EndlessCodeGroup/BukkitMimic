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

package ru.endlesscode.mimic.system;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;
import ru.endlesscode.mimic.ref.ExistingWeakReference;
import ru.endlesscode.mimic.system.registry.Metadata;
import ru.endlesscode.mimic.system.registry.SystemPriority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class system based on permissions.
 *
 * <p>To set user classes just give him permission like these:
 *      - mimic.class.ClassOne
 *      - mimic.class.ClassTwo
 * First of classes will be primary.
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
@Metadata(priority = SystemPriority.LOWEST)
public class PermissionsClassSystem extends ClassSystem {
    private static final String PERMISSION_PREFIX = "mimic.class.";

    private ExistingWeakReference<Player> playerRef;

    /**
     * Initializes and returns copy of current system
     *
     * @param args Args for initialization
     * @return Initialized system copy
     * @throws CloneNotSupportedException If the object's class does not
     *                  support the {@code Cloneable} interface.
     */
    @Override
    public PermissionsClassSystem initializedCopy(Object... args) throws CloneNotSupportedException {
        PermissionsClassSystem copy = this.clone();
        Player player = (Player) args[0];
        copy.playerRef = new ExistingWeakReference<>(player);
        return copy;
    }

    @Override
    protected PermissionsClassSystem clone() throws CloneNotSupportedException {
        return (PermissionsClassSystem) super.clone();
    }

    /**
     * Checks player has required class
     *
     * @param requiredClass Required class name
     * @return {@code true} if player has required class
     */
    @Override
    public boolean hasRequiredClass(@NotNull String requiredClass) {
        Player player = playerRef.get();
        return player.hasPermission(PERMISSION_PREFIX + requiredClass);
    }

    /**
     * Gets {@code Lost} of player system
     *
     * @apiNote
     * This method actual for systems which support many system for one player.
     * If system not support - it just return {@code List} with one element.
     *
     * @implSpec
     * Method shouldn't return {@code null}, but can return empty {@code List}.
     * Also must not contain null objects.
     *
     * @return {@code List} of player system names
     * @throws IllegalStateException If player-related object not exists.
     */
    @Override
    public @NotNull List<String> getClasses() {
        List<String> matchedPermissions = new ArrayList<>();
        Player player = this.playerRef.get();
        Set<PermissionAttachmentInfo> perms = player.getEffectivePermissions();
        for (PermissionAttachmentInfo perm : perms) {
            String permission = perm.getPermission();
            if (permission.startsWith(PERMISSION_PREFIX)) {
                String theClass = permission.substring(PERMISSION_PREFIX.length());
                matchedPermissions.add(theClass);
            }
        }

        return matchedPermissions;
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
     * @implNote
     * Usually used name of the plugin that implements system.
     *
     * @return name of system
     */
    @Override
    public String getName() {
        return "Permission-based Class System";
    }
}
