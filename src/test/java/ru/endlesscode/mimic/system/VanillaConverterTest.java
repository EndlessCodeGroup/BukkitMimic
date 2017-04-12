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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author Osip Fatkullin
 * @since 1.0
 */
@RunWith(Parameterized.class)
public class VanillaConverterTest {
    private final int exp;
    private final int level;
    private final int expToNext;

    private VanillaConverter converter;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, 0, 7},
                {7, 1, 9},
                {315, 15, 37},
                {352, 16, 42},
                {1395, 30, 112},
                {1507, 31, 121},
                {2727, 39, 193}
        });
    }

    public VanillaConverterTest(int exp, int level, int expToNext) {
        this.exp = exp;
        this.level = level;
        this.expToNext = expToNext;
    }

    @Before
    public void setUp() throws Exception {
        this.converter = VanillaConverter.getInstance();
    }

    @Test
    public void testExpToLevel() throws Exception {
        assertEquals(this.level, this.converter.expToFullLevel(exp));
    }

    @Test
    public void testLevelToExp() throws Exception {
        assertEquals(this.exp, this.converter.levelToExp(level));
    }

    @Test
    public void getExpToReachNextLevel() throws Exception {
        assertEquals(this.expToNext, this.converter.getExpToReachNextLevel(level));
    }
}