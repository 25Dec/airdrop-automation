package com.tnhandev.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class HarpiePage extends BasePage {
    public HarpiePage(BrowserContext context) {
        super(context);
    }

    public void login() {
        try {
            csNavigate("https://harpie.io/app/dashboard/0x23e3B27eDAD2f05F6831b2E37D000817275C4e52/?chainId=8453");
            csWaitForLoadState();
            harpieApproveTransaction();
            switchToOkxWalletPage();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [HarpiePage] login() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void harpieApproveTransaction() {
        try {
            csWaitForSelector("//button[span[text()='Approve']]");
            csLocator("//button[span[text()='Approve']]").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] harpieApproveTransaction() ==||== " + e.toString());
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
            System.out.println("Lỗi ở [OkxWalletPage] switchToOkxWalletPage() ==||== " + e.toString());
        }
    }
}
