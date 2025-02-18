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

        onlyRunChrome(walletPassword, sendToAddress, amountToSend);
//        onlyRunEdge(walletPassword, sendToAddress, amountToSend);
//        runBoth(walletPassword, sendToAddress, amountToSend);
    }

    public static void calculateDuration(long startTime, long endTime, int count, String browserName) {
        double duration = (endTime - startTime) / 1_000_000_000.0; // Đổi sang giây
        if (duration < 60)
            System.out.printf("🌐 Trình duyệt: %s | 🔄️ Lần thứ: %d | ⏳ Thời gian thực hiện: %.2f giây%n", browserName, count, duration);
        else
            System.out.printf("🌐 Trình duyệt: %s | 🔄️ Lần thứ: %d | ⏳ Thời gian thực hiện: %.2f phút%n", browserName, count, duration / 60);
    }

    public static void onlyRunChrome(String walletPassword, String sendToAddress, String amountToSend) {
        try (
                Playwright playwright = Playwright.create();
                Browser chromeBrowser = playwright.chromium().connectOverCDP("http://127.0.0.1:9999");
                BrowserContext chromeContext = chromeBrowser.contexts().getFirst();
        ) {
            OkxWalletPage chromeOkxWalletPage = new OkxWalletPage(chromeContext, walletPassword, sendToAddress, amountToSend);
            int count = 1;

            while (true) {
                long startTime = System.nanoTime();
                chromeOkxWalletPage.start();
                long endTime = System.nanoTime();
                calculateDuration(startTime, endTime, count, "Chrome");
                count++;
            }
        }
    }

    public static void onlyRunEdge(String walletPassword, String sendToAddress, String amountToSend) {
        try (
                Playwright playwright = Playwright.create();
                Browser edgeBrowser = playwright.chromium().connectOverCDP("http://127.0.0.1:8888");
                BrowserContext edgeContext = edgeBrowser.contexts().getFirst();
        ) {
            OkxWalletPage edgeOkxWalletPage = new OkxWalletPage(edgeContext, walletPassword, sendToAddress, amountToSend);
            int count = 1;

            while (true) {
                long startTime = System.nanoTime();
                edgeOkxWalletPage.start();
                long endTime = System.nanoTime();
                calculateDuration(startTime, endTime, count, "Edge");
                count++;
            }
        }
    }

    public static void runBoth(String walletPassword, String sendToAddress, String amountToSend) {
        Thread chromeThread = new Thread(() -> {
            try (
                    Playwright playwright = Playwright.create();
                    Browser chromeBrowser = playwright.chromium().connectOverCDP("http://127.0.0.1:9999");
                    BrowserContext chromeContext = chromeBrowser.contexts().getFirst();
            ) {
                OkxWalletPage chromeOkxWalletPage = new OkxWalletPage(chromeContext, walletPassword, sendToAddress, amountToSend);
                int count = 1;

                while (true) {
                    long startTime = System.nanoTime();
                    chromeOkxWalletPage.start();
                    long endTime = System.nanoTime();
                    calculateDuration(startTime, endTime, count, "Chrome");
                    count++;
                }
            }
        });

        Thread edgeThread = new Thread(() -> {
            try (
                    Playwright playwright = Playwright.create();
                    Browser edgeBrowser = playwright.chromium().connectOverCDP("http://127.0.0.1:8888");
                    BrowserContext edgeContext = edgeBrowser.contexts().getFirst();
            ) {
                OkxWalletPage edgeOkxWalletPage = new OkxWalletPage(edgeContext, walletPassword, sendToAddress, amountToSend);
                int count = 1;

                while (true) {
                    long startTime = System.nanoTime();
                    edgeOkxWalletPage.start();
                    long endTime = System.nanoTime();
                    calculateDuration(startTime, endTime, count, "Edge");
                    count++;
                }
            }
        });

        chromeThread.start();
        edgeThread.start();
    }
}