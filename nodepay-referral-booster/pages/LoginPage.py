from pages.BasePage import *
from pages.ExtensionPage import *
from pages.RegisterPage import *


class LoginPage(BasePage):
    def __init__(self, sb):
        super().__init__(sb)

    def complete_form(self):
        super().goTo("https://app.nodepay.ai/login")
        extensionPage = ExtensionPage(self.sb)

        usernameInput = "#basic_user"
        newPwdInput = "#basic_password"
        submitBtn = "#basic > button"
        errorNoti = "body > div.ant-notification.ant-notification-topRight.css-161f05s.ant-notification-stack.ant-notification-stack-expanded"

        while True:
            super().insertText(usernameInput, BasePage.randomStr)
            super().insertText(newPwdInput, "th!3n2OO2nh4n")
            super().click(submitBtn)

            if not (self.sb.is_element_present(errorNoti)):
                break
            else:
                self.sb.refresh_page()

        if self.sb.get_current_url() == "https://app.nodepay.ai/dashboard":
            self.sb.refresh_page()
            super().goTo(
                "chrome-extension://lgmpfmgeabnnlemejacfljbmonaomfmm/index.html", True
            )
            extensionPage.activate()
