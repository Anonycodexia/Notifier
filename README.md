# Notifier
Notifier is an Android app that forwards all incoming notifications to your Telegram chat in real time. It uses NotificationListenerService to capture title, text, and app name, then sends them via the Telegram Bot API. The app runs as a persistent foreground service and auto‑restarts on boot thanks to a BootReceiver. 
