package com.tnhandev.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public abstract class BasePage {
    protected BrowserContext context;
    protected Page page;
    private double globalTimeout = 1500;

    public BasePage(BrowserContext context) {
        this.context = context;
        this.page = context.newPage();
    }

    protected void csNavigate(String url) {
        page.navigate(url);
    }

    protected void csDelay() {
        page.waitForTimeout(globalTimeout);
    }

    protected void csDelay(double timeout) {
        page.waitForTimeout(timeout);
    }

    protected void csWaitForSelector(String selector) {
        page.waitForSelector(selector);
    }

    protected void csWaitForLoadState() {
        page.waitForLoadState(LoadState.LOAD);
    }

    protected Locator csLocator(String selector) {
        csDelay();
        return page.locator(selector);
    }

    protected Locator csGetByText(String text) {
        csDelay();
        return page.getByText(text);
    }

    protected Locator csGetByPlaceholder(String text) {
        csDelay();
        return page.getByPlaceholder(text);
    }
}
