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

    public void start() {
        try {
            login();
            switchRpc();
            fillInForm();
            confirmTransaction();
            switchToHarpiePage();
            confirmTransaction();
            lockWallet();
            closeHarpiePages();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] start() ==||== " + e.toString());
            throw new RuntimeException();
        }
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
            csNavigate("chrome-extension://mcohilncbfahbmgdjkbpemcciiolgcge/popup.html");
            csWaitForLoadState();
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
            if (csLocator("//button[span[div[text()='Confirm']]]").count() > 0) {
                csLocator("//button[span[div[text()='Confirm']]]").click();
                csDelay();
            }
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
            harpiePage.approveTransaction();
            harpiePage.switchToOkxWalletPage();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] switchToHarpiePage() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void closeHarpiePages() {
        try {
            for (Page page : context.pages()) {
                if (page.title().trim().contains("My Dashboard | Harpie")) {
                    page.bringToFront();
                    page.close();
                }
            }
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] closeHarpiePages() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }

    public void lockWallet() {
        try {
            csNavigate("chrome-extension://mcohilncbfahbmgdjkbpemcciiolgcge/popup.html");
            csWaitForLoadState();
            csWaitForSelector("//i[contains(@class,'okx-wallet-plugin-settings-2')]");
            csLocator("//i[contains(@class,'okx-wallet-plugin-settings-2')]").hover();
            csLocator("//div[contains(text(),'Lock wallet')]").click();
        }
        catch (RuntimeException e) {
            System.out.println("Lỗi ở [OkxWalletPage] lockWallet() ==||== " + e.toString());
            throw new RuntimeException();
        }
    }
}
