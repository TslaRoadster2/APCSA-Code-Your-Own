package APCSA.APCSA_Code_Your_Own;

public class WeaponItem extends Item {
    private int damageInfliction;
    private int maxDurability;
    private int currentDurability;
    private NonPlayerCharacter target;

    public WeaponItem(String description, int damageInfliction, int MaxDurability) {
        super(description, new String[] { "attack" });
        this.damageInfliction = damageInfliction;
        this.maxDurability = MaxDurability;
        this.currentDurability = currentDurability;
    }

    public boolean useAbility(String selectedAbility, Player player) {
        if (hasAbility(selectedAbility)) {
            attack();
            return true;
        }
        return false;
    }

    public void setTarget(NonPlayerCharacter target) {
        this.target = target;
    }

    private boolean attack() {
        target.takeDamage(damageInfliction);
        currentDurability -= 2;
        return currentDurability < 0;
    }

    public void repair() {
        currentDurability = maxDurability;
    }
}
