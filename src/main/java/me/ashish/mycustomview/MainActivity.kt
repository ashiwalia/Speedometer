package me.ashish.mycustomview

import android.animation.Animator
import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    var value: Float = 0f;

    lateinit var  MyView: Speedometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyView = findViewById(R.id.myView) as Speedometer
        start()


    }

    fun start(){

        var animator: Animator = ObjectAnimator.ofFloat(MyView, "testValue", 0f, 200f, 150f, 140f, 130f, 120f,
                100f, 90f, 60f, 150f, 200f, 180f, 190f, 200f,180f, 190f, 200f,180f, 190f, 200f,180f,
                190f, 200f,180f, 190f, 200f)
        animator.duration = 3000
        animator.start()
    }
}
