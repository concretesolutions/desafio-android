package br.com.bernardino.githubsearch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import br.com.bernardino.githubsearch.model.EXTRA_REPOSITORY

class PullRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pullrequest)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val repository = intent.getParcelableExtra<RepositoryDatabase>(EXTRA_REPOSITORY)
        Toast.makeText(this, "repository data: ${repository.name}", Toast.LENGTH_LONG).show()
    }

}
