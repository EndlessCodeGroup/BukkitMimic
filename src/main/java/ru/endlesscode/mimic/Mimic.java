package ru.endlesscode.mimic;

import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.endlesscode.mimic.levels.VanillaLevelSystem;
import ru.endlesscode.mimic.util.ClassUtil;
import ru.endlesscode.mimic.util.MetadataAdapter;

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
        hookSystem(VanillaLevelSystem.class);
        hookSystem(PermissionsClassSystem.class);
    }

    private <T extends BaseSystem> void hookSystem(Class<T> hookClass) {
        try {
            tryToHookSystem(hookClass);
        } catch (Exception e) {
            log.severe("[Hook] " + e.getMessage());
        }
    }

    private <T extends BaseSystem> void tryToHookSystem(Class<T> hookClass)
            throws MimicClassException {
        MetadataAdapter meta = ClassUtil.getNotNullClassMeta(hookClass);
        if (!meta.requiredClassesExists()) {
            return;
        }

        T hookedSystem = ClassUtil.createClassInstance(hookClass);
        sm.register(meta.getSystemClass(), hookedSystem, this, meta.getPriority());

        String status = hookedSystem.isEnabled() ? "Loaded" : "Waiting";
        log.info(String.format("[" + meta.getSystemName() + "] %s found: %s", hookedSystem.getName(), status));
    }
}
