package com.techelevator;

import com.techelevator.view.BasicUI;
import com.techelevator.view.Menu;
import com.techelevator.view.MenuDrivenCLI;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Application {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String SALES_REPORT = Menu.HIDDEN_OPTION;
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS,
			MAIN_MENU_OPTION_PURCHASE, SALES_REPORT};

	private static final String PURCHASE_MENU_OPTION_DEPOSIT = "Deposit Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_ITEM = "Select Item";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_DEPOSIT,
			PURCHASE_MENU_OPTION_SELECT_ITEM, PURCHASE_MENU_OPTION_FINISH};

	private VendingMachine myMachine = new VendingMachine();
	private Balance myBalance = new Balance();
	private Scanner input = new Scanner(System.in);
	private LogReports logs = new LogReports();
	private String inputItem = "";

	private final BasicUI ui;

	public Application(BasicUI ui) {
		this.ui = ui;
	}

	public static void main(String[] args) throws IOException {
		BasicUI cli = new MenuDrivenCLI();
		Application application = new Application(cli);
		application.run();
	}

	public void run() {

		while (true) {
			String selection = ui.promptForSelection(MAIN_MENU_OPTIONS);

			if (selection.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				ui.output(myMachine.displayItems());

			} else if (selection.equals(MAIN_MENU_OPTION_PURCHASE)) {
				boolean purchaseMenu = true;

				while (purchaseMenu) {
					String selectionTwo = ui.promptForSelection(PURCHASE_MENU_OPTIONS);

					if (selectionTwo.equals(PURCHASE_MENU_OPTION_DEPOSIT)) {

						showCurrentBalance();
						deposit();

					} else if (selectionTwo.equals(PURCHASE_MENU_OPTION_SELECT_ITEM)) {

						showCurrentBalance();
						purchase();

					} else if (selectionTwo.equals(PURCHASE_MENU_OPTION_FINISH)) {

						getChange();
						purchaseMenu = false;

					}
				}

			} else if (selection.equals(SALES_REPORT)) {
				logs.salesReport(myMachine.saleLog() + myBalance.getTotalSales());
				ui.output("Sales_report.txt has been generated!");
			}

		}
	}


	public void deposit() {
		boolean validDeposit = true;
		BigDecimal secondBigDecimal = new BigDecimal("0.00");
		ui.output("Insert Money (only accepts bills) >>> ");
		try {
			BigDecimal amount = new BigDecimal(input.nextLine());


			while (validDeposit) {

				if (amount.compareTo(secondBigDecimal) == 1) {

					myBalance.depositMoney(amount);
					showCurrentBalance();

					logs.activityLog("FEED MONEY: $" + amount + ".00" + " $" + myBalance.getBalance());
					validDeposit = false;

				} else {

					ui.output("That's not going to work.");
					validDeposit = false;

				}
			}
		} catch (Exception e) {
			ui.output("That's not going to work.");
		}
	}


	public void purchase() {

		ui.output("What would you like to buy? (\"i.e. A2\")");
		try  {
			inputItem = input.nextLine().toUpperCase();

			int result = itemPriceCompareToBalance();

			if (emptyBalanceCompare() <= 0) {

				ui.output("You need to deposit some money first...");

			} else if (getItemQuantity() == 0) {

				ui.output("Sorry, that item is unavailable.");

			} else if (result == 1) {

				ui.output("You need to deposit some money first...");

			} else {

				makeSale();
				makeSound();
				updateItemQuantity();
				updateTotalSales();

				logs.activityLog(getActivityLogMessage());
			}

		} catch (Exception e) {
			ui.output("That's not going to work.\nPlease choose another item.");

		}
	}


	public void showCurrentBalance() {
		ui.output("Current balance: $" + myBalance.getBalance());
	}
	public String getItemName() {
		return myMachine.myMap.get(inputItem).getName();
	}
	public String getItemPosition() {
		return myMachine.myMap.get(inputItem).getPosition();
	}
	public BigDecimal getItemPrice() {
		return myMachine.myMap.get(inputItem).getPrice();
	}
	public String getActivityLogMessage() {
		return getItemName() + " " + getItemPosition() + " $" + getItemPrice() + " $" + myBalance.getBalance();
	}
	public int getItemQuantity() {
		return myMachine.myMap.get(inputItem).getQuantity();
	}
	public int emptyBalanceCompare() {
		BigDecimal secondBigDecimal = new BigDecimal("0.00");
		return myBalance.getBalance().compareTo(secondBigDecimal);
	}
	public int itemPriceCompareToBalance() {
		return getItemPrice().compareTo(myBalance.getBalance());
	}
	public void makeSale() {
		myBalance.lessMoney(getItemPrice());
	}
	public void makeSound() {
		ui.output(myMachine.myMap.get(inputItem).getSound());
	}
	public void updateItemQuantity() {
		int newQuantity = getItemQuantity() - 1;
		myMachine.myMap.get(inputItem).setQuantity(newQuantity);
	}
	public void updateTotalSales() {
		BigDecimal currentSales = myBalance.getTotalSales();
		myBalance.setTotalSales(currentSales.add(getItemPrice()));
	}
	public void makeChange() {
		int myIntBalance =  myBalance.getBalance().multiply(new BigDecimal(100)).intValue();
		int remainder = 0;
		int quarters = myIntBalance / 25;
		remainder = myIntBalance % 25;
		int dimes = remainder / 10;
		remainder = remainder % 10;
		int nickles = remainder / 5;

		ui.output("Your change = $" + myBalance.getBalance());
		ui.output("    **MACHINE DISPENSES**\n" + quarters + " quarters, " +
				dimes + " dimes, " + nickles + " nickles");
	}
	public void getChange() {

		makeChange();

		BigDecimal change = myBalance.getBalance();
		myBalance.setBalance(new BigDecimal("0.00"));

		logs.activityLog("GIVE CHANGE: $" + change + " $" + myBalance.getBalance());
	}


}




