package theClanless.cards.celerity;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class RapidThought extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID("RapidThought");
    public static final String IMG = makeCardPath("RapidThought.png");

    // STAT DECLARATION
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheClanless.Enums.CELERITY;

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int BLOCK_PLUS = 3;
    private static final int MAGICNUMBER = 2;
    // /STAT DECLARATION/

    public RapidThought() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new DrawCardAction(magicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_PLUS);
            initializeDescription();
        }
    }
}
