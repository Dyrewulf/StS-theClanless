package theClanless.cards.potence;

import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class Decapitate extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID("Decapitate");
    public static final String IMG = makeCardPath("Decapitate.png");


    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheClanless.Enums.POTENCE;

    private static final int COST = 1;

    private static final int MAGICNUMBER = 30;
    private static final int MAGICNUMBER_PLUS = 10;



    public Decapitate() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;
        }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new JudgementAction(m, this.magicNumber)
        );

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            //this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
