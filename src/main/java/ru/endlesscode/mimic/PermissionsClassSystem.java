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
