package com.maze.sample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.maze.prefix.PrefixTextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.boy, null)
        
        val profile: PrefixTextView = findViewById(R.id.proflie)

        profile.prefix="نام مستعار: "// set name
        profile.setHintTextColor(Color.BLUE) //change color prefix
        profile.drawable=drawable//set predrawable

    }
}
