import re
import time
from playwright.sync_api import Playwright, sync_playwright, expect

def run(playwright: Playwright) -> None:
    browser = playwright.chromium.launch(headless=False)
    context = browser.new_context()
    page = context.new_page()
    page.goto("https://www.saucedemo.com/")
    page.locator("[data-test=\"username\"]").fill("visual_user")
    time.sleep(3)
    page.locator("#login_button_container div").nth(3).click()
    page.locator("[data-test=\"password\"]").fill("secret_sauce")
    time.sleep(3)
    page.locator("[data-test=\"login-button\"]").click()
    time.sleep(3)
    page.locator("[data-test=\"add-to-cart-sauce-labs-backpack\"]").click()
    page.locator("[data-test=\"add-to-cart-sauce-labs-bike-light\"]").click()
    page.locator("[data-test=\"shopping-cart-link\"]").click()
    time.sleep(3)
    page.locator("[data-test=\"checkout\"]").click()
    page.locator("[data-test=\"firstName\"]").fill("AAAAA")
    time.sleep(3)
    page.locator("[data-test=\"lastName\"]").fill("bbbbbb")
    time.sleep(3)
    page.locator("[data-test=\"postalCode\"]").fill("0000000000")
    page.get_by_text("CancelContinue").click()
    time.sleep(3)
    page.locator("[data-test=\"continue\"]").click()
    page.locator("[data-test=\"finish\"]").click()
    page.locator("[data-test=\"back-to-products\"]").click()
    time.sleep(3)

    # ---------------------
    context.close()
    browser.close()

with sync_playwright() as playwright:
    run(playwright)