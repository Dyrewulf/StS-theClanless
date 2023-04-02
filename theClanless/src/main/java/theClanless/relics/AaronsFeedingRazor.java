package theClanless.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theClanless.powers.FeedingRazorPower;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import static theClanless.theClanlessMod.makeRelicOutlinePath;
import static theClanless.theClanlessMod.makeRelicPath;

public class AaronsFeedingRazor extends CustomRelic {
    public static final String ID = theClanlessMod.makeID("AaronsFeedingRazorRelic");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("AaronsFeedingRazor.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("AaronsFeedingRazor.png"));

    public static final int COUNT = 10;

    public AaronsFeedingRazor() { super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            ++this.counter;
            if (this.counter == 10) {
                this.counter = 0;
                this.flash();
                this.pulse = false;
            } else if (this.counter == 9) {
                this.beginPulse();
                this.pulse = true;
                AbstractDungeon.player.hand.refreshHandLayout();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FeedingRazorPower(AbstractDungeon.player, 1), 1, true));
            }
        }

    }

    @Override
    public void atBattleStart() {
        if (this.counter == 9) {
            this.beginPulse();
            this.pulse = true;
            AbstractDungeon.player.hand.refreshHandLayout();
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FeedingRazorPower(AbstractDungeon.player, 1), 1, true));
        }

    }

    @Override
    public AbstractRelic makeCopy() {
        return new AaronsFeedingRazor();
    }
}
