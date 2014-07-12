package com.nucleartesuji.eclipsecalculator;

public class ShipPreset {
    static class Builder {
        private int hull              = 0;
        private int ionCannons        = 0;
        private int plasmaCannons     = 0;
        private int plasmaMissiles    = 0;
        private int antimatterCannons = 0;
        private int computer          = 0;
        private int shield            = 0;
        private int initiative        = 0;
        private int shipCount         = 1;

        public ShipPreset build() {
            return new ShipPreset(this);
        }

        public Builder setShipCount(int shipCount) {
            this.shipCount           = shipCount;
            return this;
        }

        public Builder setHull(int hull) {
            this.hull = hull;
            return this;
        }

        public Builder setIonCannons(int ionCannons) {
            this.ionCannons = ionCannons;
            return this;
        }

        public Builder setInitiative(int initiative) {
            this.initiative = initiative;
            return this;
        }

        public Builder setComputer(int computer) {
            this.computer = computer;
            return this;
        }

        public Builder setPlasmaCannons(int plasmaCannons) {
            this.plasmaCannons = plasmaCannons;
            return this;
        }

        public Builder setPlasmaMissiles(int plasmaMissiles) {
            this.plasmaMissiles = plasmaMissiles;
            return this;
        }

        public Builder setAntimatterCannons(int antimatterCannons) {
            this.antimatterCannons = antimatterCannons;
            return this;
        }

        public Builder setShield(int shield) {
            this.shield = shield;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private int hull;
    private int ionCannons;
    private int plasmaCannons;
    private int plasmaMissiles;
    private int antimatterCannons;
    private int computer;
    private int shield;
    private int initiative;
    private int shipCount;

    public ShipPreset(int shipCount, int hull, int ionCannons, int plasmaCannons,
            int plasmaMissiles, int antimatterCannons, int computer,
            int shield, int initiative) {
        this.shipCount = shipCount;
        this.hull = hull;
        this.ionCannons = ionCannons;
        this.plasmaCannons = plasmaCannons;
        this.plasmaMissiles = plasmaMissiles;
        this.antimatterCannons = antimatterCannons;
        this.computer = computer;
        this.shield = shield;
        this.initiative = initiative;
    }

    private ShipPreset(Builder builder) {
        this(builder.shipCount, builder.hull, builder.ionCannons, builder.plasmaCannons,
                builder.plasmaMissiles, builder.antimatterCannons,
                builder.computer, builder.shield, builder.initiative);
    }

    public int getHull() {
        return hull;
    }

    public int getIonCannons() {
        return ionCannons;
    }

    public int getPlasmaCannons() {
        return plasmaCannons;
    }

    public int getPlasmaMissiles() {
        return plasmaMissiles;
    }

    public int getAntimatterCannons() {
        return antimatterCannons;
    }

    public int getComputer() {
        return computer;
    }

    public int getShield() {
        return shield;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getShipCount() {
        return shipCount;
    }
}
