import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.get("https://the-internet.herokuapp.com/login")

wait = WebDriverWait(driver, 10)

# Preenche login
driver.find_element(By.ID, "username").send_keys("tomsmith")
time.sleep(2)
driver.find_element(By.ID, "password").send_keys("SuperSecretPassword!")
time.sleep(2)
driver.find_element(By.CLASS_NAME, "radius").click()
time.sleep(2)

# Valida login
assert wait.until(EC.presence_of_element_located((By.CLASS_NAME, "icon-lock")))
print("Login realizado com sucesso!")
time.sleep(6)
driver.find_element(By.CLASS_NAME, "radius").click()
time.sleep(10)
print("Logout realizado com sucesso!")

# Preenche login inválido
driver.find_element(By.ID, "username").send_keys("Joao")
time.sleep(2)
driver.find_element(By.ID, "password").send_keys("Choma")
time.sleep(2)
driver.find_element(By.CLASS_NAME, "radius").click()
time.sleep(2)

# Valida mensagem de erro de login
assert wait.until(EC.presence_of_element_located((By.CLASS_NAME, "flash")))
print("Login INVÁLIDO validado com sucesso!")
driver.quit()