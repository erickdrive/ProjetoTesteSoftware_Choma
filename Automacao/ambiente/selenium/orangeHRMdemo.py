import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.get("https://opensource-demo.orangehrmlive.com/")

wait = WebDriverWait(driver, 10)

wait.until(EC.presence_of_element_located((By.NAME, "username"))).send_keys("Admin")
wait.until(EC.presence_of_element_located((By.NAME, "password"))).send_keys("admin123")

# Clica no botão de login
wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, ".oxd-button.oxd-button--main"))).click()

assert wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, ".oxd-userdropdown-tab")))

print("Login realizado com sucesso!")
time.sleep(4)
driver.find_element(By.CLASS_NAME, "oxd-userdropdown-tab").click()
time.sleep(2)
driver.find_element(By.XPATH, '//*[@id="app"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/ul/li[4]/a').click()
print("Logout realizado com sucesso!")
time.sleep(4)
wait.until(EC.presence_of_element_located((By.NAME, "username"))).send_keys("Joao")
wait.until(EC.presence_of_element_located((By.NAME, "password"))).send_keys("Choma")
wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, ".oxd-button.oxd-button--main"))).click()
# Valida mensagem de erro de login
wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, ".oxd-alert.oxd-alert--error")))
print("Login INVÁLIDO validado com sucesso!")
driver.quit()
