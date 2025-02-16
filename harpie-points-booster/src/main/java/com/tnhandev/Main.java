package com.tnhandev;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import com.tnhandev.pages.OkxWalletPage;

public class Main {
    public static void main(String[] args) {
        String walletPassword = "020121020121";
        String sendToAddress = "0xb3139fd0fa52b9d1f18988f5e64c06df0a08b718";
        String amountToSend = "0.000001";

        try (
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().connectOverCDP("http://127.0.0.1:9876");
                BrowserContext context = browser.contexts().getFirst();
        ) {
            OkxWalletPage okxWalletPage = new OkxWalletPage(context, walletPassword, sendToAddress, amountToSend);
            okxWalletPage.login();
            okxWalletPage.switchRpc();

            int harpiePageCount = 0;

            while (true) {
                try {
                    okxWalletPage.fillInForm();
                    okxWalletPage.confirmTransaction();
                    okxWalletPage.switchToHarpiePage();
                    okxWalletPage.confirmTransaction();
                    harpiePageCount++;

                    if (harpiePageCount >= 10)
                        System.out.println("Đã đủ 10 page");
                }
                catch (RuntimeException e) {
                    break;
                }
            }
        }
    }
}