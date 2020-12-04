package com.internshala.bookhub.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView
import com.internshala.bookhub.R

    class PdfReaderActivity : AppCompatActivity() {

        lateinit var pdfView:PDFView
        lateinit var toolbarpdf: androidx.appcompat.widget.Toolbar

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_pdf_reader)

            pdfView = findViewById(R.id.pdfView)
            toolbarpdf = findViewById(R.id.toolbarPdf)

            val bookid = intent.getStringExtra("book_id")

            setSupportActionBar(toolbarpdf)
            supportActionBar?.title = "Book PDF"

            when(bookid){
                "101"->{
                    viewPdf("PSILoveYou.pdf")
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "P.S. I Love You"
                }
                "102"->{
                    viewPdf("greatgatsby.pdf")
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "The Great Gatsby"
                }
                "103"->{
                    viewPdf("AnnaKarenina.pdf")
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "Anna Karenina"
                }
                "104"->{
                    viewPdf("MadameBovary's.pdf")
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "Madame Bovary"
                }
                "105"->{
                    viewPdf("war-and-peace.pdf")
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "War and Peace"
                }
                "106"->{
                    viewPdf("Lolita.pdf")
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "Lolita"
                }
                "107"->{
                    viewPdf("Middlemarch.pdf")
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "Middlemarch"
                }
                "108"->{
                    viewPdf("the-adventures-of-huckleberry-finn.pdf")
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "The Adventure of Huckleberry Finn"
                }
                "109"->{
                    viewPdf("MobyDick.pdf")
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "Moby-Dick"
                }
                "110"->{
                    viewPdf("TheLordOfTheRings.pdf")
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "The Lord of the Rings"
                }
                else->{
                    setSupportActionBar(toolbarpdf)
                    supportActionBar?.title = "!!Error Window!!"
                    Toast.makeText(this,"Some Error Occurred!",Toast.LENGTH_LONG).show()
                }

            }

        }
        fun viewPdf(pdfName:String){
            pdfView.fromAsset(pdfName)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .defaultPage(0)
                // allows to draw something on the current page, usually visible in the middle of the screen
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                // add dynamic spacing to fit each page on its own on the screen
                // mode to fit pages in the view
                // fit each page to the view, else smaller pages are scaled relative to largest page.
                .load()
        }
    }