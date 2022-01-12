package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theClanless.cards.core.GirdMinions;
import theClanless.cards.core.TributeToTheMaster;

import java.util.ArrayList;

public class EnergyHPExchangeAction extends AbstractGameAction {

    private ArrayList<AbstractCard> choices;

    public EnergyHPExchangeAction(AbstractCreature owner) {
        this.actionType = ActionType.ENERGY;
        this.duration = Settings.ACTION_DUR_FAST;
        this.choices.add(new TributeToTheMaster());
        this.choices.add(new GirdMinions());
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(this.choices, "Choose one:", true);
            this.tickDuration();
        } else {
            this.tickDuration();
        }
    }
}
