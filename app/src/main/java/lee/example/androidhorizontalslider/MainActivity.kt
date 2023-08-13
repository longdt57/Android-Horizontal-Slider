package lee.example.androidhorizontalslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lee.module.slider.vertical.VerticalSlider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<VerticalSlider>(R.id.slider).maxHeight = 1000
    }
}
