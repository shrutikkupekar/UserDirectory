User Directory (Offline-First Android App)

A simple offline-first User Directory app built using Kotlin, Jetpack Compose, Room, Retrofit, Moshi, Coroutines, and MVVM. The app fetches users from the JSONPlaceholder API and stores them locally, allowing full offline access and instant loading.

🚀 Features

• Fetch Users (GET Only)
Retrieves 10 users from:
https://jsonplaceholder.typicode.com/users

• Local Caching with Room
Stores users in a Room database (UserEntity) using OnConflictStrategy.REPLACE.

• Offline-First Architecture
The UI always reads from Room first.
On launch:

Shows cached data instantly

Attempts API refresh

If successful → Room updates and UI auto-updates

If failed → cached data continues to show
No blank screens or crashes offline.

• Local Search (Offline)
Search bar filters by name or email using a Room SQL LIKE query.
No network calls for search.

🛠 Tech Stack

• Kotlin
• Jetpack Compose
• Room
• Retrofit + Moshi
• Coroutines + Flow
• MVVM Architecture
• Offline-First Pattern

📱 Screenshots (add these)

Online Mode — Full list of 10 users loaded
<img width="242" height="521" alt="Screenshot 2025-11-14 at 5 52 58 PM" src="https://github.com/user-attachments/assets/1f774439-4e76-41e7-b73b-1d0d43c98efa" />


Offline Mode — Same list visible with WiFi off

Search Feature Working — e.g., searching “Leanne” or “an”

📝 How It Works (Short Explanation)

The app always displays data from the Room database. When launched, it loads cached users instantly, then refreshes data from the network in the background. If network data is available, Room updates and the UI refreshes automatically via StateFlow.

🔗 API Used

https://jsonplaceholder.typicode.com/users

📂 Project Structure

data/
├── local/
│ ├── AppDatabase.kt
│ ├── UserDao.kt
│ └── UserEntity.kt
├── remote/
│ ├── JsonPlaceholderApi.kt
│ └── UserDto.kt
├── Mappers.kt
└── UserRepository.kt

di/
└── ServiceLocator.kt

ui/
├── UserViewModel.kt
├── UserViewModelFactory.kt
└── UserListScreen.kt

MainActivity.kt

🧪 Testing Summary

✔ Loads users online
✔ Shows cached data offline
✔ Search works completely offline
✔ No crashes on API failure or airplane mode

👤 Author

Shrutik Kupekar
