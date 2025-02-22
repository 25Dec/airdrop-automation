package com.tnhandev;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import com.tnhandev.pages.NodepayWeb;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<String> refCodes = List.of("Fk56o4DXrfSeitn", "gYBMdHXb3JnSvjY");

        try (
                Playwright playwright = Playwright.create();
                Browser chromeBrowser = playwright.chromium().connectOverCDP("http://127.0.0.1:9999");
                BrowserContext context = chromeBrowser.contexts().getFirst();
        ) {
            NodepayWeb nodepayWeb = new NodepayWeb(context);
            int count = 1;

            while (true) {
                long startTime = System.nanoTime();
                String randomString = generateRandomString(15);
                String refCode = refCodes.get(count % refCodes.size());
                nodepayWeb.start(randomString, refCode);
                long endTime = System.nanoTime();
                calculateDuration(startTime, endTime, count);
                count++;
            }
        }
    }

    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }

    public static void calculateDuration(long startTime, long endTime, int count) {
        double duration = (endTime - startTime) / 1_000_000_000.0; // Đổi sang giây
        if (duration < 60)
            System.out.printf("🔄️ Lần thứ: %-3d | ⏳ Thời gian thực hiện: %6.2f giây%n", count, duration);
        else
            System.out.printf("🔄️ Lần thứ: %-3d | ⏳ Thời gian thực hiện: %6.2f phút%n", count, duration / 60);
    }
}