# Weather
This is a weather app that allows you to fetch and display the current weather for a specified city. It uses the MVVM architecture, Retrofit for networking, and Room Database for local data storage.

# Features
1. Main Screen:

a) A text input field to enter the city name.
b) A button to fetch the weather information.
c) A section to display the fetched weather information (e.g., temperature, weather condition, humidity).

2. Weather API:

a) Utilizes a free weather API (e.g., OpenWeatherMap).
b) Requires an API key to access the weather data.
c) UI Design:

3. Clean and user-friendly interface.
   a) Utilizes appropriate layouts, styles, and themes.

4. Networking:

a) Fetches weather data asynchronously using Retrofit.
b) Handles network errors gracefully (e.g., no internet connection, invalid city name).
c) Data Storage (Optional but Preferred):

5. Database

a) Stores the last searched city and its weather information locally using Room Database.
b) Displays the last searched city's weather information when the app is reopened.

6. Code Quality:

a) Follows best practices for Android development.
b) Ensures the code is clean, well-organized, and commented where necessary.
c) Uses MVVM architecture for better code management and separation of concerns.
