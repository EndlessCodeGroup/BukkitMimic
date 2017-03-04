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

import org.jetbrains.annotations.NotNull;

/**
 * Exceptions that throws in Mimic
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class MimicClassException extends Exception {
    private final Class<? extends BaseSystem> theClass;
    private final ErrorCode errorCode;

    /**
     * Constructor
     * @param theClass  Class with error
     * @param errorCode Error code of exception
     */
    public MimicClassException(Class<? extends BaseSystem> theClass, @NotNull MimicClassException.ErrorCode errorCode) {
        this.theClass = theClass;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        switch (errorCode) {
            case METADATA_NOT_FOUND:
                return "Metadata not found in class \"" + theClass.getName() + "\".";
            case INSTANCE_NOT_CREATED:
                return "Cannot create instance from class \"" + theClass.getName() + "\".";
        }

        return "";
    }

    /**
     * Available error codes
     */
    public enum ErrorCode {
        METADATA_NOT_FOUND,
        INSTANCE_NOT_CREATED
    }
}
