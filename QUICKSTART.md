# Guide de Démarrage Rapide - Ouxy3

## Prérequis
- Android Studio Hedgehog | 2023.1.1 ou supérieur
- JDK 17
- Android SDK 34

## Installation

### 1. Cloner le Repository
```bash
git clone https://github.com/DaMolks/Ouxy3.git
cd Ouxy3
```

### 2. Configuration Android Studio
1. Ouvrir Android Studio
2. File > Open > Sélectionner le dossier Ouxy3
3. Attendre la synchronisation Gradle

### 3. Configuration du Projet
- Vérifier que le SDK Path est correctement configuré
- Vérifier que Gradle JDK est sur Java 17

## Structure du Code

### Architecture MVVM
```kotlin
class ExampleViewModel : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
}
```

### Injection de Dépendances (Koin)
```kotlin
val appModule = module {
    single { SessionManager(get()) }
    viewModel { MainViewModel(get()) }
}
```

### Accès aux Données
```kotlin
@Dao
interface TechnicianDao {
    @Query("SELECT * FROM technicians")
    fun getAllTechnicians(): Flow<List<Technician>>
}
```

## Tests

### Tests Unitaires
```bash
./gradlew test
```

### Tests d'Intégration
```bash
./gradlew connectedAndroidTest
```

## Points d'Attention
1. Vérifier les permissions dans AndroidManifest
2. Configurer la clé de signature pour le release
3. Tester sur différentes versions d'Android

## Déploiement
1. Mettre à jour versionCode/versionName
2. Générer un APK signé
```bash
./gradlew assembleRelease
```

## Ressources
- Documentation technique : `/docs`
- Logs des changements : `CHANGELOG.md`
- Documentation des APIs : `/docs/api`
