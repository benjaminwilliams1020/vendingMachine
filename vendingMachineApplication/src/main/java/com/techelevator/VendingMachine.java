package com.techelevator;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class VendingMachine {

    List<String> inventoryList = new ArrayList<>();
    Map<String, Snack> myMap = new LinkedHashMap<>();

    Path filePath = Paths.get("inventory.txt");
    private static final String BEGINNING_INVENTORY = "5";


    public VendingMachine() {
        getInventory();
        createSnacks();
    }

    @Override
    public String toString() {
        return inventoryList.toString();

    }

    public void getInventory() {
        try (Scanner fileScanner = new Scanner(filePath)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                inventoryList.add(BEGINNING_INVENTORY + "|" + line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setInventory(List<String> inventory) {
        inventoryList = inventory;
    }


    public String[] makeStringArray(String line) {
        String[] result = line.split("\\|");
        return result;
    }

    public void createSnacks() {
        for (String line : inventoryList) {
            if (line.contains("Chip")) {
                String[] result = makeStringArray(line);

                Snack chipBag = new Chips(result[1], result[2], new BigDecimal(result[3]), Integer.parseInt(result[0]));
                myMap.put(result[1], chipBag);
            } else if (line.contains("Drink")) {
                String[] result = makeStringArray(line);
                Snack drinkCan = new Drink(result[1], result[2], new BigDecimal(result[3]), Integer.parseInt(result[0]));
                myMap.put(result[1], drinkCan);
            } else if (line.contains("Candy")) {
                String[] result = makeStringArray(line);
                Snack candy = new Candy(result[1], result[2], new BigDecimal(result[3]), Integer.parseInt(result[0]));
                myMap.put(result[1], candy);
            } else if (line.contains("Gum")) {
                String[] result = makeStringArray(line);
                Snack gumPack = new Gum(result[1], result[2], new BigDecimal(result[3]), Integer.parseInt(result[0]));
                myMap.put(result[1], gumPack);
            }
        }
    }


    public String displayItems() {
        String result = "";
        for (Map.Entry<String, Snack> output : myMap.entrySet()) {
            if (output.getValue().getQuantity() <= 0) {
                result += output.getKey() +
                        "-->" + "SOLD OUT" + "\n";
            } else {
                result += output.getKey() +
                        "-->" + output.getValue().getName() +
                        "-->" + output.getValue().getPrice() + "\n";
            }

        }
        return result;
    }


    public String saleLog() {
        String result = "";
        for (Map.Entry<String, Snack> output : myMap.entrySet()) {
            int quantitySold = Integer.parseInt(BEGINNING_INVENTORY) - output.getValue().getQuantity();
                result += output.getValue().getName() + "|" + quantitySold + "\n";
            }
        return result + "\n**TOTAL SALES** $";
    }

}













