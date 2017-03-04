package ru.endlesscode.mimic.util;

import ru.endlesscode.mimic.BaseSystem;

/**
 * Implementation of BaseClass with parametrized constrictor
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class ParametrizedBaseSystemImpl implements BaseSystem {
    private final String name;

    ParametrizedBaseSystemImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
