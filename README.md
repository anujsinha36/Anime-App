Anime-App
Android app that uses the Jikan API to fetch and display a list of anime series, allowing users to view details and trailers.

Description: This is an Android application that fetches data from the Jikan API to display anime and their details. The app is designed to provide users with a simple and intuitive way to explore anime information, including titles, descriptions, ratings, and more.

Assumptions Made:
1. API Stability: The Jikan API will remain operational and its endpoints unchanged during the app's use.
2. Internet Connection: Users will have an active and stable internet connection to fetch data from the API.
3. Data Accuracy: The information provided by the Jikan API is assumed to be accurate and up-to-date.
4. Device Compatibility: The application is assumed to run on Android devices API level 21+.

Features Implemented:
1. Anime Listing: Fetches and displays a list of top anime from the Jikan API.
2. Anime Details: Allows users to view detailed information about a selected anime, such as: Title Synopsis Genre Rating Trailer

Known Limitations:
1. Offline Functionality: The app does not support offline mode. Data cannot be accessed without an internet connection.
2. Cast Details are not available in this API.
3. Basic UI Design: The UI is functional but minimalistic, with scope for further improvement in design and interactivity.
4. Language Support: Currently, the app only supports English and data provided by the Jikan API.
5. Limited Test Coverage: The app has not been extensively tested across all Android devices or OS versions.

Installation:
1. Clone the repository:
2. Open the project in Android Studio.
3. Build the project to download dependencies.
4. Run the app on an emulator or physical device.

API Reference: https://docs.api.jikan.moe/

Future Improvements:
1. Offline Support: Add data caching to enable offline viewing.
2. UI Enhancements: Improve UI/UX for a better user experience.
3. Multi-language Support: Add localization for multiple languages.
