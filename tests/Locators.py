from appium import webdriver
from selenium.webdriver.common.by import By


desired_capabilities = {
  "platformName": "Android",
  "appium:deviceName": "emulator-5554",
  "appium:app": "C:\\Users\\CPI\\Desktop\\CS458 Project 2\\CS458-CovidSurveyApp\\app\\build\\outputs\\apk\\debug\\app-debug.apk"
}
driver = webdriver.Remote("http://localhost:4723/wd/hub", desired_capabilities)

# web elements
name_text = driver.find_element( By.ID, "textView_register_full_name")

name_field = driver.find_element(By.ID, "editText_register_full_name")

city_text = driver.find_element(By.ID, "textView_city")

city_field = driver.find_element( By.ID, "editText_city")

dob_text = driver.find_element(By.ID, "textView_dob")

dob_field = driver.find_element(By.ID, "editText_dob")

gender_text = driver.find_element(By.ID, "textView_gender")

gender_radio_female = driver.find_element(By.ID, "radio_female")

gender_radio_male = driver.find_element(By.ID, "radio_male")

gender_radio_other = driver.find_element(By.ID, "radio_other")

vac_text = driver.find_element(By.ID, "textView_vaccine_type")

vac_type_field = driver.find_element(By.ID, "editText_vaccine_type")

side_effect_text = driver.find_element(By.ID, "textView_side_effect")

side_effect_yes = driver.find_element(By.ID, "radio_yes_side_effect")

side_effect_no = driver.find_element(By.ID, "radio_no_side_effect")

pcr_text = driver.find_element(By.ID, "textView_pcr")

pcr_yes = driver.find_element(By.ID, "radio_yes_pcr")

pcr_no = driver.find_element(By.ID, "radio_no_pcr")

#symptom_yes = driver.find_element(By.ID, "radio_yes_symptoms")

#symptom_no = driver.find_element(By.ID, "radio_no_symptoms")

#submit_btn = driver.find_element(By.ID, "button_send")