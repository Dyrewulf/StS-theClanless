package theClanless.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theClanless.cards.core.GirdMinions;
import theClanless.cards.core.TributeToTheMaster;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import java.util.ArrayList;
import java.util.Iterator;

import static theClanless.theClanlessMod.makePowerPath;

public class BloodDollPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = theClanlessMod.makeID("BloodDollPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("BloodDollPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("BloodDollPower32.png"));

    private static int BDIdOffset;
    private boolean isThisUpgraded;


    public BloodDollPower(final AbstractCreature owner, final AbstractCreature source, final int amount, final boolean isUpgraded) {
        name = NAME;
        ID = POWER_ID + BDIdOffset;
        isThisUpgraded = isUpgraded;
        ++BDIdOffset;

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
    public void atStartOfTurnPostDraw() {
        this.amount++;

        if((amount % 3) == 0) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList();
            stanceChoices.add(new GirdMinions());
            stanceChoices.add(new TributeToTheMaster());
            if (this.isThisUpgraded) {
                Iterator var4 = stanceChoices.iterator();

                while(var4.hasNext()) {
                    AbstractCard c = (AbstractCard)var4.next();
                    c.upgrade();
                }
            }

            this.addToBot(new ChooseOneAction(stanceChoices));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new BloodDollPower(owner, source, amount, isThisUpgraded);
    }
}
