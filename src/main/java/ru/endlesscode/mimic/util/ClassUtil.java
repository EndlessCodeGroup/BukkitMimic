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

package ru.endlesscode.mimic.util;

import org.jetbrains.annotations.NotNull;
import ru.endlesscode.mimic.BaseSystem;
import ru.endlesscode.mimic.Metadata;
import ru.endlesscode.mimic.MimicClassException;

/**
 * Utils to work with classes.
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class ClassUtil {
    /**
     * Checks existence of all classes in array.
     *
     * @param classNames {@code String} Array of class names to check
     * @return {@code true} if all classes exists, otherwise {@code false}
     */
    public static boolean classesExists(@NotNull String... classNames) {
        try {
            for (String className : classNames) {
                Class.forName(className);
            }

            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Gets class metadata from annotation. If annotation not exists - throw exception.
     *
     * @see ru.endlesscode.mimic.Metadata
     * @param theClass  Class to get meta
     * @param <T>       ErrorCode what implements {@code BaseSystem}
     * @return {@code Metadata} if found
     * @throws MimicClassException If {@code Metadata] not not exists
     */
    @NotNull
    public static <T extends BaseSystem> MetadataAdapter getNotNullClassMeta(@NotNull Class<T> theClass) throws MimicClassException {
        Metadata meta = theClass.getAnnotation(Metadata.class);
        if (meta == null) {
            throw new MimicClassException(theClass, MimicClassException.ErrorCode.METADATA_MISSING);
        }

        return new MetadataAdapter(meta);
    }

    /**
     * Creates instance of given class.
     *
     * @param theClass  Class to create instance
     * @param <T>       Type of instance
     * @return Created instance
     * @throws MimicClassException if instance cannot be created
     */
    @NotNull
    public static <T extends BaseSystem> T createClassInstance(Class<T> theClass) throws MimicClassException {
        try {
            return theClass.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new MimicClassException(theClass, MimicClassException.ErrorCode.CANT_BE_CREATED);
        }
    }
}
