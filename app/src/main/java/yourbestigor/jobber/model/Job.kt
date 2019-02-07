package yourbestigor.jobber.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Job {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("company")
    @Expose
    var company: String? = null
    @SerializedName("company_url")
    @Expose
    var companyUrl: Any? = null
    @SerializedName("location")
    @Expose
    var location: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("how_to_apply")
    @Expose
    var howToApply: String? = null
    @SerializedName("company_logo")
    @Expose
    var companyLogo: Any? = null

}