package theClanless.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import static com.megacrit.cardcrawl.powers.LoseStrengthPower.*;
import static com.megacrit.cardcrawl.powers.StrengthPower.*;
import static theClanless.theClanlessMod.makePowerPath;

//Gain 1 dex for the turn for each card played.

public class PressPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = theClanlessMod.makeID("PressPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("PressPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("PressPower32.png"));

    public PressPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }


    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(owner, source, new StrengthPower(this.owner, this.amount), this.amount)
        );
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(owner, source, new LoseStrengthPower(this.owner, this.amount), this.amount)
        );
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(owner, source, PressPower.POWER_ID, this.amount)
        );
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount +DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new PressPower(owner, source, amount);
    }
}
