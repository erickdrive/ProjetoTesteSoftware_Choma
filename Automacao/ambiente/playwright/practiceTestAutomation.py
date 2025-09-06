import re
import time
from playwright.sync_api import Playwright, sync_playwright, expect

def run(playwright: Playwright) -> None:
    browser = playwright.chromium.launch(headless=False)
    context = browser.new_context()
    page = context.new_page()
    page.goto("https://practicetestautomation.com/practice-test-login/")
    page.get_by_role("textbox", name="Username").fill("student")
    time.sleep(3)
    page.get_by_role("textbox", name="Password").fill("Password123")
    time.sleep(3)
    page.get_by_role("button", name="Submit").click()
    time.sleep(3)
    page.get_by_role("link", name="Log out").click()
    time.sleep(3)
    page.get_by_role("textbox", name="Username").fill("joaochoma")
    page.locator("#main-container").click()
    time.sleep(3)
    page.get_by_role("textbox", name="Password").fill("aprovado")
    page.get_by_role("button", name="Submit").click()
    time.sleep(3)
    page.locator("#error").click()
    time.sleep(3)
    # ---------------------
    context.close()
    browser.close()

with sync_playwright() as playwright:
    run(playwright)