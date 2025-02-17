package com.tnhandev.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class HarpiePage extends BasePage {
    public HarpiePage(BrowserContext context) {
        super(context);
    }

    public void login() {
        try {
            csNavigate("https://harpie.io/");
            csWaitForLoadState();
            csGetByText("ENTER APP").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [HarpiePage] login() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void approveTransaction() {
        try {
            csWaitForSelector("//button[span[text()='Approve']]");
            csLocator("//button[span[text()='Approve']]").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [HarpiePage] harpieApproveTransaction() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void switchToOkxWalletPage() {
        try {
            for (Page page : context.pages()) {
                if (page.title().contains("OKX Wallet")) {
                    page.bringToFront();
                    break;
                }
            }
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [HarpiePage] switchToOkxWalletPage() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void getCurrentPoints() {
        try {
            csWaitForSelector("//div[@class='flex gap-2 xxs:gap-1.5']/button/p");
            System.out.println(csLocator("//div[@class='flex gap-2 xxs:gap-1.5']/button/p").textContent());
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [HarpiePage] getCurrentPoints() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }
}
