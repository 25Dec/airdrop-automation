package com.tnhandev;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import com.tnhandev.pages.HanafudaPage;

public class Main {
    public static void main(String[] args) {
        String loginEmail = "thiennhannguyenhuynh@gmail.com";
        String rpc = "Base"; // Base, Arbitrum One, OP Mainnet
        String amountToSend = "0.00000000001";
        String walletPassword = "020121020121";

        run(loginEmail, rpc, amountToSend, walletPassword);
    }

    public static void calculateDuration(long startTime, long endTime, int count) {
        double duration = (endTime - startTime) / 1_000_000_000.0; // Đổi sang giây
        if (duration < 60)
            System.out.printf("🔄️ Lần thứ: %-3d | ⏳ Thời gian thực hiện: %6.2f giây%n", count, duration);
        else
            System.out.printf("🔄️ Lần thứ: %-3d | ⏳ Thời gian thực hiện: %6.2f phút%n", count, duration / 60);
    }

    public static void run(String loginEmail, String rpc, String amountToSend, String walletPassword) {
        try (
                Playwright playwright = Playwright.create();
                Browser chromeBrowser = playwright.chromium().connectOverCDP("http://127.0.0.1:8888");
                BrowserContext chromeContext = chromeBrowser.contexts().getFirst();
        ) {
            HanafudaPage hanafudaPage = new HanafudaPage(chromeContext, loginEmail, rpc, amountToSend, walletPassword);
            hanafudaPage.login();
            hanafudaPage.switchRpc();
            int count = 1;

            while (true) {
                long startTime = System.nanoTime();
                hanafudaPage.start(count);
                long endTime = System.nanoTime();
                calculateDuration(startTime, endTime, count);
                count++;
            }
        }
    }
}