# Notifier – Android Notification Forwarder

Forward all incoming Android notifications to a Telegram chat in real time.

## Features

- Listens to all notifications using Android’s `NotificationListenerService`.
- Sends notification details (app name, title, text) to a Telegram bot.
- Runs as a **foreground service** to stay alive and show a persistent notification.
- **Auto‑starts** after device boot (no need to open the app every time).
- Works on Android 5.0 (API 21) and above.

## How It Works

1. The app registers a `NotificationListenerService` that reads every posted notification.
2. When a notification appears, its package name, title, and content are sent to the configured Telegram bot via the Telegram Bot API.
3. A `BootReceiver` automatically restarts the service after the device reboots.

## Prerequisites

- An Android device or emulator running **API 21+**.
- A Telegram bot token and your chat ID.  
  - Create a bot with [@BotFather](https://t.me/BotFather) and copy the token.  
  - Get your chat ID by messaging [@userinfobot](https://t.me/userinfobot) on Telegram.
- Android Studio (or any Gradle‑compatible build environment).

## Setup & Build

### 1. Clone the repository

```bash
https://github.com/Anonycodexia/Notifier/
cd Notifier
```

### 2. Configure your Telegram credentials

Open `NotifierService.java` and replace the placeholders with your own values:

```java
String token = "YOUR_BOT_TOKEN";   // from @BotFather
String chatId = "YOUR_CHAT_ID";    // from @userinfobot
```

### 3. Build and install

- Open the project in **Android Studio**.
- Connect your device or start an emulator.
- Click **Run** to build and install the APK.

Alternatively, build from the command line:

```bash
./gradlew assembleDebug
```

Then install the generated APK (`app/build/outputs/apk/debug/app-debug.apk`) on your device.

### 4. Grant Notification Access

The app **requires** explicit user permission to read notifications:

1. Go to **Settings → Apps & notifications → Special app access → Notification access**.  
   (The exact path may vary by device.)
2. Find **Notifier** in the list and enable the toggle.

The foreground service will start automatically once access is granted.  
You can also launch the app once – the service will be started on its first creation.

### 5. (Optional) Verify boot auto‑start

Reboot your device. The app will restart its notification listener service automatically.  
You can check the persistent notification “Notifier Running – Listening notifications…” in your status bar.

## Project Structure

```
.
├── app/
│   ├── src/main/
│   │   ├── java/com/android/notifier/
│   │   │   ├── MainActivity.java          # Empty activity (launcher icon only)
│   │   │   ├── NotifierService.java       # Core service: listens & forwards
│   │   │   └── BootReceiver.java          # Restarts service on boot
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── gradle.properties
└── ...
```

## Permissions Used

| Permission | Reason |
|------------|--------|
| `INTERNET` | Send messages to Telegram API |
| `RECEIVE_BOOT_COMPLETED` | Auto‑start service after reboot |
| `FOREGROUND_SERVICE` | Keep service alive with a persistent notification |
| `BIND_NOTIFICATION_LISTENER_SERVICE` | Required to listen to notifications (granted manually) |

The manifest sets `android:usesCleartextTraffic="true"` but the Telegram API is called over **HTTPS**, so no plain‑text data is transmitted.

## Disclaimer

This app forwards **all** notifications, including those that may contain personal or sensitive information.  
Use it only on a device and with a Telegram account that you fully control.  
The developer is not responsible for any data leakage or misuse.

## License

This project currently has no license.  
If you plan to use or distribute it, consider adding an appropriate open‑source license.

---

*Made with ❤️ happy hacking.*
### 2. Configure your Telegram credentials

Open `NotifierService.java` and replace the placeholders with your own values:

```java
String token = "YOUR_BOT_TOKEN";   // from @BotFather
String chatId = "YOUR_CHAT_ID";    // from @userinfobot
```

### 3. Build and install

- Open the project in **Android Studio**.
- Connect your device or start an emulator.
- Click **Run** to build and install the APK.

Alternatively, build from the command line:

```bash
./gradlew assembleDebug
```

Then install the generated APK (`app/build/outputs/apk/debug/app-debug.apk`) on your device.

### 4. Grant Notification Access

The app **requires** explicit user permission to read notifications:

1. Go to **Settings → Apps & notifications → Special app access → Notification access**.  
   (The exact path may vary by device.)
2. Find **Notifier** in the list and enable the toggle.

The foreground service will start automatically once access is granted.  
You can also launch the app once – the service will be started on its first creation.

### 5. (Optional) Verify boot auto‑start

Reboot your device. The app will restart its notification listener service automatically.  
You can check the persistent notification “Notifier Running – Listening notifications…” in your status bar.

## Project Structure

```
.
├── app/
│   ├── src/main/
│   │   ├── java/com/android/notifier/
│   │   │   ├── MainActivity.java          # Empty activity (launcher icon only)
│   │   │   ├── NotifierService.java       # Core service: listens & forwards
│   │   │   └── BootReceiver.java          # Restarts service on boot
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── gradle.properties
└── ...
```

## Permissions Used

| Permission | Reason |
|------------|--------|
| `INTERNET` | Send messages to Telegram API |
| `RECEIVE_BOOT_COMPLETED` | Auto‑start service after reboot |
| `FOREGROUND_SERVICE` | Keep service alive with a persistent notification |
| `BIND_NOTIFICATION_LISTENER_SERVICE` | Required to listen to notifications (granted manually) |

The manifest sets `android:usesCleartextTraffic="true"` but the Telegram API is called over **HTTPS**, so no plain‑text data is transmitted.

## Disclaimer

This app forwards **all** notifications, including those that may contain personal or sensitive information.  
Use it only on a device and with a Telegram account that you fully control.  
The developer is not responsible for any data leakage or misuse.

## License

This project currently has no license.  
If you plan to use or distribute it, consider adding an appropriate open‑source license.

---

*Made with ❤️ happy hacking.*
