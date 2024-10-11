package com.example.trabajopracticotinder.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trabajopracticotinder.models.Persona


class PersonaViewModel : ViewModel() {

    // Lista de personas que se mostrará en la app
    private val _personas = MutableLiveData<List<Persona>>()
    val personas: LiveData<List<Persona>>
        get() = _personas

    // Lista de personas a las que se ha dado Like
    private val _likedPersonas = MutableLiveData<List<Persona>>(emptyList())
    val likedPersonas: LiveData<List<Persona>>
        get() = _likedPersonas

    // Índice de la persona actualmente mostrada
    private var currentIndex = 0

    init {
        // Aquí puedes inicializar la lista de personas con datos de ejemplo
        loadPersonas()
    }

    // Carga inicial de personas (esto podría cargarse desde una fuente externa)
    private fun loadPersonas() {
        _personas.value = listOf(
            Persona("Juancito Pinto", listOf(
                "resources/userPhotos/juancito_1.jpg",
                "resources/userPhotos/juancito_2.jpg",
                "resources/userPhotos/juancito_3.jpg"
            )),
            Persona("Perico de los Palotes", listOf(
                "resources/userPhotos/perico_1.jpg",
                "resources/userPhotos/perico_2.jpg",
                "resources/userPhotos/perico_3.jpg"
            ))
        )
    }

    // Función para dar "Like" a la persona actual
    fun likeCurrentPersona() {
        val currentPersona = getCurrentPersona()
        _likedPersonas.value = _likedPersonas.value?.plus(currentPersona)
        moveToNextPersona()
    }

    // Función para dar "Dislike" a la persona actual
    fun dislikeCurrentPersona() {
        moveToNextPersona()
    }

    // Obtiene la persona que está actualmente visible
    private fun getCurrentPersona(): Persona {
        return _personas.value?.get(currentIndex) ?: throw IllegalStateException("No personas available")
    }

    // Mueve al siguiente perfil de persona
    private fun moveToNextPersona() {
        if (_personas.value != null && currentIndex < _personas.value!!.size - 1) {
            currentIndex++
        } else {
            currentIndex = 0 // Reinicia la lista si llega al final
        }
    }

    // Obtiene el índice de la persona actual
    fun getCurrentIndex(): Int {
        return currentIndex
    }
}
