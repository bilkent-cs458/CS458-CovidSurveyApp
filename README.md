# CS458-CovidSurveyApp

This is the repo for our second project as part of our CS 458 Software Verification and Validation course. It was formulated for us to grasp
mobile application testing better and have hands on experience with developing and testing a mobile application. We chose to develop an
Android application with Java and have used Appium to test it.

### To run the app

1. Clone the repository.
2. [Install Android Studio](https://developer.android.com/studio/install)
3. [Go to the Appium Github Releases page](https://github.com/appium/appium/releases)
> If you do not have an Android emulator device already bundled within your Android Studio application, you can follow the instructions within [this link](https://developer.android.com/studio/run/managing-avds)
> to install an emulator to run the application in.
5. Select and download the release for your operating system and double click on the install to set it up in your machine.
6. Similarly, download the Appium Inspector GUI application from [its Github releases page](https://github.com/appium/appium-inspector/releases)
7. Start the Appium server from the Inspector and fill in your `desiredCapabilities` as follows and launch your session:
   ```json
    {
      "platformName": "Android",
      "deviceName": "emulator-5554",
      "app": "<path-to-the-apk-file>"
    }
   ```

8. Also, change the `desiredCapabilities` within the `Locators.py` script.
9. Now, you can execute the Python code and watch the magic ensue.
