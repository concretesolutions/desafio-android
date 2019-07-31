package wilquer.lima.desafioconcrete.ui.repository

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.repository_activity.*
import org.jetbrains.anko.toast
import wilquer.lima.desafioconcrete.R
import wilquer.lima.desafioconcrete.data.model.Repository
import wilquer.lima.desafioconcrete.util.RecyclerRepositoryAdapter

class RepositoryActivity : AppCompatActivity(), RepositoryContract.View {

    private var presenter: RepositoryContract.Presenter? = null
    private var listRepositories  = mutableListOf<Repository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_activity)

        presenter = RepositoryPresenter(this)
        presenter?.initView()
        recycleRepositories.apply {
            layoutManager = LinearLayoutManager(this@RepositoryActivity)
            adapter = RecyclerRepositoryAdapter(listRepositories, this@RepositoryActivity)
        }
        window.navigationBarColor = Color.BLACK
    }

    override fun setProgress(active: Boolean) {
        if(active){
            progress?.visibility =  View.VISIBLE
        }else {
            progress?.visibility = View.INVISIBLE
        }
    }

    override fun repositories(listRepositories: List<Repository>?) {
        this.listRepositories.addAll(listRepositories!!)
        recycleRepositories.adapter?.notifyDataSetChanged()
    }

    override fun error(msg: String) {
        toast(msg)
    }
}