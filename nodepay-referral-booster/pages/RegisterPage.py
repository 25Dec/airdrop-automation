from pages.BasePage import *
from pages.LoginPage import *
from utils import *


class RegisterPage(BasePage):
    def __init__(self, sb):
        super().__init__(sb)

    def complete_form(self):
        super().goTo("https://app.nodepay.ai/register")
        loginPage = LoginPage(self.sb)

        emailInput = "#basic_email"
        usernameInput = "#basic_username"
        newPwdInput = "#basic_password"
        confirmPwdInput = "#basic_confirm_password"
        refInput = "#basic_referral_code"
        termCheckbox = "#basic_agree"
        submitBtn = "#basic > button"
        errorNoti = "body > div.ant-notification.ant-notification-topRight.css-161f05s.ant-notification-stack.ant-notification-stack-expanded"
        singleRefCode = refCodes[BasePage.time % len(refCodes)]

        print(f"Lần {BasePage.time + 1}: {singleRefCode["name"]}")

        while True:
            BasePage.randomStr = generate_random_string()
            super().insertText(emailInput, f"{BasePage.randomStr}@gm.uit.edu.vn")
            super().insertText(usernameInput, BasePage.randomStr)
            super().insertText(newPwdInput, "th!3n2OO2nh4n")
            super().insertText(confirmPwdInput, "th!3n2OO2nh4n")
            super().insertText(refInput, singleRefCode["refCode"])
            super().click(termCheckbox)
            super().click(submitBtn)

            if not (self.sb.is_element_present(errorNoti)):
                break
            else:
                self.sb.refresh_page()

        if self.sb.get_current_url() == "https://app.nodepay.ai/register-successfully":
            loginPage.complete_form()
