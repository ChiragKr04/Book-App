package com.example.openbooklibrary.adapter
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color.WHITE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openbooklibrary.R
import com.example.openbooklibrary.activity.DescriptionActivity
import com.example.openbooklibrary.model.Book
import com.squareup.picasso.Picasso

class DashboardRecyclerAdapter(val context:Context,
                               val itemList:ArrayList<Book>)
    :RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>(){

    class DashboardViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val bookName:TextView = view.findViewById(R.id.txtBookName)
        val bookAuthor:TextView = view.findViewById(R.id.txtBookAuthor)
        val bookPrice:TextView = view.findViewById(R.id.txtBookPrice)
        val bookRating:TextView = view.findViewById(R.id.txtBookRating)
        val bookImage:ImageView = view.findViewById(R.id.imgBookImage)
        val llContent: LinearLayout = view.findViewById(R.id.llContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,
            parent,false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book = itemList[position]
        holder.bookName.text = book.bookName
        holder.bookAuthor.text = book.bookAuthor
        holder.bookPrice.text = book.bookPrice
        holder.bookRating.text = book.bookRating
        //holder.bookImage.setImageResource(book.bookImage)
        Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.bookImage)

        holder.llContent.setOnClickListener {
            holder.llContent.setBackgroundColor(R.color.list_color)
            val intent = Intent(context,DescriptionActivity::class.java)
            android.os.Handler().postDelayed({
                holder.llContent.setBackgroundColor(WHITE)
            },10)
            intent.putExtra("book_id",book.bookId)
            print("book.book_id = ${book.bookId} from dashboard adapter")
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)//Prevent opening context layout twice
            context.startActivity(intent)
        }
    }
}

