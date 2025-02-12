import concurrent.futures
import random
import string
import pyautogui
from seleniumbase import Driver, SB

chromeMinWidth = 500
chromeMinHeight = 400
refCodes = [
    {"name": "Chi Be", "refCode": "Fk56o4DXrfSeitn"},
    {"name": "Nhan", "refCode": "gYBMdHXb3JnSvjY"},
]


def main():
    rows, cols = calculate_windows_layout()

    with concurrent.futures.ThreadPoolExecutor() as executor:
        for index in range(rows * cols):
            executor.submit(launch_chrome_window, index, cols)


def generate_random_string(length=15):
    characters = string.ascii_lowercase + string.digits
    return "".join(random.choices(characters, k=length))


def calculate_windows_layout():
    screenWidth, screenHeight = pyautogui.size()
    cols = screenWidth // chromeMinWidth
    rows = screenHeight // chromeMinHeight
    return rows, cols


def calculate_position(index, cols):
    col = index % cols
    row = index // cols
    x = col * chromeMinWidth
    y = row * chromeMinHeight
    return x, y


def launch_chrome_window(index, cols):
    randomStr = generate_random_string()
    x, y = calculate_position(index, cols)

    with SB(headed=True, test=True) as sb:
        sb.set_window_size(chromeMinWidth, chromeMinHeight)
        sb.set_window_position(x, y)

        # Move to chrome webstore page
        sb.open(
            "https://chromewebstore.google.com/detail/nodepay-extension/lgmpfmgeabnnlemejacfljbmonaomfmm?hl=en-US&utm_source=ext_sidebar")
        sb.click("//span[@class='UywwFc-vQzf8d']")
        pyautogui.hotkey('tab', 'enter', interval=0.1)
        sb.sleep(5)

        # Move to register page
        sb.switch_to_newest_window()
        sb.type("//input[@id='basic_email']", f"{randomStr}@gm.ussh.com")
        sb.type("//input[@id='basic_username']", randomStr)
        sb.type("//input[@id='basic_password']", "th!3n2Oo2nh4n")
        sb.type("//input[@id='basic_confirm_password']", "th!3n2Oo2nh4n")
        sb.type("//input[@id='basic_referral_code']", "gYBMdHXb3JnSvjY")
        sb.execute_script("document.querySelector('#basic_agree').click()")
        sb.sleep(30)


if __name__ == "__main__":
    main()
