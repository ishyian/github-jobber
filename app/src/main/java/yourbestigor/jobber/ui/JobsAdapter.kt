package yourbestigor.jobber.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import yourbestigor.jobber.R
import yourbestigor.jobber.databinding.JobItemBinding
import yourbestigor.jobber.model.Job

class JobsAdapter(private val context: Context) : RecyclerView.Adapter<JobsAdapter.JobsViewHolder>() {


    private var jobs: List<Job> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: JobItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.job_item, parent, false)
        return JobsViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return jobs.size
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        holder.bind(jobs[position])
    }



    fun updateJobs(jobs: List<Job>) {
        this.jobs = jobs
        notifyDataSetChanged()
    }


    class JobsViewHolder(private val binding: JobItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(job: Job) {
            binding.job = job
            binding.executePendingBindings()
            Glide.with(binding.root)
                .load(job.companyLogo)
                .apply(RequestOptions()
                    .error(R.drawable.ic_github_logo))
                .into(binding.jobsIcon)

        }
    }
}
