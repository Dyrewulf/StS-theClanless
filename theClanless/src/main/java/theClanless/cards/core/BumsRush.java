package theClanless.cards.core;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.actions.BumsRushAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import java.util.ArrayList;

import static theClanless.theClanlessMod.makeCardPath;

public class BumsRush extends AbstractDynamicCard {
    // TEXT DECLARATION
    public static final String ID = theClanlessMod.makeID(BumsRush.class.getSimpleName());
    public static final String IMG = makeCardPath("BumsRush.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    // /TEXT DECLARATION/

    // STAT DECLARATION
    public static final CardColor COLOR = TheClanless.Enums.COLOR_CLANLESSRED;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1;

    private static final int DAMAGE = 7;
    private static final int DAMAGE_PLUS = 2;

    private static final int MAGICNUMBER = 1;
    private static final int MAGICNUMBER_PLUS = 1;

    private ArrayList<AbstractCard> CombatCards = new ArrayList<AbstractCard>();
    // /STAT DECLARATION/

    public BumsRush() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGICNUMBER;

        CombatCards.add(new QuickJab());
        CombatCards.add(new Haymaker());
        CombatCards.add(new FakeOut());
        CombatCards.add(new Disengage());
        CombatCards.add(new BoxedIn());
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        for (int i = 0; i < magicNumber; i++) {
            addToBot(new BumsRushAction(p, CombatCards));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_PLUS);
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            initializeDescription();
        }
    }
}
