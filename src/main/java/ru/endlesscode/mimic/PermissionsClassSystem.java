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

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.ServicePriority;
import ru.endlesscode.mimic.classes.ClassSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class system based on permissions
 * Ta set user class just give him permission by this pattern:
 *      - mimic.class.ClassName
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
@Metadata(systemType = SystemType.CLASS, priority = ServicePriority.Lowest)
public class PermissionsClassSystem implements ClassSystem {
    @Override
    public boolean playerHasClass() {
        return false;
    }

    @Override
    public boolean playerHasRequiredClass(Player player, String requiredClass) {
        return player.hasPermission("mimic.class." + requiredClass);
    }

    @Override
    public String getPlayerPrimaryClass(Player player) {
        List<String> playerClasses = getPlayerClasses(player);

        if (player.isEmpty()) {
            return "";
        }

        return playerClasses.get(0);
    }

    @Override
    public List<String> getPlayerClasses(Player player) {
        return getPlayerPermissionsStartsWith(player, "mimic.class.");
    }
    
    private List<String> getPlayerPermissionsStartsWith(Player player, String starting) {
        List<String> matchedPermissions = new ArrayList<>();
        Set<PermissionAttachmentInfo> perms = player.getEffectivePermissions();
        for (PermissionAttachmentInfo perm : perms) {
            if (perm.getPermission().startsWith(starting)) {
                matchedPermissions.add(perm.getPermission());
            }
        }

        return matchedPermissions;
    }

    @Override
    public String getName() {
        return "Permissions Based";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
