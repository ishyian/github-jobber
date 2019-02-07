package yourbestigor.jobber.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import yourbestigor.jobber.R
import yourbestigor.jobber.databinding.JobItemBinding
import yourbestigor.jobber.model.Job

class JobsAdapter(private val context: Context) : RecyclerView.Adapter<JobsAdapter.JobsViewHolder>() {
    /**
     * The list of posts of the adapter
     */
    private var posts: List<Job> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: JobItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.job_item, parent, false)
        return JobsViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        holder.bind(posts[position])
    }


    /**
     * Updates the list of posts of the adapter
     * @param posts the new list of posts of the adapter
     */
    fun updatePosts(posts: List<Job>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    class JobsViewHolder(private val binding: JobItemBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a post into the view
         */
        fun bind(job: Job) {
            binding.job = job
            binding.executePendingBindings()
        }
    }
}
