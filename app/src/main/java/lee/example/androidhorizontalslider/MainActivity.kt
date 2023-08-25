package lee.example.androidhorizontalslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lee.module.slider.middle.MiddleSlider
import lee.module.slider.vertical.VerticalSlider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<VerticalSlider>(R.id.slider_top).maxHeight = 480
        findViewById<VerticalSlider>(R.id.slider_top).setUpsideDown(false)

        findViewById<MiddleSlider>(R.id.slider_middle).valueActiveFixed = 0f
        findViewById<MiddleSlider>(R.id.slider_middle).value = 69f

        findViewById<MiddleSlider>(R.id.slider_middle_negative).valueActiveFixed = 0f
        findViewById<MiddleSlider>(R.id.slider_middle_negative).value = -69f
    }
}
