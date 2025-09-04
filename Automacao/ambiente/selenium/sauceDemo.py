import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.get("https://www.saucedemo.com")

wait = WebDriverWait(driver, 10)

# Preenche login válido
driver.find_element(By.ID, "user-name").send_keys("standard_user")
time.sleep(2)
driver.find_element(By.ID, "password").send_keys("secret_sauce")
time.sleep(2)
driver.find_element(By.ID, "login-button").click()

# Valida login
assert wait.until(EC.presence_of_element_located((By.CLASS_NAME, "app_logo")))
print("Login realizado com sucesso!")
time.sleep(6)
driver.find_element(By.CLASS_NAME, "bm-burger-button").click()
time.sleep(5)
driver.find_element(By.ID, "logout_sidebar_link").click()
print("Logout realizado com sucesso!")
time.sleep(5)

# Preenche login invalido
driver.find_element(By.ID, "user-name").send_keys("Joao")
time.sleep(2)
driver.find_element(By.ID, "password").send_keys("Choma")
time.sleep(2)
driver.find_element(By.ID, "login-button").click()
# Valida elemento de erro de login
assert wait.until(EC.presence_of_element_located((By.CLASS_NAME, "error-message-container")))
print("Login INVÁLIDO validado com sucesso!")
driver.quit()