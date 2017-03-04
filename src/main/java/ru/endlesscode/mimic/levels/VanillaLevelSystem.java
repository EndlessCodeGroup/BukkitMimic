package ru.endlesscode.mimic.levels;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import ru.endlesscode.mimic.Metadata;
import ru.endlesscode.mimic.SystemType;

/**
 * Vanilla experience bar system
 *
 * @author Osip Fatkullin
 * @since 1.0
 */
@Metadata(systemType = SystemType.LEVEL, priority = ServicePriority.Lowest)
public class VanillaLevelSystem implements LevelSystem {
    @Override
    public int getPlayerLevel(Player player) {
        return player.getLevel();
    }

    @Override
    public void setPlayerLevel(Player player, int newLevel) {
        player.setLevel(newLevel);
    }

    @Override
    public boolean didPlayerReachLevel(Player player, int requiredLevel) {
        return this.getPlayerLevel(player) >= requiredLevel;
    }

    @Override
    public void increasePlayerLevel(Player player, int lvlAmount) {
        player.giveExpLevels(lvlAmount);
    }

    @Override
    public void decreasePlayerLevel(Player player, int lvlAmount) {
        player.giveExpLevels(-lvlAmount);
    }

    @Override
    public int getTotalPlayerExp(Player player) {
        int currentLevel = getPlayerLevel(player);
        return levelToExp(currentLevel) + getPlayerExp(player);
    }

    /**
     * Converts level to exp
     *
     * @see <a href="http://minecraft.gamepedia.com/Experience#Leveling_up">Minecraft Wiki: Experience - Leveling Up</a>
     * @param level Player level
     * @return Experience amount to reach given level from 0 exp
     */
    protected int levelToExp(int level) {
        if (level >= 32) {
            return (int) (4.5*level*level - 162.5*level + 2220);
        }

        if (level >= 17) {
            return (int) (2.5*level*level - 40.5*level + 360);
        }

        return level*level + 6*level;
    }

    @Override
    public void setTotalPlayerExp(Player player, int newTotalExperience) {
        double level = expToLevel(newTotalExperience);
        int fullLevel = (int) level;
        double experiencePercent = level - fullLevel;

        this.setPlayerLevel(player, fullLevel);
        this.setPlayerExp(player, (int) (getPlayerExpToLevel(player) * experiencePercent));
    }

    /**
     * Converts experience to level
     *
     * @see <a href="http://minecraft.gamepedia.com/Experience#Leveling_up">Minecraft Wiki: Experience - Leveling Up</a>
     * @param exp Experience amount
     * @return Level amount
     */
    protected double expToLevel(long exp) {
        if (exp >= 1628) {
            return (Math.sqrt(72 * exp - 54215) + 325) / 18;
        }

        if (exp >= 394) {
            return Math.sqrt(40 * exp - 7839) / 10 + 8.1;
        }

        if (exp > 0) {
            return Math.sqrt(exp + 9) - 3;
        }

        return 0;
    }

    @Override
    public int getPlayerExp(Player player) {
        int level = getPlayerLevel(player);
        int expToLevel = getExpToReachNextLevel(level);

        return (int) (expToLevel * player.getExp());
    }

    @Override
    public void setPlayerExp(Player player, int newExperience) {
        Validate.isTrue(newExperience >= 0, "Experience cannot be negative");

        int level = getPlayerLevel(player);
        int expToNextLevel = getExpToReachNextLevel(level);
        newExperience = Math.min(newExperience, expToNextLevel);

        player.setExp(newExperience / expToNextLevel);
    }

    /**
     * Gets how much experience you need to reach specified level
     *
     * @see <a href="http://minecraft.gamepedia.com/Experience#Leveling_up">Minecraft Wiki: Experience - Leveling Up</a>
     * @param level Current level
     * @return Experience from current to next level
     */
    protected int getExpToReachNextLevel(int level) {
        if (level >= 31) {
            return 9*level - 158;
        }

        if (level >= 16) {
            return 5*level - 38;
        }

        return 2*level + 7;
    }

    @Override
    public int getPlayerExpToLevel(Player player) {
        return player.getExpToLevel();
    }

    @Override
    public void giveExpToPlayer(Player player, int expAmount) {
        player.giveExp(expAmount);
    }

    @Override
    public void takeExpFromPlayer(Player player, int expAmount) {
        expAmount = Math.min(expAmount, getTotalPlayerExp(player));
        player.giveExp(-expAmount);
    }

    @Override
    public String getName() {
        return "Vanilla";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
