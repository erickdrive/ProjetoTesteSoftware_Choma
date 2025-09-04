import re
import time
from playwright.sync_api import Playwright, sync_playwright, expect

def run(playwright: Playwright) -> None:
    browser = playwright.chromium.launch(headless=False)
    context = browser.new_context()
    page = context.new_page()
    page.goto("https://the-internet.herokuapp.com/login")
    page.get_by_role("textbox", name="Username").fill("tomsmith")
    time.sleep(3)
    page.get_by_role("textbox", name="Password").fill("SuperSecretPassword!")
    time.sleep(3)
    page.get_by_role("button", name=" Login").click()
    time.sleep(3)
    page.get_by_role("link", name="Logout").click()
    time.sleep(3)

    # ---------------------
    context.close()
    browser.close()


with sync_playwright() as playwright:
    run(playwright)