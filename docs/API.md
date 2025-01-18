# Documentation de l'API

## Modèles de Données

### Technician
```kotlin
data class Technician(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val sector: String,
    val matricule: String,
    val teamLeader: String,
    val signaturePath: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
```

### Site
```kotlin
data class Site(
    val id: Long,
    val name: String,
    val address: String,
    val contactName: String?,
    val contactPhone: String?,
    val notes: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
```

## Sessions

### SessionManager
```kotlin
class SessionManager {
    fun isOnboardingCompleted(): Boolean
    fun setOnboardingCompleted()
    fun getTechnicianId(): Long?
    fun setTechnicianId(id: Long)
    fun clearSession()
}
```

## Événements

### Base Event
```kotlin
interface OuxyEvent {
    val type: String
    val version: Int
    val source: String
    val timestamp: Long
}
```

### Événements Spécifiques
```kotlin
data class TechnicianProfileUpdatedEvent(
    val technicianId: Long,
    override val source: String
) : BaseEvent()

data class SiteUpdatedEvent(
    val siteId: Long,
    override val source: String
) : BaseEvent()
```

## Repositories

### TechnicianRepository
```kotlin
class TechnicianRepository {
    fun getAllTechnicians(): Flow<List<Technician>>
    suspend fun getTechnicianById(id: Long): Technician?
    suspend fun insertTechnician(technician: Technician): Long
    suspend fun updateTechnician(technician: Technician)
    suspend fun updateTechnicianSignature(id: Long, path: String)
}
```

### SiteRepository
```kotlin
class SiteRepository {
    fun getAllSites(): Flow<List<Site>>
    suspend fun getSiteById(id: Long): Site?
    suspend fun insertSite(site: Site): Long
    suspend fun updateSite(site: Site)
    suspend fun deleteSite(site: Site)
}
```

## ViewModels

### TechnicianProfileViewModel
```kotlin
class TechnicianProfileViewModel {
    val state: LiveData<TechnicianProfileState>
    val events: LiveData<TechnicianProfileEvent>
    fun saveTechnician(formState: TechnicianFormState)
    fun clearEvent()
}
```

### SignatureViewModel
```kotlin
class SignatureViewModel {
    val state: LiveData<SignatureState>
    val events: LiveData<SignatureEvent>
    fun saveSignature(bitmap: Bitmap, technicianId: Long)
}
```

## Navigation

### Deep Links
- `ouxy://technician/{id}`
- `ouxy://site/{id}`

### Actions de Navigation
- `action_profile_to_signature`
- `action_signature_to_sites`
- `action_sites_to_main`

## Composants UI

### SignaturePad
```kotlin
class SignaturePad : View {
    fun clear()
    fun isEmpty(): Boolean
    fun toBitmap(): Bitmap
}
```
