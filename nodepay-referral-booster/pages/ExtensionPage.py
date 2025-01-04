from atexit import register
from pages.BasePage import *
from pages.RegisterPage import *


class ExtensionPage(BasePage):
    def __init__(self, sb):
        super().__init__(sb)

    def activate(self):
        activateBtn = "#root > div > div.flex.flex-col.min-h-\[535px\] > div:nth-child(1) > div > div > img"
        logOutBtn = "#dropdown > ul > li > img"
        registerBtn = "#root > div > div > div.w-full > div:nth-child(3) > a"

        super().click(activateBtn)
        super().click(logOutBtn)
        super().click(registerBtn)

        BasePage.time += 1
