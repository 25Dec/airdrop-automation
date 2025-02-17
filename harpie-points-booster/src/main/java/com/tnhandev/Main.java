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
                BrowserContext context = browser.contexts().getFirst()
        ) {
            OkxWalletPage okxWalletPage = new OkxWalletPage(context, walletPassword, sendToAddress, amountToSend);
            int count = 1;

            while (true) {
                long startTime = System.nanoTime();
                try {
                    okxWalletPage.login();
                    okxWalletPage.switchRpc();
                    okxWalletPage.fillInForm();
                    okxWalletPage.confirmTransaction();
                    okxWalletPage.switchToHarpiePage();
                    okxWalletPage.confirmTransaction();
                    okxWalletPage.lockWallet();
                    okxWalletPage.closeHarpiePages();
                }
                catch (RuntimeException e) {
                    System.out.println("|");
                }
                long endTime = System.nanoTime();
                calculateDuration(startTime, endTime, count);
                count++;
            }
        }
    }

    public static void calculateDuration(long startTime, long endTime, int count) {
        double duration = (endTime - startTime) / 1_000_000_000.0; // Đổi sang giây
        if (duration < 60)
            System.out.printf("🔄️ Lần thứ: %d | ⏳ Thời gian thực hiện: %.2f giây%n", count, duration);
        else
            System.out.printf("🔄️ Lần thứ: %d | ⏳ Thời gian thực hiện: %.2f phút%n", count, duration / 60);
    }
}