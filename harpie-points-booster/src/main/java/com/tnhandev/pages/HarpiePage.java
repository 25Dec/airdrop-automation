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
            csGetByText("GET STARTED - IT'S FREE ").click();
            csWaitForLoadState();
            csLocator("//span[text()='Go to Dashboard']").click();
            csWaitForLoadState();
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

    public String getCurrentPoints() {
        try {
            csLocator("//div[@class='relative mx-auto overflow-hidden border bg-gradient-to-t from-neutral-900 to-neutral-950 border-neutral-800 sm:max-h-none max-xs:w-full max-h-4xl sm:max-w-4xl rounded-t-3xl xs:rounded-3xl']//div[contains(@class,'cursor-pointer')][1]").click();
            return csLocator("//div[@class='flex gap-2 xxs:gap-1.5']/button[1]/p").textContent();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [HarpiePage] getCurrentPoints() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }
}
