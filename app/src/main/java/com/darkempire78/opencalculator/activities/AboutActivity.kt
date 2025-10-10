package com.darkempire78.opencalculator.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.darkempire78.opencalculator.BuildConfig
import com.darkempire78.opencalculator.MyPreferences
import com.darkempire78.opencalculator.R
import com.darkempire78.opencalculator.Themes
import com.darkempire78.opencalculator.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Themes
        val themes = Themes(this)
        themes.applyDayNightOverride()
        setTheme(themes.getTheme())

        // Change the status bar color
        if (MyPreferences(this).theme == 1) { // Amoled theme
            window.statusBarColor = ContextCompat.getColor(this, R.color.amoled_background_color)
        } else {
            window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)
        }

        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set app version
        val versionName =  this.getString(R.string.about_other_version) + " "+ BuildConfig.VERSION_NAME + " (" + BuildConfig.VERSION_CODE + ")"
        binding.aboutAppVersion.text = versionName

        // back button
        binding.aboutBackButton.setOnClickListener {
            finish()
        }
        binding.aboutBackButtonHitbox.setOnClickListener {
            finish()
        }

        // Github
        binding.aboutGithub.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                "https://github.com/vtorres-t/OpenCalc".toUri()
            )
            startActivity(browserIntent)
        }

        // Easter egg
        var clickAppVersionCount = 0
        binding.aboutAppVersion.setOnClickListener {
            clickAppVersionCount++
            if (clickAppVersionCount > 3) {
                Toast.makeText(this, this.getString(R.string.about_easter_egg), Toast.LENGTH_SHORT).show()
                clickAppVersionCount = 0
            }
        }
    }
}
