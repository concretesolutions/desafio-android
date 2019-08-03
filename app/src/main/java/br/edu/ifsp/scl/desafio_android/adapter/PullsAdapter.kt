package br.edu.ifsp.scl.desafio_android.adapter

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.edu.ifsp.scl.desafio_android.R
import br.edu.ifsp.scl.desafio_android.model.Pull
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.item_pull_list.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PullsAdapter(private val context: Context
                   , private val pull: List<Pull>
                   , val listener: (Int) -> Unit) : RecyclerView.Adapter<PullVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PullVH(LayoutInflater.from(parent.context), parent)
    override fun onBindViewHolder(holder: PullVH, position: Int) = holder.bind(pull.elementAt(position), position, listener)
    override fun getItemCount(): Int = pull.size
}

class PullVH(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pull_list, parent, false)) {
    private var ivAvatar: ImageView? = null
    private var tvTitle: TextView? = null
    private var tvBody: TextView? = null
    private var tvDate: TextView? = null
    private var tvLogin: TextView? = null

    init {
        ivAvatar = itemView.pull_poster
        tvBody = itemView.pull_body
        tvDate = itemView.pull_date
        tvLogin = itemView.pull_login
        tvTitle = itemView.pull_title
    }

    fun bind(pull: Pull, pos: Int, listener: (Int) -> Unit) = with(itemView) {
        Glide.with(this)
            .asBitmap()
            .load(pull.user.avatar_url)
            .listener(object: RequestListener<Bitmap> {
                override fun onLoadFailed(e: GlideException?,model: Any?,target: Target<Bitmap>?,isFirstResource: Boolean): Boolean {
                    repository_progress.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(resource: Bitmap?,model: Any?,target: Target<Bitmap>?,dataSource: DataSource?,isFirstResource: Boolean): Boolean {
                    repository_progress.visibility = View.GONE
                    return false
                }
            })
            .into(ivAvatar!!)

        var data = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var ldt: LocalDateTime = LocalDateTime.parse(pull.created_at.replace("Z", ""))
            var dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            data = dtf.format(ldt)
        } else {
            var ldt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            var dtf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            data = dtf.format(ldt.parse(pull.created_at))
        }

        tvBody?.text = "${pull.body}"
        tvDate?.text = "Date -> ${data}"
        tvLogin?.text = "${pull.user.login}"
        tvTitle?.text = "${pull.title}"

        layout_item_pull_list.setOnClickListener {
            listener(pos)
        }
    }
}