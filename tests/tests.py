import unittest

from appium.webdriver.common.appiumby import AppiumBy
from appium.webdriver.common.touch_action import TouchAction
from selenium.webdriver import ActionChains

import Locators
from appium import webdriver

from selenium.webdriver.common.by import By


class MobileTestMethods(unittest.TestCase):
    # functional requirements test
    def test_one(self):
        #check name
        name_text = self.driver.find_element(By.ID, "textView_register_full_name")
        name_text.click()

        name_field = self.driver.find_element(By.ID,"editText_register_full_name" )
        name_field.click()

        #check city
        city_text = self.driver.find_element(By.ID, "textView_city")
        city_text.click()

        city_field = self.driver.find_element(By.ID, "editText_city")
        city_field.click()

        #check dob
        dob_text = self.driver.find_element(By.ID, "textView_dob")
        dob_text.click()

        dob_field = self.driver.find_element(By.ID, "editText_dob")

        #check gender
        gender_text = self.driver.find_element(By.ID, "textView_gender")
        gender_text.click()

        gender_radio_female = self.driver.find_element(By.ID, "radio_female")
        gender_radio_female.click()

        gender_radio_male = self.driver.find_element(By.ID, "radio_male")
        gender_radio_male.click()

        gender_radio_other = self.driver.find_element(By.ID, "radio_other")
        gender_radio_other.click()

        self.driver.find_element( AppiumBy.ANDROID_UIAUTOMATOR,"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"SEND\").instance(0))")

        vac_text = self.driver.find_element(By.ID, "textView_vaccine_type")
        vac_text.click()

        vac_field = self.driver.find_element(By.ID, "editText_vaccine_type")
        vac_field.click()

        side_effect_text = self.driver.find_element(By.ID, "textView_side_effect")
        side_effect_text.click()

        side_effect_yes = self.driver.find_element(By.ID, "radio_yes_side_effect")
        side_effect_yes.click()

        side_effect_no = self.driver.find_element(By.ID, "radio_no_side_effect")
        side_effect_no.click()

        pcr_text = self.driver.find_element(By.ID, "textView_pcr")
        pcr_text.click()

        pcr_yes = self.driver.find_element(By.ID, "radio_yes_pcr")
        pcr_yes.click()

        pcr_no = self.driver.find_element(By.ID, "radio_no_pcr")
        pcr_no.click()

        symptom_yes = self.driver.find_element(By.ID, "radio_yes_symptoms")
        symptom_yes.click()

        symptom_no = self.driver.find_element(By.ID, "radio_no_symptoms")
        symptom_no.click()

        submit_btn = self.driver.find_element(By.ID, "button_send")

    # successful login test
    def test_two(self):
        self.driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR,
                                 "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Full Name\").instance(0))")

        name_field = self.driver.find_element(By.ID, "editText_register_full_name")
        name_field.send_keys( "Oğulcan Çetinkaya")

        city_field = self.driver.find_element(By.ID, "editText_city" )
        city_field.send_keys("Ankara")

        dob_field = self.driver.find_element(By.ID, "editText_dob")
        dob_field.send_keys("15/03/2022")


        gender_radio_male = self.driver.find_element(By.ID, "radio_male")
        gender_radio_male.click()

        self.driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR,
                                 "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"SEND\").instance(0))")

        vac_field = self.driver.find_element(By.ID, "editText_vaccine_type")
        vac_field.send_keys( "Moderna")

        submit_btn = self.driver.find_element(By.ID, "button_send")
        submit_btn.click()

    def test_two_one(self):
        self.driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR,
                                 "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Full Name\").instance(0))")

        name_field = self.driver.find_element(By.ID, "editText_register_full_name")
        #name_field.send_keys( "Oğulcan Çetinkaya")

        city_field = self.driver.find_element(By.ID, "editText_city" )
        city_field.send_keys("Ankara")

        dob_field = self.driver.find_element(By.ID, "editText_dob")
        dob_field.send_keys("15/03/2022")


        gender_radio_male = self.driver.find_element(By.ID, "radio_male")
        gender_radio_male.click()

        self.driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR,
                                 "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"SEND\").instance(0))")

        submit_btn = self.driver.find_element(By.ID, "button_send")
        submit_btn.click()

        self.assertEqual("Full name is required", name_field.text)


    def test_three(self):
        name_field = self.driver.find_element(By.ID, "editText_register_full_name")
        name_field.send_keys( "Oğulcan Çetinkaya")

        city_field = self.driver.find_element(By.ID, "editText_city")
        city_field.send_keys("Ankara")

        self.driver.background_app(5)

        name_field = self.driver.find_element(By.ID, "editText_register_full_name")
        city_field = self.driver.find_element(By.ID, "editText_city")

        self.assertEqual( "Oğulcan Çetinkaya", name_field.text)
        self.assertEqual( "Ankara", city_field.text)


    def test_four(self):
        name_field = self.driver.find_element(By.ID, "editText_register_full_name")
        name_field.send_keys("Oğulcan Çetinkaya")

        city_field = self.driver.find_element(By.ID, "editText_city")
        city_field.send_keys("Ankara")

        dob_field = self.driver.find_element(By.ID, "editText_dob")
        dob_field.send_keys("15/03/2022")

        gender_radio_male = self.driver.find_element(By.ID, "radio_male")
        gender_radio_male.click()

        self.driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR,
                                 "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"SEND\").instance(0))")

        vac_field = self.driver.find_element(By.ID, "editText_vaccine_type")
        vac_field.send_keys("Moderna")

        submit_btn = self.driver.find_element(By.ID, "button_send")
        submit_btn.click()

        validation_msg = self.driver.find_element(By.XPATH ,"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")

        self.assertEqual("Thank you for joining Covid-19 Vaccine Survey", validation_msg.text)



    def test_five(self):
        name_field = self.driver.find_element(By.ID, "editText_register_full_name")
        name_field.send_keys("Oğulcan Çetinkaya")

        city_field = self.driver.find_element(By.ID, "editText_city")
        city_field.send_keys("Ankara")

        dob_field = self.driver.find_element(By.ID, "editText_dob")
        dob_field.send_keys("15/03/2022")

        gender_radio_male = self.driver.find_element(By.ID, "radio_male")
        gender_radio_male.click()

        self.driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR,
                                 "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"SEND\").instance(0))")

        vac_field = self.driver.find_element(By.ID, "editText_vaccine_type")
        vac_field.send_keys("yes")

        submit_btn = self.driver.find_element(By.ID, "button_send")
        submit_btn.click()

        self.assertEqual("Vaccine Type is not correct", vac_field.text)


    @classmethod
    def setUp(self):
        self.driver = webdriver.Remote("http://localhost:4723/wd/hub", Locators.desired_capabilities)

    @classmethod
    def tearDown(self):
        self.driver.quit()


if __name__ == '__main__':
    unittest.main()







