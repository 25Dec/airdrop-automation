package com.tnhandev.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class HanafudaPage extends BasePage {
    private String loginEmail;
    private String rpc;
    private String amountToSend;
    private String walletPassword;

    public HanafudaPage(BrowserContext context, String loginEmail, String rpc, String amountToSend, String walletPassword) {
        super(context);
        this.loginEmail = loginEmail;
        this.rpc = rpc;
        this.amountToSend = amountToSend;
        this.walletPassword = walletPassword;
    }

    public void start(int count) {
        createTransaction();
        switchToOkxWalletPage(count);
        closeOkxWalletPage();
    }

    public void login() {
        try {
            csNavigate("https://hanafuda.hana.network/");
            csWaitForLoadState();
            csWaitForSelector("//button[text()='Sign Up with Google']");
            Page popup = page.waitForPopup(() -> {
                csLocator("//button[text()='Sign Up with Google']").click();
            });
            popup.waitForSelector("//div[@data-identifier='" + loginEmail + "']");
            popup.locator("//div[@data-identifier='" + loginEmail + "']").click();
            page.bringToFront();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [HanafudaPage] login() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void switchRpc() {
        try {
            csLocator("//button//div[text()='Hyperliquid']").click();
            csLocator("//div[@role='menuitem']//div[@class='ml-3 text-button-l' and text()='" + rpc + "']").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [HanafudaPage] switchRpc() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void createTransaction() {
        try {
            csGetByPlaceholder("0").fill(amountToSend);
            csDelay();
            csLocator("//button[text()='Deposit']").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [HanafudaPage] createTransaction() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void switchToOkxWalletPage(int count) {
        try {
            OkxWalletPage okxWalletPage = new OkxWalletPage(context, walletPassword);
            okxWalletPage.start(count);
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] switchToOkxWalletPage() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void closeOkxWalletPage() {
        try {
            for (Page page : context.pages()) {
                if (page.title().trim().contains("OKX Wallet")) {
                    page.bringToFront();
                    page.close();
                }
            }
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] closeHarpiePages() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }
}
