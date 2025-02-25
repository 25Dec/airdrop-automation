package com.tnhandev.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class OkxWalletPage extends BasePage {
    private String walletPassword;

    public OkxWalletPage(BrowserContext context, String walletPassword) {
        super(context);
        this.walletPassword = walletPassword;
    }

    public void start(int count) {
        login();
        confirmTransaction();
        lockWallet();
        switchToHanafudaPage();
    }

    public void login() {
        try {
            csNavigate("chrome-extension://mcohilncbfahbmgdjkbpemcciiolgcge/popup.html");
            csWaitForLoadState();
            csGetByPlaceholder("Enter your password").fill(walletPassword);
            csGetByText("Unlock").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] login() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void confirmTransaction() {
        try {
            csDelay(1000);
            csWaitForSelector("//button[span[div[text()='Confirm']]]");
            if (csLocator("//button[span[div[text()='Confirm']]]").count() > 0) {
                csLocator("//button[span[div[text()='Confirm']]]").click();
            }
            else {
                csReload();
                confirmTransaction();
            }
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] confirmTransaction() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void lockWallet() {
        try {
            csDelay(3000);
            csLocator("//div[contains(@class,'_wallet-icon_5gayk_1')]//i[contains(@class,'okx-wallet-plugin-settings-2')]").click();
            csLocator("//div[contains(text(),'Lock wallet')]").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] lockWallet() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void switchToHanafudaPage() {
        try {
            for (Page page : context.pages()) {
                if (page.title().contains("Hanafuda | Deposit your assets and earn Hana Points and Hanafuda")) {
                    page.bringToFront();
                    break;
                }
            }
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] switchToHanafudaPage() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }
}
