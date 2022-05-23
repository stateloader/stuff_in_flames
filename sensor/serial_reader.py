# Importing Libraries
import serial
import datetime
import time

delim = ","
path = "Sensor\\src\\main\\resources\\current_reading.txt"

def write_file(package):
    file = open(path, "w")
    file.write(package)
    file.close()

def canonical_time():
    timenow = datetime.datetime.now()
    timestr = str(timenow)
    return str(timestr[0:19])

def main():
    arduino = serial.Serial(port='com3', baudrate=9600)
    print("Established Serial Communication to Arduino")
    print("Reading...")

    while True:

        serial_data = arduino.readline()
        string_data = str(serial_data[0:len(serial_data)].decode("utf-8"))
        string_pair = string_data.split(delim)

        temperature = string_pair[0]
        humidity = string_pair[1].strip()
        datetime = canonical_time()

        package = temperature + delim + humidity + delim + datetime
        
        write_file(package)

if __name__ == "__main__":
    main()
