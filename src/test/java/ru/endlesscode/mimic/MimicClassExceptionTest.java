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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MimicClassExceptionTest {
    @Test
    public void testMetadataMissingMessage() throws Exception {
        MimicClassException e = new MimicClassException(BaseSystem.class, MimicClassException.ErrorCode.METADATA_MISSING);
        assertEquals("Metadata not found in class 'ru.endlesscode.mimic.BaseSystem'.", e.getMessage());
    }

    @Test
    public void testInstanceCantBeCreated() throws Exception {
        MimicClassException e = new MimicClassException(BaseSystem.class, MimicClassException.ErrorCode.CANT_BE_CREATED);
        assertEquals("Cannot create instance of class 'ru.endlesscode.mimic.BaseSystem'.", e.getMessage());
    }

}