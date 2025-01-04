class BasePage:
    randomStr = ""
    time = 0

    def __init__(self, sb):
        self.sb = sb

    def goTo(self, url, activateCdpMode=False):
        if activateCdpMode:
            self.sb.activate_cdp_mode(url)
        else:
            self.sb.get(url)
        self.sb.sleep(2)

    def insertText(self, locator, value):
        self.sb.clear(locator)
        self.sb.type(locator, value)

    def click(self, locator):
        self.sb.sleep(2)
        self.sb.click(locator)
        self.sb.sleep(2)
