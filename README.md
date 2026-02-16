## Week 5 Native

Demo-videon linkki: https://unioulu-my.sharepoint.com/:v:/g/personal/jlievone24_students_oamk_fi/IQAA3EUBIHUDR41oBVCBWROOAS6J9DJrE5bdqgHbBLpGtgU?e=lr5nyB&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D

## Lyhyt kuvaus (Retrofit, Gson, Coroutines API-kutsu, UI-tila)

Sovellus hakee nyt OpenWeatherMapin API:sta säädatan käyttäjän syöttämän kaupungin perusteella ja näyttää sen Jetpack Compose -käyttöliittymässä.

Uusina työkaluina toimivat Retrofit HTTP-pyyntöjen hallintaa ja API-integraatiota varten, Gson JSON-datan Kotlin-dataluokiksi automaattisesti muuntamista varten ja Coroutines API-kutsujen tekemistä taustasäikeessä varten jotta UI ei jää jumiin. UI-tilaa hallitaan WeatherUiState-oliolla.

Sovelluksen pyöriessä käyttäjä siis syöttää kaupungin nimen tekstikenttään ja painamalla "Fetch Weather" ViewModel kutsuu Retrofit + Coroutines -ketjua jossa Retrofit rakentaa HTTP GET -pyynnön ja JSON-vastaus muunnetaan WeatherResponse-dataluokaksi Gsonin avulla jonka jälkeen ViewModel päivittää WeatherUiState-tilan. Compose UI sitten reagoi tilamuutoksiin automaattisesti ja näyttää kaupungin nimen, lämpötilan ja lyhyen sään kuvauksen

Virhetilanteessa (esim. väärin kirjoitettu kaupunki) näytetään virheilmoitus (404 not found).

## Eli yksinkertaisesti:

Syötä kaupunki tekstikenttään.

Paina "Fetch Weather".

Näet kaupungin sään ja lämpötilan.

Voit kytkeä tumman teeman päälle/pois näytön alalaidasta.

## API-avain

API-avain on tallennettu local.properties -tiedostoon ja siirretty BuildConfig-kentäksi tähän tyyliin:

# OPENWEATHER_API_KEY="tähän_oma_api_key"

# BuildConfig (app/build.gradle)
# buildConfigField("String", "OPENWEATHER_API_KEY", "\"${property("OPENWEATHER_API_KEY")}\"")

Retrofit käyttää tätä avainta API-kutsussa ilman että paljastaa API-avainta ja local.properties varmistaa ettei se eksy Githubiin.
