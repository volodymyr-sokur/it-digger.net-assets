#!/bin/bash

# 1. Install dependencies
echo "Updating and installing dependencies..."
sudo apt update && sudo apt install -y python3-pip python3-rpi.gpio wget

# 2. Setup Directory
sudo mkdir -p /opt/RetroFlag

# 3. Create the Python Listener (Overwrite is fine)
cat <<EOF | sudo tee /opt/RetroFlag/SafeShutdown.py
import RPi.GPIO as GPIO
import os
import time

powerPin, resetPin = 3, 2
GPIO.setmode(GPIO.BCM)
GPIO.setup(powerPin, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(resetPin, GPIO.IN, pull_up_down=GPIO.PUD_UP)

while True:
    if GPIO.input(powerPin) == GPIO.LOW:
        os.system("sudo shutdown -h now")
    if GPIO.input(resetPin) == GPIO.LOW:
        os.system("sudo reboot")
    time.sleep(0.5)
EOF

# 4. Create Service (Overwrite is fine)
cat <<EOF | sudo tee /etc/systemd/system/retroflag-shutdown.service
[Unit]
Description=RetroFlag Safe Shutdown for Ubuntu
After=network.target

[Service]
Type=simple
ExecStart=/usr/bin/python3 /opt/RetroFlag/SafeShutdown.py
Restart=always

[Install]
WantedBy=multi-user.target
EOF

# 5. Smart Config Updates (Checks if line exists first)
CONFIG_FILE="/boot/firmware/config.txt"
[ ! -f "$CONFIG_FILE" ] && CONFIG_FILE="/boot/config.txt"

add_config_line() {
    if ! grep -q "$1" "$CONFIG_FILE"; then
        echo "$1" | sudo tee -a "$CONFIG_FILE"
    fi
}

add_config_line "enable_uart=1"
add_config_line "dtoverlay=gpio-poweroff,gpiopin=4,active_low=1"

# 6. Activation
sudo systemctl daemon-reload
sudo systemctl enable retroflag-shutdown.service
sudo systemctl restart retroflag-shutdown.service

echo "Installation complete. Your NESPi is now Ubuntu-ready!"