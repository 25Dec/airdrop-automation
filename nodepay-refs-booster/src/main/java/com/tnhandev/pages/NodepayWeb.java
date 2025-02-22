package com.tnhandev.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;

public class NodepayWeb extends BasePage {
    private String randomString;
    private String refCode;


    public NodepayWeb(BrowserContext context) {
        super(context);

    }

    public void start(String randomString, String refCode) {
        this.randomString = randomString;
        this.refCode = refCode;
        register();
        login();

    }

    public void register() {
        try {
            csNavigate("https://app.nodepay.ai/register");
            csWaitForLoadState();
            csGetByPlaceholder("Your email").fill(randomString + "@gmail.com");
            csGetByPlaceholder("User name").fill(randomString);
            csGetByPlaceholder("New password").fill("th!3n2OO2nh4n");
            csGetByPlaceholder("Confirm password").fill("th!3n2OO2nh4n");
            csGetByPlaceholder("Code").fill(refCode);
            csLocator("//input[@id='basic_agree']").check();
            csWaitForSelector("//button[span[text()='Register']]");
            while (true) {
                csLocator("//button[span[text()='Register']]").click();
                if (csGetByText("Something went error").isVisible()) {}
            }
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void login() {
        csWaitForLoadState();
        csGetByPlaceholder("Username or email").fill(randomString + "@gmail.com");
        csGetByPlaceholder("Password").fill("th!3n2OO2nh4n");
        for (int i = 0; i < 5; i++) {
            Locator registerBtn = csLocator("//button[span[text()='Access My Account']]");
            if (registerBtn.isEnabled())
                registerBtn.click();
            else
                csDelay(5000);
        }
    }
}
