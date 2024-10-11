package com.example.trabajopracticotinder.View

import android.content.Context
import android.util.AttributeSet
import  android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.trabajopracticotinder.R

class PhotoBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var totalPhotos: Int = 0

    fun setupPhotoBar(photoCount: Int) {
        // Limpia la barra actual para agregar nuevas vistas
        removeAllViews()

        // Guarda cuántas fotos hay
        totalPhotos = photoCount

        // Crea y añade las vistas de las barras
        for (i in 0 until totalPhotos) {
            val view = View(context).apply {
                layoutParams = LayoutParams(20, 4).apply {
                    setMargins(4, 0, 4, 0)
                }
                background = ContextCompat.getDrawable(context, R.drawable.photo_bar_inactive)
            }
            addView(view)
        }
    }

    fun setActivePhoto(index: Int) {
        // Asegúrate de que el índice esté dentro del rango
        if (index < 0 || index >= totalPhotos) return

        // Actualiza todas las barras a inactivas
        for (i in 0 until totalPhotos) {
            val view = getChildAt(i)
            view.background = ContextCompat.getDrawable(
                context,
                if (i == index) R.drawable.photo_bar_active else R.drawable.photo_bar_inactive
            )
        }
    }
}
