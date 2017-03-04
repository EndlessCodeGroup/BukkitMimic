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
