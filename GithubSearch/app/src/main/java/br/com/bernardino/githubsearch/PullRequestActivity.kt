package br.com.bernardino.githubsearch

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import br.com.bernardino.githubsearch.model.EXTRA_REPOSITORY
import br.com.bernardino.githubsearch.model.Repository

class PullRequestActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pullrequest)

        val repository = intent.getParcelableExtra<RepositoryDatabase>(EXTRA_REPOSITORY)
        Toast.makeText(this, "repository data: ${repository.name}", LENGTH_LONG).show()
    }
}
