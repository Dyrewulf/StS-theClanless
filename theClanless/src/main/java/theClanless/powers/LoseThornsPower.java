package theClanless.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import static theClanless.theClanlessMod.makePowerPath;

public class LoseThornsPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = theClanlessMod.makeID("LoseThornsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));

    public LoseThornsPower(AbstractCreature owner, int thornsDamage) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.type = PowerType.DEBUFF;
        this.owner = owner;
        this.amount = thornsDamage;
        this.updateDescription();

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }

    @Override
    public void atStartOfTurn() {
        flash();

        ThornsPower thorns = new ThornsPower(owner, -amount);
        thorns.type = PowerType.DEBUFF;
        addToBot(new ApplyPowerAction(owner, owner, thorns, -amount));

        AbstractPower currentThorns = owner.getPower(ThornsPower.POWER_ID);
        if (currentThorns != null && amount >= currentThorns.amount) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, currentThorns));
        }

        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public AbstractPower makeCopy() {
        return null;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
