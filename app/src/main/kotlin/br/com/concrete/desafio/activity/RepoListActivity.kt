package br.com.concrete.desafio.activity

import br.com.concrete.desafio.toast

class RepoListActivity : android.support.v7.app.AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.com.concrete.R.layout.activity_main)
        br.com.concrete.sdk.RepoRepository.search(page = 0).subscribe(
                { toast("${it.totalCount}") },
                { toast("${it.message}") })
    }
}