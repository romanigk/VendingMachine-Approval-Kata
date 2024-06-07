package org.codingdojo.vendingmachine;

import org.approvaltests.Approvals;
import org.approvaltests.StoryBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendingMachineTest {

    private VendingMachine machine;
    private Map<String, Integer> coins;
    private StoryBoard story;
    private VendingMachinePrinter printer;

    @BeforeEach
    void setUp() {
        machine = new VendingMachine();
        coins = new HashMap<String, Integer>(){{
            put("penny", 1);
            put("nickel", 5);
            put("dime", 10);
            put("quarter", 25);
        }};
        printer = new VendingMachinePrinter(machine);
        story = new StoryBoard();
    }

    @Test
    public void accept_nickel()
    {
        story.addDescription("Feature: Nickel is accepted");
        story.addFrame("Initial state", printer.print());

        insertCoin("nickel");

        Approvals.verify(story);
    }

    @Test
    public void accept_dime()
    {
        story.addDescription("Feature: Dime is accepted");
        story.addFrame("Initial state", printer.print());

        insertCoin("dime");

        Approvals.verify(story);
    }

   @Test
    public void accept_quarter()
    {
        story.addDescription("Feature: Quarter is accepted");
        story.addFrame("Initial state", printer.print());

        insertCoin("quarter");

        Approvals.verify(story);
    }

    @Test
    public void update_balance()
    {
        story.addDescription("Feature: Update balance displayed when coin inserted");
        machine.insertCoin(coins.get("nickel"));
        machine.insertCoin(coins.get("quarter"));
        story.addFrame("Initial state", printer.print());

        insertCoin("dime");

        Approvals.verify(story);
    }

    //@Test
    public void reject_penny()
    {
        story.addDescription("Feature: Reject penny");
        story.addFrame("Initial state", printer.print());

        insertCoin("penny");

        Approvals.verify(story);
    }

    //@Test
    public void returnAllCoins()
    {
        story.addDescription("Feature: Return Coins");
        machine.insertCoin(coins.get("quarter"));
        machine.insertCoin(coins.get("nickel"));
        story.addFrame("Initial state", printer.print());

        returnCoins();

        story.addFrame("End state", printer.print());
        Approvals.verify(story);
    }

    private void insertCoin(String coinName) {
        machine.insertCoin(coins.get(coinName));
        story.addFrame("insert coin - " + coinName, printer.print());
    }

    private void returnCoins()
    {
        //machine.returnCoins();
        story.addFrame("return coins", printer.print());
    }

}
