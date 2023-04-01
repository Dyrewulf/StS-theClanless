package theClanless.relics;


import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import java.util.Iterator;

import static theClanless.theClanlessMod.makeRelicOutlinePath;
import static theClanless.theClanlessMod.makeRelicPath;

public class DrumOfXipeTopec extends CustomRelic {
    public static final String ID = theClanlessMod.makeID("DrumOfXipeTopecRelic");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DrumOfXipeTopec.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DrumOfXipeTopec.png"));

    public DrumOfXipeTopec() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        Iterator var1 = AbstractDungeon.combatRewardScreen.rewards.iterator();

        while(true) {
            RewardItem reward;
            do {
                if (!var1.hasNext()) {
                    return;
                }

                reward = (RewardItem)var1.next();
            } while(reward.cards == null);

            Iterator var3 = reward.cards.iterator();

            while(var3.hasNext()) {
                AbstractCard c = (AbstractCard)var3.next();
                this.onPreviewObtainCard(c);
            }
        }
    }

    @Override
    public void onPreviewObtainCard(AbstractCard c) {
        this.onObtainCard(c);
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (c.color == TheClanless.Enums.CELERITY && c.canUpgrade() && !c.upgraded) {
            c.upgrade();
        }

    }

    @Override
    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 48;
    }

    public static String getID() {return ID;}
}
