from seleniumbase import SB
from pages.WebStorePage import *
from pages.RegisterPage import *
from pages.LoginPage import *
from utils import *

with SB(maximize=True, uc=True, headed=True) as sb:
    # Init pages
    webStorePage = WebStorePage(sb)
    registerPage = RegisterPage(sb)

    # Manually install nodepay extension
    webStorePage.goTo(
        "https://chromewebstore.google.com/detail/nodepay-extension/lgmpfmgeabnnlemejacfljbmonaomfmm?pli=1",
        True,
    )
    webStorePage.installExt()

    # Start
    while BasePage.time <= 900:
        registerPage.complete_form()
