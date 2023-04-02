package theClanless.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import java.util.Iterator;

import static theClanless.theClanlessMod.makeRelicOutlinePath;
import static theClanless.theClanlessMod.makeRelicPath;

public class ProtractedInvestment extends CustomRelic {
    public static final String ID = theClanlessMod.makeID("ProtractedInvestmentRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ProtractedInvestment.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ProtractedInvestment.png"));

    public ProtractedInvestment() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.CLINK);
    }

    private static int INVESTMENT;
    private static int WITHDRAW;


    @Override
    public void onEquip() {
        int currentgold = AbstractDungeon.player.gold;
        INVESTMENT = Math.max(currentgold - (currentgold % 5), 5);
        WITHDRAW = (int) (INVESTMENT * 0.20);
        this.setCounter(INVESTMENT);
        AbstractDungeon.player.loseGold(INVESTMENT);
    }
    @Override
    public void onVictory() {

        if(!this.usedUp){
            boolean isEliteOrBoss = AbstractDungeon.getCurrRoom().eliteTrigger;
            Iterator var2 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var2.hasNext()) {
                AbstractMonster m = (AbstractMonster)var2.next();
                if (m.type == AbstractMonster.EnemyType.BOSS || m.type == AbstractMonster.EnemyType.ELITE) {
                    isEliteOrBoss = true;
                }
            }

            if(isEliteOrBoss) {
                this.setCounter(this.counter - WITHDRAW);
                AbstractDungeon.player.gainGold(WITHDRAW * 2);

                if(this.counter < WITHDRAW) {
                    this.usedUp();
                }

            }
        }

    }


    @Override
    public void onUnequip() {
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public static String getID() {return ID;}

}
