package com.example.openbooklibrary.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.openbooklibrary.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info.*
import org.json.JSONObject
import com.android.volley.Response
import com.example.openbooklibrary.database.BookDatabase
import com.example.openbooklibrary.database.BookEntity
import com.example.openbooklibrary.util.ConnectionManager
import org.json.JSONException


class DescriptionActivity : AppCompatActivity() {

    lateinit var txtBookName:TextView
    lateinit var txtBookAuthor:TextView
    lateinit var txtBookInfoPrice:TextView
    lateinit var txtBookInfoRating:TextView
    lateinit var imgBookImage:ImageView
    lateinit var txtBookDescp:TextView
    lateinit var btnFavourite:Button
    lateinit var progressBar: ProgressBar
    lateinit var progressLayout: RelativeLayout
    var bookId:String? = "100"
    lateinit var toolBar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        txtBookName = findViewById(R.id.txtInfoBookName)
        txtBookAuthor = findViewById(R.id.txtBookAuthorName)
        txtBookInfoPrice = findViewById(R.id.txtBookPrice)
        txtBookInfoRating = findViewById(R.id.txtBookRating)
        imgBookImage = findViewById(R.id.imgBook)
        btnFavourite = findViewById(R.id.btnAddToFavourite)
        txtBookDescp = findViewById(R.id.txtBookDescription)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        progressLayout = findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE
        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Book Details"
        if(intent!=null){
                bookId = intent.getStringExtra("book_id")
                println("description activity book_id $bookId")
        }else{
            finish()
            Toast.makeText(this@DescriptionActivity,"Error occurred",Toast.LENGTH_SHORT).show()
        }
        if(bookId=="100"){
            finish()
            Toast.makeText(this@DescriptionActivity,"Error occurred",Toast.LENGTH_SHORT).show()
        }
        val queue = Volley.newRequestQueue(this@DescriptionActivity)
        val url = "http://13.235.250.119/v1/book/get_book/"
        if(ConnectionManager().checkConnectivity(this@DescriptionActivity)){
            val jsonParams = JSONObject()
            jsonParams.put("book_id",bookId)
            val jsonRequest = object : JsonObjectRequest(Method.POST,url,jsonParams,Response.Listener {
                try{
                    val success = it.getBoolean("success")
                    println("it value $it")
                    Toast.makeText(this@DescriptionActivity,"$it",Toast.LENGTH_LONG).show()
                    println("book_id success = $success")
                    if(success){
                        val bookJsonObject = it.getJSONObject("book_data")
                        progressLayout.visibility = View.GONE
                        val bookImageUrl = bookJsonObject.getString("image")
                        Picasso.get().load(bookJsonObject.getString("image"))
                            .error(R.drawable.default_book_cover).into(imgBook)
                        txtBookName.text = bookJsonObject.getString("name")
                        txtBookAuthor.text = bookJsonObject.getString("author")
                        txtBookInfoPrice.text = bookJsonObject.getString("price")
                        txtBookInfoRating.text = bookJsonObject.getString("rating")
                        txtBookDescp.text = bookJsonObject.getString("description")

                        val bookEntity = BookEntity(
                            bookId?.toInt() as Int,
                            txtBookName.text.toString(),
                            txtBookAuthor.text.toString(),
                            txtBookInfoPrice.text.toString(),
                            txtBookInfoRating.text.toString(),
                            txtBookDescp.text.toString(),
                            bookImageUrl
                        )
                        val checkFav = DBAsyncTask(applicationContext,bookEntity,1).execute()
                        val isFav = checkFav.get()
                        if(isFav){
                            btnFavourite.text = "Remove from Favourite"
                            val favColor = ContextCompat.getColor(applicationContext,R.color.fav_color)
                            btnFavourite.setBackgroundColor(favColor)
                        }else{
                            btnFavourite.text = "Add to Favourite"
                            val nofavColor = ContextCompat.getColor(applicationContext,R.color.colorPrimary)
                            btnFavourite.setBackgroundColor(nofavColor)
                        }
                        btnFavourite.setOnClickListener {
                            if(!DBAsyncTask(applicationContext,bookEntity,1).execute().get()){

                                val async = DBAsyncTask(applicationContext,bookEntity,2).execute()
                                val result = async.get()
                                if(result){
                                    Toast.makeText(this@DescriptionActivity,"Book added",Toast.LENGTH_LONG).show()
                                    btnFavourite.text = "Remove from Favourite"
                                    val favColor = ContextCompat.getColor(applicationContext,R.color.fav_color)
                                    btnFavourite.setBackgroundColor(favColor)
                                }else{
                                    Toast.makeText(this@DescriptionActivity,"Error adding book to favourite",Toast.LENGTH_LONG).show()
                                }
                            }else{
                                val async = DBAsyncTask(applicationContext,bookEntity,3).execute()
                                val result = async.get()

                                if(result){
                                    Toast.makeText(this@DescriptionActivity,"Book removed",Toast.LENGTH_LONG).show()
                                    btnFavourite.text = "Add to Favourite"
                                    val nofavColor = ContextCompat.getColor(applicationContext,R.color.colorPrimary)
                                    btnFavourite.setBackgroundColor(nofavColor)
                                }else{
                                    Toast.makeText(this@DescriptionActivity,"Error adding book to favourite",
                                        Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }else{
                        Toast.makeText(this@DescriptionActivity,"Back End error occurred (else)!! Try Again!!",
                            Toast.LENGTH_SHORT).show()
                    }

                }catch (e: JSONException){
                    Toast.makeText(this@DescriptionActivity,"Back End error occurred (catch)!! Try Again!!",
                        Toast.LENGTH_SHORT).show()
                }
            },
                Response.ErrorListener {

                    Toast.makeText(this@DescriptionActivity,"Error from Volley occurred!! Try Again!!",
                        Toast.LENGTH_SHORT).show()

                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String,String>()
                    headers["Content-type"] = "application/json"
                    headers["token"] = "73f39fd33b23ba"
                    return headers
                }
            }
            queue.add(jsonRequest)
        }else{
            val dialog = AlertDialog.Builder(this@DescriptionActivity)
            dialog.setTitle("Error")
            dialog.setMessage("Internet connection NOT found!")
            dialog.setPositiveButton("Open Settings"){ text, listener->
                val settingIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingIntent)
                finish()
            }
            dialog.setNegativeButton("Exit"){text, listener->
                ActivityCompat.finishAffinity(this@DescriptionActivity)
            }
            dialog.create()
            dialog.show()
        }
    }
    class DBAsyncTask(context: Context,val bookEntity: BookEntity,val mode:Int):AsyncTask<Void,Void,Boolean>(){
        /*
        * 1-> Check in DB if book is in favourite
        * 2-> Save book in favourite
        * 3->   Remove the book from favourite
        * */
        val db = Room.databaseBuilder(context,BookDatabase::class.java,"books-db").build()
        override fun doInBackground(vararg params: Void?): Boolean {
            when(mode){
                1->{
                    //Check in DB if book is in favourite
                    val book:BookEntity? = db.bookDao().getBookById(bookEntity.book_id.toString())
                    db.close()
                    return book != null
                }
                2->{
                    //Save book in favourite
                    db.bookDao().insertBook(bookEntity)
                    db.close()
                    return true
                }
                3->{
                    db.bookDao().deleteBook(bookEntity)
                    db.close()
                    return true
                }
            }
            return false
        }

    }

    override fun onPause() {
        super.onPause()
        finish()
    }

}

