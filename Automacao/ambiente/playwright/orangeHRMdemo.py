import re
import time
from playwright.sync_api import Playwright, sync_playwright, expect

def run(playwright: Playwright) -> None:
    browser = playwright.chromium.launch(headless=False)
    context = browser.new_context()
    page = context.new_page()
    page.goto("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
    time.sleep(1)
    page.get_by_role("textbox", name="Username").fill("Admin")
    time.sleep(1)
    page.get_by_role("textbox", name="Password").fill("admin123")
    time.sleep(1)
    page.get_by_role("button", name="Login").click()
    time.sleep(1)
    page.get_by_role("link", name="Admin").click()
    time.sleep(1)
    page.get_by_role("link", name="My Info").click()
    time.sleep(1)

    # ---------------------
    context.close()
    browser.close()


with sync_playwright() as playwright:
    run(playwright)