package com.example.examenfinalmoviles

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createButton()
        listenDB()
    }

    private fun createButton(){
        createBtn.setOnClickListener{
            startActivity(Intent(this, create::class.java))
        }
    }

    @SuppressLint("ShowToast")
    private fun listenDB(){
        db.collection("songs")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Toast.makeText(this,"Listen failed.",Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }
                val coll = ArrayList<String>()
                for (doc in value!!) {
                    coll.add(doc.toString())
                }
                songsList.adapter = ArrayAdapter(this, R.layout.song_item, coll)
            }
    }
}
