package entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Entity(
    @Transient
    var fileName: String = "",
    var id: String = "",
    var name: String = "",
)
