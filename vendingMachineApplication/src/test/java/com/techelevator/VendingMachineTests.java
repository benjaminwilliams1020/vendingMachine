package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineTests {

    private VendingMachine sut;

    @Before
    public void setUp() {
          sut = new VendingMachine();
        }



    @Test
    public void does_inventory_list_populate() throws IOException {

        sut.getInventory();
        Assert.assertEquals("5|B3|Wonka Bar|1.50|Candy",sut.inventoryList.get(6));
    }

    @Test
    public void does_array_get_created() throws IOException {
        sut.getInventory();
        String testString = sut.inventoryList.get(6);
        String[] testArray = sut.makeStringArray(testString);
        Assert.assertArrayEquals(new String[] {"5", "B3","Wonka Bar","1.50", "Candy"},testArray);
    }

    @Test
    public void does_the_snack_get_created() throws IOException {
        List<String> testInventory = new ArrayList<>();
        testInventory.add("5|B3|Wonka Bar|1.50|Candy");
        testInventory.add("5|A4|Cloud Popcorn|3.65|Chip");
        sut.setInventory(testInventory);
        sut.createSnacks();
        Assert.assertEquals(new BigDecimal("1.50"), sut.myMap.get("B3").getPrice());
    }


}
