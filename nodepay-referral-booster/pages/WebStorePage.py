from pages.BasePage import *


class WebStorePage(BasePage):
    def __init__(self, sb):
        super().__init__(sb)

    def installExt(self):
        addToChromeBtn = "#yDmH0d > c-wiz > div > div > main > div > section.VWBXhd > section > div.OdjmDb > div > button > span.UywwFc-vQzf8d"

        super().click(addToChromeBtn)

        self.sb.sleep(5)
