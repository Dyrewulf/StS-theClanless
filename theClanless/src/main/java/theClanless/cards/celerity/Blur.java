package theClanless.cards.celerity;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.actions.AdditionalStrikeAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.cards.core.QuickJab;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

public class Blur extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID(Blur.class.getSimpleName());
    public static final String IMG = theClanlessMod.makeCardPath("Blur.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheClanless.Enums.CELERITY;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int MAGICNUMBER = 1;
    private static final int MAGICNUMBER_PLUS = 1;


    public Blur() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;

        this.tags.add(CardTags.STRIKE);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL)
        );
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(
                    new AdditionalStrikeAction(p, new QuickJab(), false)
            );
        }
        AbstractDungeon.actionManager.addToBottom(
                new AdditionalStrikeAction(p, new QuickJab(), true)
        );
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            upgradeDescription();
            initializeDescription();
        }
    }
}
