package com.example.shooter.objects.packs;

import com.example.shooter.objects.GameObject;

public abstract class Packs extends GameObject {
    boolean activated = false;
    protected int bonus;

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public int getBonus() {
        return bonus;
    }
}
