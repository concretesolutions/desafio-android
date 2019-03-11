package cl.getapps.githubjavarepos.dummy

import cl.getapps.githubjavarepos.feature.repos.domain.Owner
import cl.getapps.githubjavarepos.feature.repos.domain.Repo
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) repos.
     */
    val ITEMS: MutableList<Repo> = ArrayList()

    /**
     * A map of sample (dummy) repos, by ID.
     */
    val ITEM_MAP: MutableMap<String, Repo> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample repos.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: Repo) {
        ITEMS.add(item)
        ITEM_MAP[item.name] = item
    }

    private fun createDummyItem(position: Int): Repo {
        return Repo(
            "Repo $position name",
            "This is the description of the repo",
            Owner("User Name", "http://pngimg.com/uploads/android_logo/android_logo_PNG12.png"),
            7,
            2
        )
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}
