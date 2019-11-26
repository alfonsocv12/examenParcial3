package com.example.examenfinalmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create.*

class create : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        actionSave()
    }

    private fun actionSave(){
        saveBtn.setOnClickListener {
            val song = hashMapOf(
                "name" to nameEtput.text.toString(),
                "author" to authorEtput.text.toString()
            )
            db.collection("songs")
                .add(song)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this,"DocumentSnapshot added with ID: ${documentReference.id}",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this,"Error adding document", Toast.LENGTH_LONG).show()
                }
        }
    }
}
