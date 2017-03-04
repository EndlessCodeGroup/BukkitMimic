package ru.endlesscode.mimic.levels;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author Osip Fatkullin
 * @since 1.0
 */
@RunWith(value = Parameterized.class)
public class VanillaLevelSystemTest {
    private VanillaLevelSystem vanillaLevelSystem;

    private int level;
    private int xp;
    private int xpToNextLevel;

    public VanillaLevelSystemTest(int level, int xp, int xpToNextLevel) {
        this.level = level;
        this.xp = xp;
        this.xpToNextLevel = xpToNextLevel;
    }

    @Before
    public void init() {
        this.vanillaLevelSystem = new VanillaLevelSystem();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                {0, 0, 7},
                {1, 7, 9},
                {16, 352, 42},
                {17, 394, 47},
                {31, 1507, 121},
                {32, 1628, 130},
                {40, 2920, 202},
                {41, 3122, 211}
        };

        return Arrays.asList(data);
    }

    @Test
    public void levelToExp() throws Exception {
        int givenXp = vanillaLevelSystem.levelToExp(level);

        assertEquals(xp, givenXp);
    }

    @Test
    public void expToLevel() throws Exception {
        int givenLevel = (int) vanillaLevelSystem.expToLevel(xp);

        assertEquals(level, givenLevel);
    }

    @Test
    public void getExpToReachNextLevel() {
        int givenXp = vanillaLevelSystem.getExpToReachNextLevel(level);

        assertEquals(xpToNextLevel, givenXp);
    }
}