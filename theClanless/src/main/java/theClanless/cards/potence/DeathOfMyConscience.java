package theClanless.cards.potence;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DemonFormPower;
import theClanless.actions.DOMCAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.powers.DeathOfMyConsciencePower;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class DeathOfMyConscience extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID(DeathOfMyConscience.class.getSimpleName());
    public static final String IMG = makeCardPath("DeathOfMyConscience.png");


    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheClanless.Enums.POTENCE;

    private static final int COST = 3;

    private static final int MAGICNUMBER = 2;
    private static final int MAGICNUMBER_PLUS = 1;


    public DeathOfMyConscience() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DeathOfMyConsciencePower(p, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public float getTitleFontSize() {
        return 16.0F;
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            initializeDescription();
        }
    }


}
