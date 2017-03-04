package ru.endlesscode.mimic.util;

import org.junit.Test;
import ru.endlesscode.mimic.BaseSystem;
import ru.endlesscode.mimic.Metadata;
import ru.endlesscode.mimic.MimicClassException;

import static org.junit.Assert.*;

/**
 * Testing ClassUtil
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
public class ClassUtilTest {
    @Test
    public void classesExists_ExistingClassesMustReturnTrue() {
        String[] classes = {
                "ru.endlesscode.mimic.Mimic",
                "ru.endlesscode.mimic.util.ClassUtil"
        };

        boolean existence = ClassUtil.classesExists(classes);

        assertTrue(existence);
    }

    @Test
    public void classesExists_OneNotExistingClassMustReturnFalse() {
        String[] classes = {
                "ru.endlesscode.mimic.Mimic",
                "ru.endlesscode.mimic.util.ClassUtil",
                "not.existing.classpath"
        };

        boolean existence = ClassUtil.classesExists(classes);

        assertFalse(existence);
    }

    @Test
    public void classesExists_EmptyClassesMustReturnTrue() {
        String[] classes = {};

        boolean existence = ClassUtil.classesExists(classes);

        assertTrue(existence);
    }

    @Test(expected = MimicClassException.class)
    public void getClassMetaData_NotAnnotatedClassMustThrowException() throws Exception {
        Class<? extends BaseSystem> theClass = BaseSystem.class;

        ClassUtil.getNotNullClassMeta(theClass);
    }

    @Test
    public void getClassMetaData_AnnotatedClassMustReturnRightResult() throws Exception {
        Class<? extends BaseSystem> theClass = AnnotatedBaseSystem.class;

        MetadataAdapter meta = ClassUtil.getNotNullClassMeta(theClass);

        assertNotNull(meta);
    }

    @Test(expected = MimicClassException.class)
    public void getClassInstance_ClassWithPrivateConstructorThrowException() throws Exception {
        Class<? extends BaseSystem> theClass = PrivateBaseSystemImpl.class;

        ClassUtil.createClassInstance(theClass);
    }

    @Test(expected = MimicClassException.class)
    public void getClassInstance_ClassWithParametrizedConstructorThrowException() throws Exception {
        Class<? extends BaseSystem> theClass = ParametrizedBaseSystemImpl.class;

        ClassUtil.createClassInstance(theClass);
    }

    @Test
    public void getClassInstance_ClassWithDefaultConstructorMustReturnRightResult() throws Exception {
        Class<? extends BaseSystem> theClass = BaseSystemImpl.class;

        BaseSystem instance = ClassUtil.createClassInstance(theClass);

        assertNotNull(instance);
    }
}