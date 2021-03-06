/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.hellotinyusdz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.sample_text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = findViewById<View>(R.id.container)

        // Set up a touch listener which calls the native sound engine
        view.setOnTouchListener {_, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                //playSound(true)
            } else if (event.action == MotionEvent.ACTION_UP) {
                //playSound(false)
            } else {
                return@setOnTouchListener false
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        if (createStream() != 0) {
            val errorString : String = "ERROR"
            Toast.makeText(applicationContext, errorString,Toast.LENGTH_LONG).show()
            sample_text.text = errorString
        }
    }

    override fun onPause() {
        super.onPause()
    }


    // Creates and starts Oboe stream to play audio
    private external fun createStream() : Int

    companion object {
        // Used to load native code calling oboe on app startup.
        init {
            System.loadLibrary("hello-tinyusdz")
        }
    }
}
