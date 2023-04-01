package theClanless.cards.core;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.powers.BloodDollPower;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class BloodDoll extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID("BloodDoll");
    public static final String IMG = makeCardPath("BloodDoll.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheClanless.Enums.COLOR_CLANLESSRED;

    //private static final int MAGICNUMBER = 3;
    private static final int COST = 2;
    //private static final int UPGRADE_COST = 2;

    public BloodDoll() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BloodDollPower(p, p, 1, upgraded), 1));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeBaseCost(UPGRADE_COST);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}