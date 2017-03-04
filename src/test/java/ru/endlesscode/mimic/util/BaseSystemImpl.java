package ru.endlesscode.mimic.util;

import ru.endlesscode.mimic.BaseSystem;

/**
 * Implementation of BaseSystem with default constructor
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class BaseSystemImpl implements BaseSystem {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
