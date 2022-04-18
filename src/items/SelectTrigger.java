package items;

import main.Rarity;
import monsters.Monster;
import main.Trigger;
import exceptions.UnusableItemException;

/**
 * An item that gives a {@link monsters.Monster} a select {@link main.Trigger}
 * for their Ability Trigger
 *
 * @author Jackie Jone
 * @version 1.0, Apr 2022
 */
public class SelectTrigger extends Item {

    private Trigger itemTrigger;

    /**
     * Constructor for SelectTrigger Item,
     * the {@link main.Rarity} of this item is always {@link main.Rarity#LEGENDARY}.
     *
     * @param newName        name of the item.
     * @param newDescription description of the item.
     * @param newTrigger     {@link main.Trigger} to give the item.
     */
    public SelectTrigger(String newName, String newDescription, Trigger newTrigger) {
        super(newName, newDescription, Rarity.LEGENDARY);

        itemTrigger = newTrigger;
    }

    /**
     * Gives the applies the {@link main.Trigger} of the {@link items.Item} to a
     * {@link monsters.Monster}.
     *
     * @param monster The {@link monsters.Monster} to apply the {@link main.Trigger} to
     * @throws UnusableItemException The {@link monsters.Monster} which the {@link items.Item}
     * is used on already has the {@link main.Trigger} of the {@link items.Item}.
     */
    public void use(Monster monster) throws UnusableItemException{
        if (monster.getTrigger() == itemTrigger){
            throw new UnusableItemException("Monster: " + monster.getName() + " already has trigger + " + monster.getTrigger());
        } else {
            monster.setTrigger(itemTrigger);
        }
    }

}