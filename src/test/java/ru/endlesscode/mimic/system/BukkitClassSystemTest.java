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

package ru.endlesscode.mimic.system;

import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import ru.endlesscode.mimic.bukkit.BukkitTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Osip Fatkullin
 * @since 1.0
 */
public class BukkitClassSystemTest extends BukkitTest {
    private BukkitClassSystem classSystem;

    @Before
    public void setUp() {
        super.setUp();

        try {
            this.classSystem = mock(BukkitClassSystem.class);
            when(this.classSystem.initializedCopy(ArgumentMatchers.any())).thenCallRealMethod();
            when(this.classSystem.clone()).thenCallRealMethod();
        } catch (CloneNotSupportedException e) {
            fail("Cloning must be supported");
        }
    }

    @Test
    public void testCloneMustBeNotSameObject() throws Exception {
        BukkitClassSystem copy = this.classSystem.clone();

        assertNotSame(this.classSystem, copy);
    }

    @Test
    public void testInitializedCopyMustHaveRightHandler() throws Exception {
        BukkitClassSystem copy = this.classSystem.initializedCopy(this.player);
        when(copy.getHandler()).thenCallRealMethod();

        Player handler = copy.getHandler();
        assertSame(this.player, handler);
    }
}