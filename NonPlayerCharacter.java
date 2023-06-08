package APCSA.APCSA_Code_Your_Own;

public class NonPlayerCharacter {
    private int maxHealth;
    private int currentHealth;
    private int attackDamage;
    private String name;
    private boolean isDead = false;

    public NonPlayerCharacter(int maxHealth, int attackDamage, String name) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.name = name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public boolean takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth > 0){
            isDead = true;
        }
        return isDead;
    }

    public void attackPlayer(Player targetPlayer, int damage) {
        targetPlayer.takeDamage(damage);
    }

    public void renewHealth() {
        currentHealth = maxHealth;
    }

    public void renewHealth(int health) {
        currentHealth += health;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public String toString() {
        return name + " with health " + currentHealth + "//" + maxHealth + ".";
    }
}
