package com.tnhandev.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class OkxWalletPage extends BasePage {
    private String walletPassword;
    private String sendToAddress;
    private String amountToSend;

    public OkxWalletPage(BrowserContext context, String walletPassword, String sendToAddress, String amountToSend) {
        super(context);
        this.walletPassword = walletPassword;
        this.sendToAddress = sendToAddress;
        this.amountToSend = amountToSend;
    }

    public void login() {
        try {
            csNavigate("chrome-extension://mcohilncbfahbmgdjkbpemcciiolgcge/popup.html");
            csGetByPlaceholder("Enter your password").fill(walletPassword);
            csGetByText("Unlock").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] login() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void switchRpc() {
        try {
            csLocator("//div[@class='_container_1eikt_1']").click();
            csDelay(1000);
            csLocator("//div[@class='okui-tabs-pane-list-wrapper']//div[text()='Custom']").click();
            csDelay(1000);
            csLocator("//div[text()='Harpie Base RPC']").click();
            csDelay(2000);
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] switchRpc() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void fillInForm() {
        try {
            csLocator("//i[contains(@class,'okx-wallet-plugin-up')]").click();
            csGetByPlaceholder("Enter wallet address or domain name").fill(sendToAddress);
            csGetByPlaceholder("0.000000").fill(amountToSend);
            csWaitForSelector("//button[span[text()='Next']]");
            csLocator("//button[span[text()='Next']]").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] fillInForm() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void confirmTransaction() {
        try {
            csWaitForSelector("//button[span[div[text()='Confirm']]]");
            csLocator("//button[span[div[text()='Confirm']]]").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] confirmTransaction() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void switchToHarpiePage() {
        try {
            HarpiePage harpiePage = new HarpiePage(context);
            harpiePage.login();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] switchToHarpiePage() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void closeHarpiePage() {
        try {
            for (Page page : context.pages()) {
                if (page.url().contains("harpie.io")) {
                    page.bringToFront();
                    csClose();
                }
            }
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] closeHarpiePage() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }
}
