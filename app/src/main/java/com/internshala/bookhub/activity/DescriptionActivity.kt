package com.internshala.bookhub.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.internshala.bookhub.R
import com.internshala.bookhub.database.BookDatabase
import com.internshala.bookhub.database.BookEntity
import com.internshala.bookhub.util.ConnectionManager
import com.squareup.picasso.Picasso
import org.json.JSONObject

class DescriptionActivity : AppCompatActivity() {

    lateinit var txtBookName: TextView
    lateinit var txtBookAuthor: TextView
    lateinit var txtBookPrice: TextView
    lateinit var txtBookRating: TextView
    lateinit var imgBookImage: ImageView
    lateinit var txtBookDesc: TextView
    lateinit var btnAddToFav: Button
    lateinit var progressBar: ProgressBar
    lateinit var progressLayout: RelativeLayout
    lateinit var btnreadpdf:Button
    lateinit var imgamazon:ImageView
    lateinit var imgkindle:ImageView
    lateinit var btnDownload:Button
    lateinit var descLayout:RelativeLayout

    lateinit var toolbar: Toolbar

    var bookId: String? = "100"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        txtBookName = findViewById(R.id.txtBookName)
        txtBookAuthor = findViewById(R.id.txtBookAuthor)
        txtBookPrice = findViewById(R.id.txtBookPrice)
        txtBookRating = findViewById(R.id.txtBookRating)
        imgBookImage = findViewById(R.id.imgBookImage)
        txtBookDesc = findViewById(R.id.txtBookDesc)
        btnAddToFav = findViewById(R.id.btnAddToFav)
        progressBar = findViewById(R.id.progressBar)
        btnreadpdf = findViewById(R.id.btnReadPdf)
        progressBar.visibility = View.VISIBLE
        progressLayout = findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE
        imgamazon = findViewById(R.id.imgAmazon)
        imgkindle = findViewById(R.id.imgKindle)
        btnDownload = findViewById(R.id.downloadBtn)
        descLayout = findViewById(R.id.descLayout)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Book Details"

        if (intent!=null) {
            bookId = intent.getStringExtra("book_id")
            println("description activity book_id $bookId")
        } else {
            finish()
            Toast.makeText(
                this@DescriptionActivity,
                "Some unexpected error occurred!",
                Toast.LENGTH_SHORT
            ).show()
        }

        if (bookId == "100") {
            finish()
            Toast.makeText(
                this@DescriptionActivity,
                "Some unexpected error occurred!",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnreadpdf.setOnClickListener {
            val intent = Intent(this, PdfReaderActivity::class.java)
            intent.putExtra("book_id", bookId)
            startActivity(intent)
        }

        when(bookId){
            "101" -> {
                btnDownload.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("http://9sk356oaz6.pdcdn5.top/dl2.php?id=6877926&h=3c3fda7ad2a3ca83af0bc7ea6b04db72&u=cache&ext=pdf&n=Ps%20i%20love%20you%20-%20readers%20stuffz")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "102" -> {
                btnDownload.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.wsfcs.k12.nc.us/cms/lib/NC01001395/Centricity/Domain/7935/Gatsby_PDF_FullText.pdf")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "103" -> {
                btnDownload.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("http://p2saq2omx0.pdcdn2.top/dl2.php?id=4311807&h=54b8a15b6e3c783df760646619c09b01&u=cache&ext=pdf&n=Anna%20karenina%20by%20leo%20tolstoy")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "104" -> {
                btnDownload.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("http://wnsjsxt0c5.pdcdn5.top/dl2.php?id=193189732&h=861811fc238b00cdfb983409b59cc572&u=cache&ext=pdf&n=Madame%20bovarys%20daughter%20a%20novel")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "105" -> {
                btnDownload.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("http://www.planetebook.com/free-ebooks/war-and-peace.pdf")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "106" -> {
                btnDownload.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("http://sg1dg6zt9s.pdcdn1.top/dl2.php?id=186066623&h=ad4c280d33e58a77b5f51e66ab0d327f&u=cache&ext=pdf&n=Lolita%20penguin%20modern%20classics")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "107" -> {
                btnDownload.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("http://mzxqw3aa22.pdcdn1.top/dl2.php?id=52296633&h=d2dfbadfd4e36dda21d733d1446f2158&u=cache&ext=pdf&n=George%20eliot%20middlemarch")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "108" -> {
                btnDownload.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("http://xqb5k1zaoz.pdcdn4.top/dl2.php?id=186648141&h=c076bbf4a4ace5aa94535805abea095a&u=cache&ext=pdf&n=Mark%20twains%20the%20adventures%20of%20huckleberry%20finn%20blooms%20modern%20critical%20interpretations")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "109" -> {
                btnDownload.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.ieterna.org/archive-pdf/fiction/adventure/Moby%20Dick.pdf")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "110" -> {
                btnDownload.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("http://17a3yglal6.pdcdn3.top/dl2.php?id=199867074&h=23668174fffee6e95d9dbb1f6ba39017&u=cache&ext=pdf&n=The%20lord%20of%20the%20rings%20trilogy")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            else->{
                Snackbar.make(descLayout, "Not Available", Snackbar.LENGTH_SHORT)
                    .setAction("Ok"){}
                    .show()
            }

        }

        when(bookId){
            "101" -> {
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.in/PS-Love-You-Cecelia-Ahern/dp/0008331650/ref=sr_1_1?dchild=1&keywords=PS+I+Love+you&qid=1601807982&sr=8-1")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.in/PS-Love-You-Cecelia-Ahern-ebook/dp/B002SZAU1Y/ref=tmm_kin_swatch_0?_encoding=UTF8&qid=1601807982&sr=8-1")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "102" -> {
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Great-Gatsby-F-Scott-Fitzgerald/dp/0743273567" +
                                "/ref=sr_1_2?dchild=1&keywords=great+gatsby&qid=1601808146&s=amazon-devices&sr=1-2")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Great-Gatsby-F-Scott-Fitzgerald-ebook/dp/B08JKMCLRT/ref=tmm_kin_swatch_0?_encoding=UTF8&qid=1601808146&sr=1-2")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "103" -> {
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Anna-Karenina-Annotated-Nikolayevich-Tolstoy/dp/B08GVJLN54/ref=tmm_pap_swatch_0?_encoding=UTF8&qid=1601808280&sr=1-8")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Anna-Karenina-Annotated-Leo-Tolstoy-ebook/dp/B088C575DC/ref=tmm_kin_swatch_0?_encoding=UTF8&qid=1601808280&sr=1-8")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "104" -> {
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Madame-Collins-Classics-Gustave-Flaubert/dp/0007420307/ref=tmm_pap_swatch_0?_encoding=UTF8&qid=1601808338&sr=1-1")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Madame-Bovary-Tale-Provincial-Life-ebook/dp/B004TQALTQ/ref=tmm_kin_swatch_0?_encoding=UTF8&qid=1601808338&sr=1-1")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "105" -> {
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/War-Peace-Penguin-Clothbound-Classics/dp/0241265541/ref=tmm_hrd_swatch_0?_encoding=UTF8&qid=1601808403&sr=1-1")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/War-Peace-Leo-Tolstoy-ebook/dp/B003X4M9F4/ref=tmm_kin_swatch_0?_encoding=UTF8&qid=1601808403&sr=1-1")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "106" -> {
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Real-Lolita-Kidnapping-Horner-Scandalized/dp/0062661922/ref=tmm_hrd_swatch_0?_encoding=UTF8&qid=1601808443&sr=1-9")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Real-Lolita-Kidnapping-Horner-Scandalized-ebook/dp/B076P91Z4D/ref=tmm_kin_swatch_0?_encoding=UTF8&qid=1601808443&sr=1-9")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "107" -> {
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Middlemarch-George-Eliot/dp/B08JF17M5B/ref=tmm_pap_swatch_0?_encoding=UTF8&qid=1601808504&sr=1-1")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Middlemarch-George-Eliot-ebook/dp/B084KQT9G5/ref=tmm_kin_swatch_0?_encoding=UTF8&qid=1601808504&sr=1-1")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "108" -> {
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Adventures-Huckleberry-Finn-ANNOTATED-ILLUSTRATED/dp/B08KH133F1/ref=tmm_pap_swatch_0?_encoding=UTF8&qid=1601808571&sr=1-6")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Adventures-Huckleberry-Finn-annotated-illustrated-ebook-dp-B084JT56WZ/dp/B084JT56WZ/ref=mt_other?_encoding=UTF8&me=&qid=1601808571")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "109" -> {
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/dp/B08KNS363T/ref=sr_1_4?dchild=1&keywords=moby+dick&qid=1601808626&s=digital-text&sr=1-4")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/dp/B08KNS363T/ref=sr_1_4?dchild=1&keywords=moby+dick&qid=1601808626&s=digital-text&sr=1-4")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            "110" -> {
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Lord-Rings-3-Book/dp/B00VZJ4YYS/ref=sr_1_8?dchild=1&keywords=the+lord+of+the+ring+trilogy&qid=1601808662&s=digital-text&sr=1-8")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("https://www.amazon.com/Lord-Rings-3-Book/dp/B00VZJ4YYS/ref=sr_1_8?dchild=1&keywords=the+lord+of+the+ring+trilogy&qid=1601808662&s=digital-text&sr=1-8")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            else->{
                imgamazon.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("amazon.in")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                imgkindle.setOnClickListener {
                    val uri: Uri =
                        Uri.parse("amazon.com/dp/B07978J597")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }

        }


        val queue = Volley.newRequestQueue(this@DescriptionActivity)
        val url = "http://13.235.250.119/v1/book/get_book/"

        val jsonParams = JSONObject()
        jsonParams.put("book_id", bookId)


        if (ConnectionManager().checkConnectivity(this@DescriptionActivity)) {
            val jsonRequest =
                object : JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener {

                    try {

                        val success = it.getBoolean("success")
                        if (success) {
                            val bookJsonObject = it.getJSONObject("book_data")
                            progressLayout.visibility = View.GONE

                            val bookImageUrl = bookJsonObject.getString("image")
                            Picasso.get().load(bookJsonObject.getString("image"))
                                .error(R.drawable.default_book_cover).into(imgBookImage)
                            txtBookName.text = bookJsonObject.getString("name")
                            txtBookAuthor.text = bookJsonObject.getString("author")
                            txtBookPrice.text = bookJsonObject.getString("price")
                            txtBookRating.text = bookJsonObject.getString("rating")
                            txtBookDesc.text = bookJsonObject.getString("description")

                            val bookEntity = BookEntity(
                                bookId?.toInt() as Int,
                                txtBookName.text.toString(),
                                txtBookAuthor.text.toString(),
                                txtBookPrice.text.toString(),
                                txtBookRating.text.toString(),
                                txtBookDesc.text.toString(),
                                bookImageUrl
                            )

                            val checkFav = DBAsyncTask(applicationContext, bookEntity, 1).execute()
                            val isFav = checkFav.get()

                            if (isFav) {
                                btnAddToFav.text = "Remove from Favourites"
                                val favColor = ContextCompat.getColor(
                                    applicationContext,
                                    R.color.colorFavourite
                                )
                                btnAddToFav.setBackgroundColor(favColor)
                            } else {
                                btnAddToFav.text = "Add to Favourites"
                                val noFavColor =
                                    ContextCompat.getColor(applicationContext, R.color.colorPrimary)
                                btnAddToFav.setBackgroundColor(noFavColor)
                            }

                            btnAddToFav.setOnClickListener {

                                if (!DBAsyncTask(
                                        applicationContext,
                                        bookEntity,
                                        1
                                    ).execute().get()
                                ) {

                                    val async =
                                        DBAsyncTask(applicationContext, bookEntity, 2).execute()
                                    val result = async.get()
                                    if (result) {
                                        Toast.makeText(
                                            this@DescriptionActivity,
                                            "Book added to favourites",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        btnAddToFav.text = "Remove from favourites"
                                        val favColor = ContextCompat.getColor(
                                            applicationContext,
                                            R.color.colorFavourite
                                        )
                                        btnAddToFav.setBackgroundColor(favColor)
                                    } else {
                                        Toast.makeText(
                                            this@DescriptionActivity,
                                            "Some error occurred!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {

                                    val async =
                                        DBAsyncTask(applicationContext, bookEntity, 3).execute()
                                    val result = async.get()

                                    if (result) {
                                        Toast.makeText(
                                            this@DescriptionActivity,
                                            "Book removed from favourites",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        btnAddToFav.text = "Add to favourites"
                                        val noFavColor =
                                            ContextCompat.getColor(
                                                applicationContext,
                                                R.color.colorPrimary
                                            )
                                        btnAddToFav.setBackgroundColor(noFavColor)
                                    } else {
                                        Toast.makeText(
                                            this@DescriptionActivity,
                                            "Some error occurred!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }
                            }

                        } else {
                            Toast.makeText(
                                this@DescriptionActivity,
                                "Some Error Occurred!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } catch (e: Exception) {
                        Toast.makeText(
                            this@DescriptionActivity,
                            "Some error occurred!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }, Response.ErrorListener {

                    Toast.makeText(this@DescriptionActivity, "Volley Error $it", Toast.LENGTH_SHORT)
                        .show()

                }) {
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Content-type"] = "application/json"
                        headers["token"] = "9bf534118365f1"
                        return headers
                    }
                }

            queue.add(jsonRequest)
        } else {
            val dialog = AlertDialog.Builder(this@DescriptionActivity)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection is not Found")
            dialog.setPositiveButton("Open Settings") { text, listener ->
                val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                finish()
            }

            dialog.setNegativeButton("Exit") { text, listener ->
                ActivityCompat.finishAffinity(this@DescriptionActivity)
            }
            dialog.create()
            dialog.show()
        }

    }


    class DBAsyncTask(val context: Context, val bookEntity: BookEntity, val mode: Int) :
        AsyncTask<Void, Void, Boolean>() {

        /*
        Mode 1 -> Check DB if the book is favourite or not
        Mode 2 -> Save the book into DB as favourite
        Mode 3 -> Remove the favourite book
        * */

        val db = Room.databaseBuilder(context, BookDatabase::class.java, "books-db").build()

        override fun doInBackground(vararg p0: Void?): Boolean {

            when (mode) {

                1 -> {

                    // Check DB if the book is favourite or not
                    val book: BookEntity? = db.bookDao().getBookById(bookEntity.book_id.toString())
                    db.close()
                    return book != null

                }

                2 -> {

                    // Save the book into DB as favourite
                    db.bookDao().insertBook(bookEntity)
                    db.close()
                    return true

                }

                3 -> {

                    // Remove the favourite book
                    db.bookDao().deleteBook(bookEntity)
                    db.close()
                    return true

                }
            }
            return false
        }

    }
}

