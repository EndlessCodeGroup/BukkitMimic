package ru.endlesscode.mimic.util;

import ru.endlesscode.mimic.BaseSystem;

/**
 * {@code BaseSystem} implementation with private constructor
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class PrivateBaseSystemImpl implements BaseSystem {
    private PrivateBaseSystemImpl() {}

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
