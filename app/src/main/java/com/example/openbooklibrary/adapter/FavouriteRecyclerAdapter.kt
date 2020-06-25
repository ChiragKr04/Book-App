package com.example.openbooklibrary.adapter
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.openbooklibrary.R
import com.example.openbooklibrary.activity.DescriptionActivity
import com.example.openbooklibrary.database.BookEntity
import com.squareup.picasso.Picasso
class FavouriteRecyclerAdapter(val context: Context, val bookList:List<BookEntity>)
    : RecyclerView.Adapter<FavouriteRecyclerAdapter.FavouriteViewHolder>() {
    class FavouriteViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val txtBookName:TextView = view.findViewById(R.id.txtFavBookTitle)
        val txtBookAuthor:TextView = view.findViewById(R.id.txtFavBookAuthor)
        val txtBookPrice:TextView = view.findViewById(R.id.txtFavBookPrice)
        val txtBookRating:TextView = view.findViewById(R.id.txtFavBookRating)
        val txtBookImage:ImageView = view.findViewById(R.id.imgFavBookImage)
        val llContent:LinearLayout = view.findViewById(R.id.llFavContent)
        val favCardView:CardView = view.findViewById(R.id.favCardView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_favourite_single_row,
            parent,false)
        return FavouriteViewHolder(view)
    }
    override fun getItemCount(): Int {
        return bookList.size
    }
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val book = bookList[position]
        holder.txtBookName.text = book.bookName
        holder.txtBookAuthor.text = book.bookAuthor
        holder.txtBookPrice.text = book.bookPrice
        holder.txtBookRating.text = book.bookRating
        Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.txtBookImage)
        holder.llContent.setOnClickListener {
            holder.llContent.setBackgroundColor(R.color.list_color)
            val intent = Intent(context,DescriptionActivity::class.java)
            android.os.Handler().postDelayed({
                holder.llContent.setBackgroundColor(Color.WHITE)
            },10)
            intent.putExtra("book_id",102)
            println("book.book_id = ${book.book_id} from favourite adapter")
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)//Prevent opening context layout twice
            context.startActivity(intent)
        }
    }
}