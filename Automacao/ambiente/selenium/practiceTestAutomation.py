import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.get("https://practicetestautomation.com/practice-test-login/")

wait = WebDriverWait(driver, 10)

# Preenche login
driver.find_element(By.ID, "username").send_keys("student")
time.sleep(2)
driver.find_element(By.ID, "password").send_keys("Password123")
time.sleep(2)
driver.find_element(By.CLASS_NAME, "btn").click()
time.sleep(2)
# Valida login
assert wait.until(EC.presence_of_element_located((By.CLASS_NAME, "post-title")))
print("Login realizado com sucesso!")
driver.find_element(By.CLASS_NAME, "has-very-dark-gray-background-color").click()
time.sleep(5)
print("Logout realizado com sucesso!")

# Preenche login inválido
driver.find_element(By.ID, "username").send_keys("Joao")
time.sleep(2)
driver.find_element(By.ID, "password").send_keys("Choma")
time.sleep(2)
driver.find_element(By.CLASS_NAME, "btn").click()
time.sleep(2)

# Valida mensagem de erro de login
assert wait.until(EC.presence_of_element_located((By.ID, "error")))
print("Login INVÁLIDO validado com sucesso!")
driver.quit()